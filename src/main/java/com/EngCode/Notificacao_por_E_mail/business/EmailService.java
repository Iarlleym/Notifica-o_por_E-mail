package com.EngCode.Notificacao_por_E_mail.business;

import com.EngCode.Notificacao_por_E_mail.business.dto.TarefasDTO;
import com.EngCode.Notificacao_por_E_mail.infrastructure.exceptions.EmailExceptions;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

@Service // ANOTAÇÃO SPRING: Marca esta classe como um Componente de Serviço, contendo a lógica de negócio principal.
@RequiredArgsConstructor // LOMBOK: Gera um construtor com todos os campos 'final', essencial para a Injeção de Dependência.
public class EmailService {

    // INJEÇÃO DE DEPENDÊNCIA: O Spring inicializa e fornece estas duas ferramentas essenciais.
    private final JavaMailSender javaMailSender; // Ferramenta do Spring para enviar e-mails via SMTP.
    private final TemplateEngine templateEngine; // Ferramenta do Thymeleaf para processar o template HTML.

    // INJEÇÃO DE VALORES: Puxa o valor da variável 'envio.email.remetente' do arquivo application.properties.
    @Value("${envio.email.remetente}")
    public String remetente;

    // INJEÇÃO DE VALORES: Puxa o valor do nome que aparecerá como remetente.
    @Value("${envio.email.nomeRemetente}")
    private String nomeRemetente;

    // MÉTODO PRINCIPAL: Recebe o DTO (objeto de dados) da tarefa e inicia o envio.
    public void enviaEmail (TarefasDTO tarefasDTO) {

        try {

            // PASSO 1: CRIAÇÃO DA MENSAGEM
            MimeMessage mensagem = javaMailSender.createMimeMessage(); // Cria a estrutura básica da mensagem MIME (e-mail).

            // Cria um Helper para configurar a MimeMessage.
            // O 'true' indica que o e-mail pode ter múltiplos formatos (como texto e HTML), e o UTF-8 define a codificação de caracteres.
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mensagem, true, StandardCharsets.UTF_8.name());

            // Define o endereço de e-mail e o nome do remetente (usando as variáveis @Value).
            mimeMessageHelper.setFrom(new InternetAddress(remetente, nomeRemetente));

            // Define o destinatário, pegando o e-mail do objeto DTO.
            mimeMessageHelper.setTo(InternetAddress.parse(tarefasDTO.getEmailUsuario()));

            // Define o assunto do e-mail.
            mimeMessageHelper.setSubject("Notificação de Tarefa");

            // PASSO 2: PREENCHIMENTO DO TEMPLATE COM O THYMELEAF
            Context context = new  Context(); // Cria o objeto Context, que armazena os dados que serão injetados no HTML.

            // Injeta as variáveis do DTO no Context, sob os nomes que o HTML espera.
            context.setVariable("nomeTarefa", tarefasDTO.getNomeTarefa());
            context.setVariable("dataEvento", tarefasDTO.getDataEvento());
            context.setVariable("descricao", tarefasDTO.getDescricao());

            // Pede ao Thymeleaf para processar ("renderizar") o template "notificacao.html"
            // (Assumindo que seu arquivo se chama 'notificacao.html' e está em src/main/resources/templates).
            String templateEmail = templateEngine.process("notificacao", context);

            // Adiciona o conteúdo HTML renderizado à mensagem. O 'true' final é crucial para que o e-mail seja interpretado como HTML.
            mimeMessageHelper.setText(templateEmail, true);

            // PASSO 3: ENVIO DA MENSAGEM
            javaMailSender.send(mensagem); // Envia a mensagem através da conexão SMTP configurada.


        }catch (MessagingException | UnsupportedEncodingException e) {
            // TRATAMENTO DE ERRO: Captura erros da API de e-mail (JavaMail) e da codificação de caracteres.
            // Lança uma exceção personalizada (EmailExceptions), encapsulando o erro original (e.getCause()).
            throw new EmailExceptions("Erro ao enviar o E-mail: ", e.getCause());
        }

    }

}