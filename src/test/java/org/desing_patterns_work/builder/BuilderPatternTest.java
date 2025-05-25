package org.desing_patterns_work.builder;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

public class BuilderPatternTest {

    @Test
    public void testMinimalReport() {
        // Criar um relatório com apenas os campos obrigatórios
        FinancialReport report = new FinancialReport.Builder(
                "Relatório Básico",
                "Empresa Teste",
                LocalDate.of(2025, 1, 31))
            .build();
        
        String summary = report.getSummary();
        
        // Verificar que os campos obrigatórios estão presentes
        assertTrue(summary.contains("Relatório Básico"));
        assertTrue(summary.contains("Empresa: Empresa Teste"));
        assertTrue(summary.contains("Período: 2025-01-31"));
        
        // Verificar que não há recomendações ou disclaimer
        assertFalse(summary.contains("Recomendações:"));
        assertFalse(summary.contains("Aviso Legal:"));
    }
    
    @Test
    public void testCompleteReport() {
        // Criar um relatório completo com todos os campos
        FinancialReport report = new FinancialReport.Builder(
                "Relatório Completo",
                "Tech SA",
                LocalDate.of(2025, 3, 31))
            .addSection("Resumo")
            .addSection("Análise")
            .withFinancials(1000000.0, 800000.0, 200000.0)
            .addAnalyst("Analista 1")
            .addAnalyst("Analista 2")
            .addRecommendation("Comprar")
            .withDisclaimer("Texto de aviso legal")
            .build();
        
        String summary = report.getSummary();
        
        // Verificar que todos os campos estão presentes
        assertTrue(summary.contains("Relatório Completo"));
        assertTrue(summary.contains("Tech SA"));
        assertTrue(summary.contains("Resumo"));
        assertTrue(summary.contains("Análise"));
        assertTrue(summary.contains("Receita: R$1000000,00"));
        assertTrue(summary.contains("Despesas: R$800000,00"));
        assertTrue(summary.contains("Lucro: R$200000,00"));
        assertTrue(summary.contains("Analista 1"));
        assertTrue(summary.contains("Analista 2"));
        assertTrue(summary.contains("Comprar"));
        assertTrue(summary.contains("Aviso Legal: Texto de aviso legal"));
    }
    
    @Test
    public void testReportWithoutFinancials() {
        // Criar um relatório sem dados financeiros
        FinancialReport report = new FinancialReport.Builder(
                "Relatório Parcial",
                "Empresa ABC",
                LocalDate.of(2025, 2, 28))
            .addSection("Seção Única")
            .addRecommendation("Aguardar")
            .build();
        
        String summary = report.getSummary();
        
        // Verificar que as informações presentes estão corretas
        assertTrue(summary.contains("Relatório Parcial"));
        assertTrue(summary.contains("Seção Única"));
        assertTrue(summary.contains("Aguardar"));
        
        // Verificar que os valores financeiros são zero (default)
        assertTrue(summary.contains("Receita: R$0,00"));
    }
}