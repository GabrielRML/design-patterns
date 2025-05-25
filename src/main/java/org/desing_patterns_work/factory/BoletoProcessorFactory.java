package org.desing_patterns_work.factory;

public class BoletoProcessorFactory extends TransactionProcessorFactory {
    private final int expirationDays;
    
    public BoletoProcessorFactory(int expirationDays) {
        this.expirationDays = expirationDays;
    }
    
    @Override
    public TransactionProcessor createProcessor() {
        return new BoletoProcessor(expirationDays);
    }
}