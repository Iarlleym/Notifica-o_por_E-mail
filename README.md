# 📧 Microserviço de Notificação por E-mail | EngCode

![Status](https://img.shields.io/badge/Status-Conclu%C3%ADdo-brightgreen?style=for-the-badge)
![Tecnologia](https://img.shields.io/badge/Spring%20Boot-3.x-blue?style=for-the-badge&logo=springboot)
![Linguagem](https://img.shields.io/badge/Java-17+-orange?style=for-the-badge&logo=java)
![Template Engine](https://img.shields.io/badge/Thymeleaf-Elegante-005F0F?style=for-the-badge&logo=thymeleaf)

---

## 🎯 Sobre o Projeto

Este é um **microserviço dedicado e isolado**, responsável exclusivamente pelo envio de notificações transacionais por e-mail.

Ele foi construído para ser chamado por outros serviços da arquitetura EngCode (como o Agendador de Tarefas), garantindo que a complexa tarefa de comunicação por e-mail não atrase ou sobrecarregue o sistema principal. A prioridade é a **qualidade visual**, utilizando templates HTML robustos e estilizados (Preto & Laranja).

## ✨ Funcionalidades em Destaque

| Ícone | Funcionalidade | Descrição Técnica |
| :---: | :--- | :--- |
| 📬 | **Envio Transacional** | Endpoint HTTP POST dedicado para disparar e-mails de notificação instantaneamente. |
| 🎨 | **Templates Dinâmicos** | Utilização do **Thymeleaf** para renderizar conteúdo dinâmico (nome da tarefa, prazo) em um template HTML altamente estilizado. |
| 🛡️ | **Design Robusto** | Template construído com **CSS Inline** e estrutura de tabelas, garantindo que o design (Preto & Laranja) não quebre em clientes de e-mail restritivos. |
| 🔗 | **Comunicação Simples** | Recebe um DTO com dados (email do destinatário, nome da tarefa, descrição) e executa o envio, mantendo a responsabilidade única. |

## 🛠️ Stack Tecnológico

* **Linguagem:** Java 17+
* **Framework:** Spring Boot 3.x
* **Envio de E-mail:** `Spring Mail` & `JavaMailSender`
* **Templates:** `Thymeleaf` (Processamento HTML)
* **Estilização:** CSS Inline (E-mail Design Best Practices)
* **Build:** Gradle
* **Versionamento:** GitHub (seguindo Git Flow)

## ⚙️ Arquitetura e Fluxo de Dados

Este microsserviço opera como um **Serviço de Comunicação**.

**Fluxo de Envio (Microsserviço de Tarefas ➡️ Notificações):**

1.  **Origem (Serviço de Tarefas):** Após a criação/atualização de uma tarefa, ele dispara uma requisição **HTTP POST** para o endpoint `/email`.
2.  **Destino (Esta API):** O `EmailController` recebe o **`TarefasDTO`** (contendo e-mail, título e descrição).
3.  **Lógica (`EmailService`):** O Service injeta o `JavaMailSender` e o `TemplateEngine`. Ele preenche o template HTML com as variáveis e executa o disparo SMTP.
4.  **Status:** A API retorna um `HTTP 200 OK` (sem corpo), confirmando que a requisição de envio foi recebida e processada.

## 🚀 Como Executar o Projeto

Para rodar este microserviço localmente, você precisa ter o Java 17+ instalado e configurar o serviço SMTP.

⚠️ LEMBRETE IMPORTANTE: Quem for executar o projeto deve pegar o código SMTP (host, porta, usuário e senha) do seu e-mail e substituir no arquivo application.yaml pelos valores corretos. Sem essa configuração, o envio de e-mails não será efetuado.


### 1. Clonar o Repositório

```bash
git clone [https://github.com/Iarlleym/Notifica-o_por-E-mail.git](https://github.com/Iarlleym/Notifica-o_por-E-mail.git)
cd Notifica-o_por_E-mail
