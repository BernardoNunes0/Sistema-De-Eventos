# Sistema de Eventos

Projeto desenvolvido para o trabalho prático A3 da disciplina de Garantia de Qualidade de Software.

## Tema

Sistema de gestão de eventos, participantes e ingressos, desenvolvido em Java com orientação a objetos, testes unitários e desenvolvimento orientado a testes (TDD).

## Integrantes

- Bernardo Nunes Gomes
- Luiza Karen Borges Pimentel
- Pedro Henrique Alves Soares
- Kauã Kellysson de Oliveira Mendes
- Lucas Medeiros dos Santos
- Álvaro Vasconcelos Garcia Figueiredo

## Funcionalidades

Pelo menu do terminal, é possível:

- Cadastrar, consultar por código, alterar e excluir eventos.
- Cadastrar, consultar por código, alterar e excluir participantes.
- Emitir, consultar por código, trocar o titular e excluir ingressos.

As classes de cadastro também oferecem métodos para listar todos os registros. A classe `Ingresso` controla as situações emitido, utilizado e cancelado e impede operações inválidas, como usar o mesmo ingresso duas vezes ou trocar o titular após o uso.

Os dados são mantidos em memória durante a execução do programa.

## Tecnologias

- Java 8
- Maven
- JUnit 5
- Checkstyle
- JaCoCo
- Git e GitHub

## Requisitos para executar

- JDK 8 instalado.
- Maven 3.6.3 ou superior instalado.
- Comandos `java`, `javac` e `mvn` disponíveis no terminal.

## Como obter o projeto

```bash
git clone https://github.com/BernardoNunes0/Sistema-De-Eventos.git
cd Sistema-De-Eventos/sistemadeevento
```

Os comandos seguintes devem ser executados na pasta `sistemadeevento`, onde está o arquivo `pom.xml`.

## Como compilar

```bash
mvn compile
```

## Como executar os testes

Para executar toda a suíte de testes:

```bash
mvn test
```

Para executar apenas os testes de uma classe, como `IngressoTest`:

```bash
mvn test "-Dtest=IngressoTest"
```

A execução também realiza a verificação de estilo pelo Checkstyle. Após os testes aprovados, o JaCoCo gera o relatório de cobertura em `target/site/jacoco/jacoco.xml`.

## Como executar o programa

Na pasta `sistemadeevento`, compile e execute:

```bash
mvn compile
java -cp target/classes com.evento.App
```

O menu interativo permite cadastrar, consultar, alterar e excluir eventos, participantes e ingressos. Os dados ficam em memória durante a execução.

Para rodar a demonstração automática com dados de exemplo, use:

```bash
java -cp target/classes com.evento.App --demo
```

## Desenvolvimento e testes

O histórico registra etapas de desenvolvimento com os prefixos `RED`, `GREEN` e `REFACTOR`, além de testes adicionais identificados como `TEST`.

Os testes verificam operações válidas, valores limite e situações de erro, conforme aplicável a cada comportamento. As alterações são desenvolvidas em branches e integradas à `main` por Pull Requests.
