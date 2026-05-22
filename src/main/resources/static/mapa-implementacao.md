# 🗺️ Plano de Execução do Backlog: Sistema Passaporte

Este é o roteiro de execução para cobrir 100% dos requisitos de negócio (User Stories - HS). A regra é: só avance para a próxima etapa (Step) quando a atual puder ser testada com sucesso no Postman.

---

## 🏗️ Fase 1: Fundação de Segurança e Identidade

### Step 1.1: Controle Rígido de Papéis
- [ ] Criar o Enum `PerfilUsuarioEnum` e a entidade `Usuario`.
- [ ] Garantir que o banco salve o Enum como String (`@Enumerated(EnumType.STRING)`).
- **🎯 Backlog Atingido:** - **[HSadm101]** Definir perfis de acesso.
  - **[HSadm104]** Segurança na segregação de funções (RH não vê laudos médicos).

### Step 1.2: O Acesso do Candidato
- [ ] Adicionar `chaveAcesso` na entidade `Usuario` com restrição `@Column(unique = true)`.
- [ ] Criar no Controller um ExceptionHandler que devolva erro legível se alguém tentar cadastrar chave duplicada.
- **🎯 Backlog Atingido:** - **[Glossário] / [HScd314]** Contratado acessa o passaporte por uma chave única e segura.

---

## 🎨 Fase 2: O Motor de Templates (Criação de Vagas pelo RH)

### Step 2.1: A Raiz do Cargo e a Proteção do Histórico
- [ ] Criar a entidade `PerfilPassaporte` com os campos `nome`, `ativo` e o vital `versao (Integer)`.
- [ ] Criar no `PerfilPassaporteService` a lógica: se o RH editar o molde, o sistema desativa a versão atual e cria uma linha nova (V2) no banco.
- **🎯 Backlog Atingido:** - **[HSrh201]** Criar o Passaporte (Molde base).
  - **[HSrh203]** Ativar/Desativar Molde.
  - **[HSrh202] / [HSrh207]** O histórico não pode ser alterado retroativamente (Padrão de Versionamento).

### Step 2.2: A Árvore de Execução
- [ ] Criar as entidades `AtividadeTemplate`, `TarefaTemplate` e `SubtarefaTemplate`.
- [ ] Implementar a restrição de chaves estrangeiras entre elas (`@ManyToOne`).
- [ ] Adicionar a coluna `ordem` para forçar a execução sequencial.
- **🎯 Backlog Atingido:** - **[HSrh204] / [HSrh205] / [HSrh206]** O sistema aceita Atividades, que têm Tarefas, que têm Subtarefas obrigatórias e ordenadas.

### Step 2.3: Delegação Abstrata
- [ ] Inserir o campo `perfilResponsavel` (baseado no `PerfilUsuarioEnum`) em cada Template criado no Step 2.2.
- **🎯 Backlog Atingido:** - **[HSrh209] / [HSrh211] / [HSrh213]** O RH define quem (qual departamento) deve executar ou validar cada etapa teórica.

### Step 2.4: Módulos Clonáveis
- [ ] Criar um método no Service que permita copiar blocos de `Atividades` de um Perfil antigo para um novo Perfil.
- **🎯 Backlog Atingido:** - **[HSrh214]** Criar componentes reutilizáveis.

---

## 🏃 Fase 3: A Mágica da Execução (Instanciar o Candidato)

### Step 3.1: O Nascimento do Passaporte Real
- [ ] Criar o endpoint `POST /api/passaportes` recebendo `candidato_id` e `perfil_modelo_id`.
- [ ] No Service, registrar a `dataCriacao` (para ancorar os prazos).
- **🎯 Backlog Atingido:** - **[HSrh301]** Associar o Passaporte ao candidato específico.

### Step 3.2: O Padrão de Cópia Isolada (Snapshot)
- [ ] No momento que o Passaporte é salvo, o Service deve ler as listas de Templates (Fase 2) e fazer `INSERT` nas tabelas reais `Atividade`, `Tarefa` e `Subtarefa`.
- [ ] Adicionar nestas tabelas reais o campo `responsavel_especifico_id` (vinculado a um ID de `Usuario`).
- **🎯 Backlog Atingido:** - **[HSrh302] / [HSrh303] / [HSrh304]** Diferente do Molde (que pede um setor), o processo em andamento permite delegar para um CPF/Funcionário específico.

### Step 3.3: O Motor de Regras (Máquina de Estados)
- [ ] Criar o `StatusFluxoEnum` (ABERTA, VALIDA, INVALIDA, CANCELADA) e atrelar às entidades reais.
- [ ] No Service, blindar o sistema: Uma Tarefa pai não pode receber "VALIDA" se uma Subtarefa filha estiver "ABERTA".
- **🎯 Backlog Atingido:** - **[HSrh312]** Sinalizar status do Passaporte bloqueando avanços ilícitos.

---

## 🛡️ Fase 4: Operação, Arquivos e Governança

### Step 4.1: O Cofre de Uploads
- [ ] Criar a entidade `Artefato` contendo `caminhoUrl`. 
- [ ] Criar endpoint de upload garantindo que o arquivo só é salvo no banco atrelado a uma `Tarefa` ou `Subtarefa` (Chave estrangeira não nula).
- **🎯 Backlog Atingido:** - **[HScd315] / [HScd316] / [HScd317]** Candidato vincula arquivos a tarefas específicas e o RH consegue listar tudo depois.

### Step 4.2: Assinatura Jurídica
- [ ] Ao aprovar um artefato, gerar no Service um Hash (Token Criptográfico) usando `AprovadorID + Timestamp + ArtefatoID` e salvar no campo `tokenAssinatura`.
- **🎯 Backlog Atingido:** - **[Glossário]** Requisito inegociável de Assinatura Eletrônica comprovável.

### Step 4.3: Otimização de Custos e Tempo (Reuso)
- [ ] Criar a tabela `CategoriaDocumento` para configurar se o arquivo expira.
- [ ] No Service, antes de cobrar o envio do candidato, checar se um documento válido da mesma categoria já existe e reaproveitar.
- **🎯 Backlog Atingido:** - **[HSrh215] / [HScd320]** Reaproveitar documentações base já enviadas.

### Step 4.4: O Tribunal da Auditoria (Reprovações)
- [ ] Criar a tabela blindada `LogStatus`.
- [ ] Criar o endpoint de Rejeição de Tarefa exigindo um DTO com texto explicativo. No Service, mudar a tarefa para "INVALIDA" e forçar o `INSERT` na tabela de Log (Com o usuário, a hora e o motivo).
- **🎯 Backlog Atingido:** - **[HSrh311]** Invalidar documentos justificando o motivo e gerando rastreabilidade imutável.

---

## 📊 Fase 5: Entrega de Valor Executivo (Visão Macro)

### Step 5.1: O Painel Gerencial Leve
- [ ] Criar os *Response DTOs* otimizados que condensam a quantidade de Tarefas e seus Status em uma resposta JSON limpa, sem arrastar relacionamentos pesados.
- **🎯 Backlog Atingido:** - **[HSrh313]** Dashboard para listar Passaportes e atividades totais.

### Step 5.2: O Motor Proativo (Agendamentos)
- [ ] Criar um Job no Spring Boot (`@Scheduled(cron = "0 0 0 * * ?")` - Rodando toda meia noite).
- [ ] Ele deve buscar no banco `Tarefas` com prazos próximos e gerar os alertas sistêmicos.
- **🎯 Backlog Atingido:** - **[HSrh318]** Sistema de Notificações ativas.