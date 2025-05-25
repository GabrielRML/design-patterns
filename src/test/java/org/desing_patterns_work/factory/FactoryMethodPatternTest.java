package org.desing_patterns_work.factory;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.*;

public class FactoryMethodPatternTest {
    private Transaction smallTransaction;
    private Transaction largeTransaction;
    private ByteArrayOutputStream outputStream;
    private PrintStream originalOut;

    @BeforeEach
    public void setUp() {
        // Configurar transações de teste
        smallTransaction = new Transaction(
            "Cliente Teste",
            "Loja Teste",
            1000.0,
            "Transação pequena"
        );

        largeTransaction = new Transaction(
            "Cliente Teste",
            "Loja Teste",
            10000.0,
            "Transação grande"
        );

        // Configurar captura de saída para os testes
        originalOut = System.out;
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }
    
    @Test
    public void testCreditCardProcessor() {
        // Criar a fábrica com limite
        TransactionProcessorFactory factory = new CreditCardProcessorFactory("Banco Teste", 5000.0);
        
        // Testar transação pequena (deve ser aprovada)
        assertTrue(factory.executeTransaction(smallTransaction));
        String output = outputStream.toString();
        assertTrue(output.contains("Processador de Cartão de Crédito - Banco Teste"));
        assertTrue(output.contains("Processando pagamento via cartão de crédito"));
        
        // Limpar o stream
        outputStream.reset();
        
        // Testar transação grande (deve ser rejeitada por exceder o limite)
        assertFalse(factory.executeTransaction(largeTransaction));
        output = outputStream.toString();
        assertTrue(output.contains("Transação excede o limite permitido"));
    }
    
    @Test
    public void testPixProcessor() {
        // Criar a fábrica de PIX
        TransactionProcessorFactory factory = new PixProcessorFactory("123", 100);
        
        // Testar processamento
        assertTrue(factory.executeTransaction(smallTransaction));
        String output = outputStream.toString();
        assertTrue(output.contains("Processador PIX - Banco 123"));
        assertTrue(output.contains("Iniciando transferência PIX"));
        assertTrue(output.contains("Transferência PIX concluída"));
        
        // Testar também com transação grande (PIX deve aceitar qualquer valor)
        outputStream.reset();
        assertTrue(factory.executeTransaction(largeTransaction));
    }
    
    @Test
    public void testBoletoProcessor() {
        // Criar a fábrica de boleto
        TransactionProcessorFactory factory = new BoletoProcessorFactory(10);
        
        // Testar processamento
        assertTrue(factory.executeTransaction(smallTransaction));
        String output = outputStream.toString();
        assertTrue(output.contains("Processador de Boleto Bancário"));
        assertTrue(output.contains("Boleto gerado com código"));
        assertTrue(output.contains("Vencimento em 10 dias"));
        
        // Boletos devem aceitar transações de qualquer valor
        outputStream.reset();
        assertTrue(factory.executeTransaction(largeTransaction));
    }
    
    @Test
    public void testFactoryMethodCreation() {
        // Testar a criação de processadores através das fábricas
        
        // Cartão de crédito
        TransactionProcessorFactory creditCardFactory = new CreditCardProcessorFactory("Banco ABC", 1000.0);
        TransactionProcessor creditProcessor = creditCardFactory.createProcessor();
        assertEquals("Processador de Cartão de Crédito - Banco ABC", creditProcessor.getProcessorName());
        assertTrue(creditProcessor instanceof CreditCardProcessor);
        
        // PIX
        TransactionProcessorFactory pixFactory = new PixProcessorFactory("789", 50);
        TransactionProcessor pixProcessor = pixFactory.createProcessor();
        assertEquals("Processador PIX - Banco 789", pixProcessor.getProcessorName());
        assertTrue(pixProcessor instanceof PixProcessor);
        
        // Boleto
        TransactionProcessorFactory boletoFactory = new BoletoProcessorFactory(15);
        TransactionProcessor boletoProcessor = boletoFactory.createProcessor();
        assertEquals("Processador de Boleto Bancário", boletoProcessor.getProcessorName());
        assertTrue(boletoProcessor instanceof BoletoProcessor);
    }
}