package org.desing_patterns_work.observer;

public class StockUpdate {
    private final String marketName;
    private final String stockSymbol;
    private final double price;
    private final double changePercentage;
    
    public StockUpdate(String marketName, String stockSymbol, double price, double changePercentage) {
        this.marketName = marketName;
        this.stockSymbol = stockSymbol;
        this.price = price;
        this.changePercentage = changePercentage;
    }
    
    public String getMarketName() {
        return marketName;
    }
    
    public String getStockSymbol() {
        return stockSymbol;
    }
    
    public double getPrice() {
        return price;
    }
    
    public double getChangePercentage() {
        return changePercentage;
    }
}