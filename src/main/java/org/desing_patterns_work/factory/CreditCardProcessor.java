package org.desing_patterns_work.factory;

public class CreditCardProcessor implements TransactionProcessor {
    private final String bankName;
    private final double maxLimit;
    
    public CreditCardProcessor(String bankName, double maxLimit) {
        this.bankName = bankName;
        this.maxLimit = maxLimit;
    }
    
    @Override
    public boolean processTransaction(Transaction transaction) {
        if (transaction.getAmount() > maxLimit) {
            System.out.printf("[%s] Transação excede o limite permitido: R$%.2f > R$%.2f%n", 
                bankName, transaction.getAmount(), maxLimit);
            return false;
        }
        
        System.out.printf("[%s] Processando pagamento via cartão de crédito: R$%.2f para %s%n", 
            bankName, transaction.getAmount(), transaction.getRecipient());
        return true;
    }
    
    @Override
    public String getProcessorName() {
        return "Processador de Cartão de Crédito - " + bankName;
    }
}