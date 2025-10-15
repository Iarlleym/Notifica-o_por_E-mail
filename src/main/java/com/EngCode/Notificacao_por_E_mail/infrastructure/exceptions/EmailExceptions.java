package com.EngCode.Notificacao_por_E_mail.infrastructure.exceptions;

public class EmailExceptions extends RuntimeException{

    public EmailExceptions (String mensagem) {
        super(mensagem);
    }
    public EmailExceptions (String mensagem, Throwable throwable) {
        super(mensagem,throwable);
    }
}
