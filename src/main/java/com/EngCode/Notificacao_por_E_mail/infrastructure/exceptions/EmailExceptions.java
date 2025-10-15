package com.EngCode.Notificacao_por_E_mail.infrastructure.exceptions;
// Define o pacote onde a exceção reside. É comum colocar exceções em 'infrastructure'
// para indicar que elas se originam de falhas de comunicação ou configuração externa.

// BLOCÃO 1: DEFINIÇÃO DA CLASSE
// -------------------------------------------------------------------------
public class EmailExceptions extends RuntimeException{
    // A classe estende RuntimeException. Isso a torna uma 'unchecked exception' (não verificada)
    // no Java. Isso significa que o código que chama o método (o EmailService) não é
    // obrigado a usar um bloco try-catch, mas o Spring pode tratá-la em tempo de execução.

    // BLOCÃO 2: CONSTRUTORES (Formas de Lançar a Exceção)
    // -------------------------------------------------------------------------

    public EmailExceptions (String mensagem) {
        // Construtor Básico: Usado para lançar a exceção com uma mensagem de erro simples.
        // Exemplo: new EmailExceptions("Falha ao se conectar ao servidor SMTP.");
        super(mensagem);
        // Chama o construtor da classe pai (RuntimeException) para armazenar a mensagem.
    }
    public EmailExceptions (String mensagem, Throwable throwable) {
        // Construtor Completo: Usado para "embrulhar" uma exceção de baixo nível (a 'causa').
        // CONCEITO: No EmailService, ele encapsula a MessagingException (do JavaMail)
        // mantendo o rastreamento do erro original (throwable) enquanto lança sua exceção de negócio.
        super(mensagem,throwable);
    }
}