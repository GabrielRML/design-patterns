package org.desing_patterns_work.observer;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.*;

public class ObserverPatternTest {
    private StockMarket market;
    private RetailInvestor retailInvestor;
    private InstitutionalInvestor institutionalInvestor;
    private ByteArrayOutputStream outputStream;
    private PrintStream originalOut;

    @BeforeEach
    public void setUp() {
        // Configurar o mercado e investidores
        market = new StockMarket("B3");
        retailInvestor = new RetailInvestor("Investidor Teste");
        institutionalInvestor = new InstitutionalInvestor("Fundo Teste", 3.0);

        // Configurar captura de saída para os testes
        originalOut = System.out;
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }

    @Test
    public void testInvestorNotifications() {
        // Adicionar os observadores
        market.addInvestor(retailInvestor);
        market.addInvestor(institutionalInvestor);

        // Notificar sobre uma pequena alteração
        market.notifyStockChange("PETR4", 32.45, 1.2);
        
        // Verificar se o investidor de varejo recebeu a notificação
        String output = outputStream.toString();
        assertTrue(output.contains("[Investidor Teste] Nova atualização para PETR4"));
        
        // Verificar que o investidor institucional não recebeu (abaixo do threshold)
        assertFalse(output.contains("[Fundo Teste] Atenção! Movimento significativo"));
        
        // Limpar o stream
        outputStream.reset();
        
        // Notificar sobre uma grande alteração
        market.notifyStockChange("VALE3", 68.75, -4.3);
        
        // Verificar se ambos investidores receberam notificação
        output = outputStream.toString();
        assertTrue(output.contains("[Investidor Teste] Nova atualização para VALE3"));
        assertTrue(output.contains("[Fundo Teste] Atenção! Movimento significativo"));
    }

    @Test
    public void testRemoveObserver() {
        // Adicionar e remover um observador
        market.addInvestor(retailInvestor);
        market.addInvestor(institutionalInvestor);
        market.removeInvestor(retailInvestor);
        
        outputStream.reset();
        
        // Notificar sobre alteração
        market.notifyStockChange("ITUB4", 29.18, 0.7);
        
        // Verificar que apenas o institucional recebeu (o de varejo foi removido)
        String output = outputStream.toString();
        assertFalse(output.contains("[Investidor Teste]"));
    }
}