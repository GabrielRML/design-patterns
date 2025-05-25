package org.desing_patterns_work;

import org.desing_patterns_work.builder.FinancialReport;
import org.desing_patterns_work.factory.*;
import org.desing_patterns_work.observer.*;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        System.out.println("==== Demonstração de Padrões de Projeto ====\n");

        // Demonstração do padrão Observer
        demonstrateObserverPattern();

        System.out.println("\n-----------------------------------\n");

        // Demonstração do padrão Builder
        demonstrateBuilderPattern();

        System.out.println("\n-----------------------------------\n");

        // Demonstração do padrão Factory Method
        demonstrateFactoryMethodPattern();
    }

    private static void demonstrateObserverPattern() {
        System.out.println("PADRÃO OBSERVER - Sistema de Notificações de Investimentos");

        // Criar o mercado de ações (Subject)
        StockMarket b3 = new StockMarket("B3");

        // Criar observadores
        InvestorObserver investor1 = new RetailInvestor("Carlos Silva");
        InvestorObserver investor2 = new InstitutionalInvestor("Fundo XYZ Capital", 3.0);

        // Registrar observadores
        b3.addInvestor(investor1);
        b3.addInvestor(investor2);

        // Notificar sobre mudanças
        b3.notifyStockChange("PETR4", 32.45, 1.2);
        b3.notifyStockChange("VALE3", 68.75, -4.3);
        b3.notifyStockChange("ITUB4", 29.18, 0.7);
    }

    private static void demonstrateBuilderPattern() {
        System.out.println("PADRÃO BUILDER - Geração de Relatórios Financeiros");

        // Criar um relatório completo usando o Builder
        FinancialReport quarterlyReport = new FinancialReport.Builder(
                "Relatório Financeiro Q1 2025",
                "TechSolutions SA",
                LocalDate.of(2025, 3, 31))
                .addSection("Resumo Executivo")
                .addSection("Demonstração de Resultados")
                .addSection("Balanço Patrimonial")
                .addSection("Fluxo de Caixa")
                .withFinancials(2500000.00, 1750000.00, 750000.00)
                .addAnalyst("Maria Oliveira, CFA")
                .addAnalyst("João Pereira, MBA")
                .addRecommendation("Manter posição - expectativa de crescimento de 15% em 2025")
                .withDisclaimer("Este relatório é apenas para fins informativos e não constitui uma recomendação de investimento.")
                .build();

        // Exibir o relatório
        System.out.println(quarterlyReport.getSummary());
    }

    private static void demonstrateFactoryMethodPattern() {
        System.out.println("PADRÃO FACTORY METHOD - Processamento de Transações Financeiras");

        // Criar transação de exemplo
        Transaction transaction = new Transaction(
                "Julia Mendes",
                "Livraria Cultura",
                135.90,
                "Compra de livros"
        );

        // Utilizar diferentes fábricas para processar a mesma transação
        TransactionProcessorFactory creditCardFactory = new CreditCardProcessorFactory("Banco Digital", 5000.00);
        TransactionProcessorFactory pixFactory = new PixProcessorFactory("341", 1000);
        TransactionProcessorFactory boletoFactory = new BoletoProcessorFactory(10);

        System.out.println("\n1. Processamento por Cartão de Crédito:");
        creditCardFactory.executeTransaction(transaction);

        System.out.println("\n2. Processamento por PIX:");
        pixFactory.executeTransaction(transaction);

        System.out.println("\n3. Processamento por Boleto:");
        boletoFactory.executeTransaction(transaction);

        // Exemplo de transação que excede o limite
        Transaction largeTransaction = new Transaction(
                "Julia Mendes",
                "Loja de Eletrônicos",
                7500.00,
                "Smart TV 65 polegadas"
        );

        System.out.println("\n4. Tentativa de processamento de transação grande:");
        creditCardFactory.executeTransaction(largeTransaction);
    }
}