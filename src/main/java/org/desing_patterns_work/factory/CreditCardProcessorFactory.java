package org.desing_patterns_work.factory;

public class CreditCardProcessorFactory extends TransactionProcessorFactory {
    private final String bankName;
    private final double maxLimit;
    
    public CreditCardProcessorFactory(String bankName, double maxLimit) {
        this.bankName = bankName;
        this.maxLimit = maxLimit;
    }
    
    @Override
    public TransactionProcessor createProcessor() {
        return new CreditCardProcessor(bankName, maxLimit);
    }
}