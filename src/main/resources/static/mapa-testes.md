# 🗺️ Mapa de Testes e Marcos de Sucesso: Sistema Passaporte

Este documento mapeia os gatilhos de teste. Sempre que você concluir as classes de um determinado **Marco (Milestone)**, você deve parar de programar, abrir o Postman e executar as validações descritas abaixo. Se tudo passar, avance para o próximo bloco.

---

## 🏁 Marco 1: O Início do Ecossistema (Atores)
**Quando estiver pronto:** Toda a Fase 1 + Pacote de Exceptions.
*(Classes: Usuario, PerfilUsuarioEnum, GlobalExceptionHandler e suas camadas)*

### 🧪 O que você já pode testar no Postman:
1. **Cadastrar o RH e o Candidato:**
   - Faça um `POST /api/usuarios` enviando um JSON com `perfil: "RH"`. O banco deve retornar Status 201.
   - Faça outro `POST` criando um usuário com `perfil: "CONTRATADO"` (este será o nosso candidato de testes).
2. **Validar a Segurança do DTO:**
   - Olhe o JSON de resposta (Status 201). A `senha` **não** pode aparecer na resposta. Se aparecer, seu DTO está configurado errado.
3. **Validar o Tratamento de Exceções (A Chave Única):**
   - Tente fazer um `POST` criando um novo usuário enviando uma `chaveAcesso` que você já usou no passo anterior.
   - O sistema **não** pode explodir um erro feio de Java. O seu `GlobalExceptionHandler` deve capturar a violação no banco e devolver um JSON limpo (ex: `Status 400 Bad Request` com a mensagem "Chave de acesso já em uso").

---

## 🏗️ Marco 2: A Construção do Molde (A Regra de Negócio)
**Quando estiver pronto:** Toda a Fase 2.
*(Classes: PerfilPassaporte, AtividadeTemplate, TarefaTemplate, SubtarefaTemplate e suas camadas)*

### 🧪 O que você já pode testar no Postman:
1. **Criar a Vaga (O Perfil):**
   - Faça um `POST /api/templates/perfis` (Ex: "Desenvolvedor Júnior"). O sistema deve retornar o ID desse novo perfil (provavelmente ID `1`).
2. **Testar a Hierarquia de Chaves Estrangeiras:**
   - Faça um `POST /api/templates/atividades` enviando `perfilPassaporteId: 1`. A atividade deve ser criada com sucesso.
   - **O Teste de Fogo:** Tente fazer o mesmo `POST`, mas envie `perfilPassaporteId: 999` (um ID que não existe). O banco de dados (H2) deve bloquear a ação e o seu Exception Handler deve devolver `Status 404 Not Found` (Perfil não encontrado).
3. **Povoar a Árvore:**
   - Cadastre algumas Tarefas apontando para o ID da Atividade criada, preparando o terreno para a próxima fase.

---

## 🚀 Marco 3: A Mágica do Snapshot (Instanciando o Passaporte)
**Quando estiver pronto:** Toda a Fase 3.
*(Classes: Passaporte, Atividade, Tarefa e suas camadas operacionais)*

### 🧪 O que você já pode testar no Postman:
1. **O Gatilho Principal (Gerar Passaporte):**
   - Faça um `POST /api/passaportes` enviando no JSON o ID do Candidato (criado no Marco 1) e o ID do Perfil (criado no Marco 2).
   - O retorno deve indicar que o passaporte foi instanciado.
2. **Validar o "Clone" (Padrão Snapshot):**
   - Acesse o console do seu banco de dados H2 (`http://localhost:8080/h2-console`).
   - Faça um `SELECT * FROM tb_atividades`.
   - **O momento "Eureca":** Você verá que o Spring Boot leu os seus templates teóricos e automaticamente inseriu linhas novas na tabela de atividades reais, já com o status `ABERTA` e amarradas ao passaporte gerado. A inteligência do seu `PassaporteService` está funcionando!
3. **Listar o Painel do Candidato:**
   - Faça um `GET /api/atividades/passaporte/1`. Você deve receber um JSON limpo (graças ao `AtividadeResponseDTO`) listando exatamente o que o candidato precisa fazer, sem vazar dados das tabelas relacionadas.

---

## 🔐 Marco 4: A Prova Final (Validação e Auditoria)
**Quando estiver pronto:** Toda a Fase 4.
*(Classes: Artefato, CategoriaDocumento, LogStatus, ValidationController)*

### 🧪 O que você já pode testar no Postman:
1. **Simular o Upload de Arquivo:**
   - Faça um `POST /api/artefatos` enviando um JSON com `tarefaId: 1` e uma URL de imagem fictícia.
   - Verifique no banco se o `tokenAssinatura` foi gerado (um hash criptográfico) e salvo na linha correspondente da tabela `tb_artefatos`.
2. **A Prova de Governança (Reprovação):**
   - Faça um `PATCH /api/tarefas/1/rejeitar` enviando um JSON com o motivo: `"Documento enviado está ilegível"`.
3. **Validar a Trilha de Auditoria:**
   - Abra o banco H2 novamente e faça um `SELECT * FROM tb_log_status`.
   - A sua tabela deve conter um registro fresquinho provando que a Tarefa 1 foi rejeitada, exibindo exatamente a data, a hora, e a string "Documento enviado está ilegível". 
4. **O Teste da Máquina de Estados:**
   - Tente aprovar (`PATCH .../aprovar`) o passaporte inteiro se alguma tarefa interna ainda estiver `ABERTA` ou `INVALIDA`. O seu `ValidationService` deve interceptar e lançar um `Status 400 Bad Request` avisando que há pendências.

---

🎉 **Objetivo Concluído!** Se o seu código passar por todos esses 4 marcos no Postman, você acaba de desenvolver o back-end completo de um produto real e pronto para ser integrado a qualquer Front-end (React, Angular, etc).