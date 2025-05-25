package org.desing_patterns_work.factory;

public interface TransactionProcessor {
    boolean processTransaction(Transaction transaction);
    String getProcessorName();
}