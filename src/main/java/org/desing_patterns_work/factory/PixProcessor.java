package org.desing_patterns_work.factory;

public class PixProcessor implements TransactionProcessor {
    private final String bankCode;
    private final int processingTimeMs;
    
    public PixProcessor(String bankCode, int processingTimeMs) {
        this.bankCode = bankCode;
        this.processingTimeMs = processingTimeMs;
    }
    
    @Override
    public boolean processTransaction(Transaction transaction) {
        try {
            System.out.printf("[%s] Iniciando transferência PIX de R$%.2f para %s...%n", 
                bankCode, transaction.getAmount(), transaction.getRecipient());

            Thread.sleep(processingTimeMs);
            
            System.out.printf("[%s] Transferência PIX concluída com sucesso!%n", bankCode);
            return true;
        } catch (InterruptedException e) {
            System.out.printf("[%s] Erro durante processamento PIX: %s%n", bankCode, e.getMessage());
            return false;
        }
    }
    
    @Override
    public String getProcessorName() {
        return "Processador PIX - Banco " + bankCode;
    }
}