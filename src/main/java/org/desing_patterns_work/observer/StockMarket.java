package org.desing_patterns_work.observer;

import java.util.ArrayList;
import java.util.List;

public class StockMarket {
    private final List<InvestorObserver> investors = new ArrayList<>();
    private final String marketName;
    
    public StockMarket(String marketName) {
        this.marketName = marketName;
    }
    
    public void addInvestor(InvestorObserver investor) {
        investors.add(investor);
    }
    
    public void removeInvestor(InvestorObserver investor) {
        investors.remove(investor);
    }
    
    public void notifyStockChange(String stockSymbol, double price, double changePercentage) {
        for (InvestorObserver investor : investors) {
            investor.update(new StockUpdate(marketName, stockSymbol, price, changePercentage));
        }
    }
}