package org.desing_patterns_work.observer;

public class RetailInvestor implements InvestorObserver {
    private final String name;
    
    public RetailInvestor(String name) {
        this.name = name;
    }
    
    @Override
    public void update(StockUpdate stockUpdate) {
        System.out.printf("[%s] Nova atualização para %s: R$%.2f (%.2f%%)%n", 
            name, stockUpdate.getStockSymbol(), stockUpdate.getPrice(), 
            stockUpdate.getChangePercentage());
    }
}