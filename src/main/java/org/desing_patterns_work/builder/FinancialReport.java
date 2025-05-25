package org.desing_patterns_work.builder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FinancialReport {
    private final String title;
    private final String company;
    private final LocalDate period;
    private final List<String> sections;
    private final double revenue;
    private final double expenses;
    private final double profit;
    private final List<String> analysts;
    private final List<String> recommendations;
    private final String disclaimer;
    
    private FinancialReport(Builder builder) {
        this.title = builder.title;
        this.company = builder.company;
        this.period = builder.period;
        this.sections = builder.sections;
        this.revenue = builder.revenue;
        this.expenses = builder.expenses;
        this.profit = builder.profit;
        this.analysts = builder.analysts;
        this.recommendations = builder.recommendations;
        this.disclaimer = builder.disclaimer;
    }
    
    public String getSummary() {
        StringBuilder sb = new StringBuilder();
        sb.append("===== ").append(title).append(" =====\n");
        sb.append("Empresa: ").append(company).append("\n");
        sb.append("Período: ").append(period).append("\n\n");
        
        sb.append("Seções: \n");
        for (String section : sections) {
            sb.append("- ").append(section).append("\n");
        }
        sb.append("\n");
        
        sb.append("Financeiro: \n");
        sb.append("- Receita: R$").append(String.format("%.2f", revenue)).append("\n");
        sb.append("- Despesas: R$").append(String.format("%.2f", expenses)).append("\n");
        sb.append("- Lucro: R$").append(String.format("%.2f", profit)).append("\n\n");
        
        if (!analysts.isEmpty()) {
            sb.append("Analistas: \n");
            for (String analyst : analysts) {
                sb.append("- ").append(analyst).append("\n");
            }
            sb.append("\n");
        }
        
        if (!recommendations.isEmpty()) {
            sb.append("Recomendações: \n");
            for (String recommendation : recommendations) {
                sb.append("- ").append(recommendation).append("\n");
            }
            sb.append("\n");
        }
        
        if (disclaimer != null && !disclaimer.isEmpty()) {
            sb.append("Aviso Legal: ").append(disclaimer).append("\n");
        }
        
        return sb.toString();
    }

    public static class Builder {
        private final String title;
        private final String company;
        private final LocalDate period;
        private final List<String> sections = new ArrayList<>();
        private double revenue;
        private double expenses;
        private double profit;
        private final List<String> analysts = new ArrayList<>();
        private final List<String> recommendations = new ArrayList<>();
        private String disclaimer;
        
        public Builder(String title, String company, LocalDate period) {
            this.title = title;
            this.company = company;
            this.period = period;
        }
        
        public Builder addSection(String section) {
            this.sections.add(section);
            return this;
        }
        
        public Builder withFinancials(double revenue, double expenses, double profit) {
            this.revenue = revenue;
            this.expenses = expenses;
            this.profit = profit;
            return this;
        }
        
        public Builder addAnalyst(String analyst) {
            this.analysts.add(analyst);
            return this;
        }
        
        public Builder addRecommendation(String recommendation) {
            this.recommendations.add(recommendation);
            return this;
        }
        
        public Builder withDisclaimer(String disclaimer) {
            this.disclaimer = disclaimer;
            return this;
        }
        
        public FinancialReport build() {
            return new FinancialReport(this);
        }
    }
}