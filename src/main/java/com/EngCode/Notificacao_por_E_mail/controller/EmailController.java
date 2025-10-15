package com.EngCode.Notificacao_por_E_mail.controller;

// BLOCÃO 1: IMPORTAÇÕES E FERRAMENTAS
// -------------------------------------------------------------------------

import com.EngCode.Notificacao_por_E_mail.business.EmailService; // Lógica de negócio (Service)
import com.EngCode.Notificacao_por_E_mail.business.dto.TarefasDTO; // DTO (dados de entrada da requisição)
import lombok.RequiredArgsConstructor; // Lombok para gerar o construtor necessário
import org.springframework.http.ResponseEntity; // Classe para formatar a resposta HTTP
import org.springframework.web.bind.annotation.PostMapping; // Anotação REST para requisições POST
import org.springframework.web.bind.annotation.RequestBody; // Anotação para mapear o corpo da requisição
import org.springframework.web.bind.annotation.RequestMapping; // Anotação para definir o caminho base
import org.springframework.web.bind.annotation.RestController; // Anotação principal do Controller

@RestController
// ANOTAÇÃO PRINCIPAL: Marca a classe como um Controller que lida com requisições HTTP REST (retorna JSON).
@RequiredArgsConstructor
// LOMBOK: Gera o construtor com a variável 'final', garantindo que o Spring injete a dependência.
@RequestMapping ("/email")
// Define o caminho base para todos os endpoints (ex: POST para /email).
public class EmailController {

    // BLOCÃO 2: INJEÇÃO DE DEPENDÊNCIA
    // -------------------------------------------------------------------------

    private final EmailService emailService;
    // VARIÁVEL ESSENCIAL: A palavra-chave 'final' é crucial. Ela garante que o Spring injete
    // a instância da EmailService no construtor (graças ao @RequiredArgsConstructor),
    // evitando o erro de NullPointerException que corrigimos.

    // BLOCÃO 3: ENDPOINT PRINCIPAL (ENVIO DE E-MAIL)
    // -------------------------------------------------------------------------

    @PostMapping
    // Mapeia requisições HTTP do tipo POST para o caminho /email.
    public ResponseEntity <Void> enviarEmail (@RequestBody TarefasDTO tarefasDTO) {
        // ResponseEntity <Void>: O retorno será apenas o Status HTTP (sem corpo no JSON).
        // @RequestBody TarefasDTO: Pega o JSON enviado no corpo da requisição e o converte
        // em um objeto TarefasDTO.

        // Chama o método do Service para executar a lógica de envio (construção, preenchimento e disparo do e-mail).
        emailService.enviaEmail(tarefasDTO);

        // Retorna a resposta HTTP. ResponseEntity.ok() define o status 200 (OK).
        // .build() cria a resposta sem corpo (Void), confirmando que a operação foi recebida.
        return ResponseEntity.ok().build();
    }
}