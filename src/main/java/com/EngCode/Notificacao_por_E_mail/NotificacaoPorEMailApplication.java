package com.EngCode.Notificacao_por_E_mail;
// Define o pacote raiz da sua aplicação. É a partir daqui que o Spring Boot começa a procurar
// todos os outros componentes (@Controller, @Service, @Component, etc.) do projeto.

import org.springframework.boot.SpringApplication;
// Importa a classe principal do Spring Boot que contém o método para iniciar a aplicação.
import org.springframework.boot.autoconfigure.SpringBootApplication;
// Importa a anotação que define o ponto de partida da configuração.

// BLOCÃO 1: CONFIGURAÇÃO E INICIALIZAÇÃO
// -------------------------------------------------------------------------
@SpringBootApplication
// Esta é a anotação mais importante do Spring Boot. Ela combina três anotações essenciais:
// 1. @EnableAutoConfiguration: Configura automaticamente o Spring Boot com base nas dependências (ex: detecta que você tem JavaMail e configura o MailSender).
// 2. @ComponentScan: Diz ao Spring para escanear e encontrar todos os componentes (@Controller, @Service, etc.) a partir deste pacote (com.EngCode.Notificacao_por_E_mail).
// 3. @Configuration: Indica que a classe pode ser usada para definir beans (objetos) de configuração.
public class NotificacaoPorEMailApplication {

    // BLOCÃO 2: MÉTODO MAIN
    // -------------------------------------------------------------------------
    public static void main(String[] args) {
        // O método 'main' padrão do Java, é o ponto de entrada que o sistema operacional usa.

        // Inicia o processo da aplicação Spring.
        // Ele carrega todas as configurações, escaneia os componentes, inicia o servidor embutido (Tomcat) e coloca sua aplicação em funcionamento.
        SpringApplication.run(NotificacaoPorEMailApplication.class, args);
    }

}