package org.desing_patterns_work.factory;

public abstract class TransactionProcessorFactory {
    
    public abstract TransactionProcessor createProcessor();
    
    public boolean executeTransaction(Transaction transaction) {
        TransactionProcessor processor = createProcessor();
        System.out.println("Utilizando " + processor.getProcessorName());
        return processor.processTransaction(transaction);
    }
}