package org.desing_patterns_work.observer;

public class InstitutionalInvestor implements InvestorObserver {
    private final String name;
    private final double minChangeThreshold;
    
    public InstitutionalInvestor(String name, double minChangeThreshold) {
        this.name = name;
        this.minChangeThreshold = minChangeThreshold;
    }
    
    @Override
    public void update(StockUpdate stockUpdate) {
        if (Math.abs(stockUpdate.getChangePercentage()) >= minChangeThreshold) {
            System.out.printf("[%s] Atenção! Movimento significativo em %s: %.2f%% (%s: R$%.2f)%n", 
                name, stockUpdate.getStockSymbol(), stockUpdate.getChangePercentage(), 
                stockUpdate.getMarketName(), stockUpdate.getPrice());
        }
    }
}