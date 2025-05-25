package org.desing_patterns_work.factory;

public class PixProcessorFactory extends TransactionProcessorFactory {
    private final String bankCode;
    private final int processingTimeMs;
    
    public PixProcessorFactory(String bankCode, int processingTimeMs) {
        this.bankCode = bankCode;
        this.processingTimeMs = processingTimeMs;
    }
    
    @Override
    public TransactionProcessor createProcessor() {
        return new PixProcessor(bankCode, processingTimeMs);
    }
}