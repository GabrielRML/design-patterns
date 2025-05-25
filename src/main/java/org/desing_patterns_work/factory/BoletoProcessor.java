package org.desing_patterns_work.factory;

public class BoletoProcessor implements TransactionProcessor {
    private final int expirationDays;
    
    public BoletoProcessor(int expirationDays) {
        this.expirationDays = expirationDays;
    }
    
    @Override
    public boolean processTransaction(Transaction transaction) {
        String boletoCode = generateBoletoCode(transaction);
        System.out.printf("Boleto gerado com código %s no valor de R$%.2f para %s%n", 
            boletoCode, transaction.getAmount(), transaction.getRecipient());
        System.out.printf("Vencimento em %d dias. Aguardando pagamento.%n", expirationDays);
        return true;
    }
    
    private String generateBoletoCode(Transaction transaction) {
        String recipient = transaction.getRecipient().replaceAll("\\s+", "");
        long amountInt = (long)(transaction.getAmount() * 100);
        return String.format("%d%d%s", 
            System.currentTimeMillis() % 1000000000, 
            amountInt,
            recipient.substring(0, Math.min(5, recipient.length())));
    }
    
    @Override
    public String getProcessorName() {
        return "Processador de Boleto Bancário";
    }
}