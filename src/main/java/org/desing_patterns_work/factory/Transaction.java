package org.desing_patterns_work.factory;

public class Transaction {
    private final String sender;
    private final String recipient;
    private final double amount;
    private final String description;
    
    public Transaction(String sender, String recipient, double amount, String description) {
        this.sender = sender;
        this.recipient = recipient;
        this.amount = amount;
        this.description = description;
    }
    
    public String getSender() {
        return sender;
    }
    
    public String getRecipient() {
        return recipient;
    }
    
    public double getAmount() {
        return amount;
    }
    
    public String getDescription() {
        return description;
    }
}