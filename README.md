# Padrões de Projeto: Observer, Builder e Factory Method

Este projeto demonstra a implementação de três importantes padrões de projeto em Java, cada um aplicado a um contexto de negócio diferente.

## Padrões Implementados

### 1. Padrão Observer - Sistema de Notificações de Investimentos

O padrão Observer estabelece uma relação um-para-muitos entre objetos, de forma que quando um objeto muda de estado, todos os seus dependentes são notificados e atualizados automaticamente.

**Implementação:**
- `StockMarket`: Objeto observável que notifica os investidores sobre mudanças nos preços das ações.
- `InvestorObserver`: Interface para todos os observadores.
- `RetailInvestor` e `InstitutionalInvestor`: Observadores concretos que reagem a notificações de maneiras diferentes.

**Caso de Uso:** Sistema onde investidores recebem atualizações sobre mudanças nos preços de ações, com investidores institucionais reagindo apenas a movimentos significativos.

### 2. Padrão Builder - Geração de Relatórios Financeiros

O padrão Builder separa a construção de um objeto complexo da sua representação, permitindo criar diferentes representações usando o mesmo processo de construção.

**Implementação:**
- `FinancialReport`: Objeto complexo com múltiplos campos opcionais.
- `FinancialReport.Builder`: Classe interna que constrói o relatório passo a passo.

**Caso de Uso:** Criação de relatórios financeiros flexíveis com diferentes seções, recomendações e informações, sem necessidade de construtores complexos.

### 3. Padrão Factory Method - Processamento de Transações Financeiras

O padrão Factory Method fornece uma interface para criar objetos, mas permite que as subclasses decidam quais classes instanciar.

**Implementação:**
- `TransactionProcessor`: Interface para processadores de transação.
- `CreditCardProcessor`, `PixProcessor`, `BoletoProcessor`: Implementações concretas.
- `TransactionProcessorFactory`: Classe abstrata que define o método factory.
- `CreditCardProcessorFactory`, `PixProcessorFactory`, `BoletoProcessorFactory`: Fábricas concretas.

**Caso de Uso:** Sistema de processamento de pagamentos que suporta diferentes métodos (cartão de crédito, PIX, boleto) com regras específicas para cada um.

## Como Executar o Projeto

### Requisitos
- Java 11 ou superior
- Gradle 7.0 ou superior

### Passos para Execução

1. Clone o repositório:
   ```bash
   git clone https://github.com/GabrielRML/design-patterns.git
   cd design-patterns
   ```

2. Compile o projeto:
   ```bash
   ./gradlew build
   ```

3. Execute a aplicação principal:
   ```bash
   ./gradlew run
   ```

## Testes Unitários

O projeto inclui testes unitários para cada padrão de projeto implementado.

### Executando os Testes

#### Via Gradle
```bash
# Executar todos os testes
./gradlew test

# Executar teste específico
./gradlew test --tests org.desing_patterns_work.factory.FactoryMethodPatternTest
```

#### Via IntelliJ IDEA
1. Clique com o botão direito na pasta `src/test/java`
2. Selecione "Run 'All Tests'"

Para executar um teste específico, abra a classe de teste e clique no ícone verde ao lado do método.

### Sobre os Testes

- **ObserverPatternTest**: Verifica se os investidores recebem notificações corretamente e se a remoção de investidores funciona.
- **BuilderPatternTest**: Testa a criação de relatórios com diferentes configurações, validando todos os campos.
- **FactoryMethodPatternTest**: Verifica se cada fábrica cria o processador correto e se cada tipo de processador funciona conforme esperado.
- **DesignPatternsIntegrationTest**: Testa a integração dos três padrões.
