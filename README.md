# Expenses Tracker

O aplicativo tem como objetivo centralizar e organizar informações de despesas financeiras por meio do registro desses gastos. Será possível fazer o cadastro, edição e exclusão de despesas e suas categorias e visualizá-las por completo ou pela utilização de filtro. Além disso, será possível fazer a visualização de relatórios de gastos em determinados períodos de tempo que informarão gastos totais, por categoria e etc.

## Recursos

- Criação, leitura, atualização e exclusão de categorias de despesas.
- Registro, leitura, alteração e remoção de despesas com valor, descrição, data e categoria.
- Conexão com um banco de dados PostgreSQL para persistência de dados.

## Tecnologias Utilizadas

- Java
- JavaFX
- PostgreSQL
- JUnit 5 (para testes)

## Configuração do Ambiente

1. **Pré-requisitos:**
  - Java
  - JavaFX
  - PostgreSQL
  - Maven

2. **Instalação do Banco de Dados:**
  - Crie um banco de dados chamado `expenses_tracker` ou altere o nome no arquivo `DatabaseConnection.java`.
  - Altere as credenciais no arquivo de caminho `main/java/expenses_tracker/dbDatabaseConnection.java`, se necessário. `Usuário e senha` estão como `postgres`;
  - Esquema do banco de dados está no arquivo de caminho `main/java/expenses_tracker/db/Schema.sql` 

## Testes

Os testes são escritos usando JUnit. Para executar os testes, use o seguinte comando: mvn test

**OBS1**: Os ícones não são suportados pelo console e provavelmente aparecerão como '?', mas na interface aparecem "corretamente". No banco são armazenados e aparecem corretamente.
**OBS2**: Por algum motivo não consegui executar os testes das classes DAOs com o mvn test, estavam sendo ignorados. Consegui apenas executando os arquivos diretamente.
**OBS3**: O teste getAllTest() da classe CategoryDAOTest.java apenas está retornando true apenas quando não há cadastros de categorias, pois os desconsidera. 
