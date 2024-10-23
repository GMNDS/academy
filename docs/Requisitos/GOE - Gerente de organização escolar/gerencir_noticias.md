## Gerenciar Notícias
### Usuários
- GOE

### Funcionalidades
- **Criar Comunicado**
- **Alterar Comunicado**
- **Apagar Comunicado**
- **Visualizar Comunicados**

---

### 1. Criar Comunicado

#### Fluxo Normal
1. O usuário abre o sistema acadêmico. <!-- Interface do usuário -->
2. O usuário faz login na conta de ADM ou Gestor. <!-- Interface do usuário -->
3. O usuário entra na aba de "Recursos". <!-- Interação do sistema -->
4. O usuário seleciona a opção "Notícias e Eventos". <!-- Interação do sistema -->
5. O usuário clica no ícone “+” para adicionar um novo comunicado. <!-- Interface do usuário -->
6. O usuário clica em “Adicionar Comunicado”. <!-- Interface do usuário -->
7. O usuário preenche as informações necessárias do comunicado (Título, assunto, destinatários, corpo do texto, etc.). <!-- Interface do usuário -->
8. O usuário confirma as informações. <!-- Interface do usuário -->
9. O usuário clica em "Postar Comunicado". <!-- Interação do sistema -->
10. O sistema valida as informações e publica o comunicado. <!-- Interação do sistema -->
11. O sistema exibe uma mensagem de confirmação "Comunicado publicado com sucesso"; <!-- Interação do sistema -->

#### Fluxo de Exceção
7.1. Caso falte alguma informação obrigatória (Título, corpo de texto, especificação dos destinatários), o sistema não permitirá a publicação e exibirá a mensagem: "Preencha todos os campos obrigatórios para prosseguir". <!-- Interação do sistema -->
10.1. Caso ocorra um erro ao publicar o comunicado, o sistema exibirá a mensagem "Erro ao publicar comunicado, tente novamente"; <!-- Interação do sistema -->

---

### 2. Alterar Comunicado

#### Fluxo Normal
1. O usuário abre o sistema acadêmico. <!-- Interface do usuário -->
2. O usuário faz login na conta de ADM ou Gestor. <!-- Interface do usuário -->
3. O usuário entra na aba de "Recursos". <!-- Interação do sistema -->
4. O usuário seleciona a opção "Notícias e Eventos". <!-- Interação do sistema -->
5. O usuário escolhe o comunicado que deseja alterar da lista de comunicados publicados. <!-- Interface do usuário -->
6. O sistema abre um painel com as informações do comunicado selecionado. <!-- Interação do sistema -->
7. O usuário realiza as alterações necessárias (Título, assunto, destinatários, corpo do texto). <!-- Interface do usuário -->
8. O usuário confirma as informações. <!-- Interface do usuário -->
9. O usuário clica em "Salvar Alterações". <!-- Interface do usuário -->
10. O sistema valida as informações e atualiza o comunicado. <!-- Interação do sistema -->
11. O sistema exibe uma mensagem de confirmação "Comunicado alterado com sucesso"; <!-- Interação do sistema -->

#### Fluxo de Exceção
5.1. Caso o comunicado não seja encontrado, o sistema deve exibir a mensagem: "Comunicado não encontrado". <!-- Interação do sistema -->
7.1. Caso falte alguma informação obrigatória (Título, corpo de texto, especificação dos destinatários), o sistema não permitirá a alteração e exibirá a mensagem: "Preencha todos os campos obrigatórios para prosseguir". <!-- Interação do sistema -->
10.1. Caso ocorra um erro ao salvar as alterações, o sistema exibirá uma mensagem "Erro ao alterar comunicado, tente novamente"; <!-- Interação do sistema -->

---

### 3. Apagar Comunicado

#### Fluxo Normal
1. O usuário abre o sistema acadêmico. <!-- Interface do usuário -->
2. O usuário faz login na conta de ADM ou Gestor. <!-- Interface do usuário -->
3. O usuário entra na aba de "Recursos". <!-- Interação do sistema -->
4. O usuário seleciona a opção "Notícias e Eventos". <!-- Interação do sistema -->
5. O usuário escolhe o comunicado que deseja apagar da lista de comunicados publicados. <!-- Interface do usuário -->
6. O sistema solicita confirmação da exclusão do comunicado. <!-- Interação do sistema -->
7. O usuário confirma a exclusão. <!-- Interface do usuário -->
8. O sistema remove o comunicado do banco de dados e exibe a mensagem "Comunicado apagado com sucesso"; <!-- Interação do sistema -->

#### Fluxo de Exceção
5.1. Caso o comunicado não seja encontrado, o sistema deve exibir a mensagem: "Comunicado não encontrado". <!-- Interação do sistema -->
8.1. Caso ocorra um erro ao apagar o comunicado, o sistema exibirá a mensagem "Erro ao apagar comunicado, tente novamente"; <!-- Interação do sistema -->

---

### 4. Visualizar Comunicado

#### Fluxo Normal
1. O usuário abre o sistema acadêmico. <!-- Interface do usuário -->
2. O usuário faz login na conta de ADM ou Gestor. <!-- Interface do usuário -->
3. O usuário entra na aba de "Recursos". <!-- Interação do sistema -->
4. O usuário seleciona a opção "Notícias e Eventos". <!-- Interação do sistema -->
5. O sistema exibe uma lista de todos os comunicados publicados, incluindo detalhes como título, assunto, destinatários e data de publicação. <!-- Interação do sistema -->
6. O usuário pode aplicar filtros para buscar comunicados específicos (por data, assunto, etc.). <!-- Interface do usuário -->
7. O sistema exibe os resultados filtrados conforme a busca. <!-- Interação do sistema -->

#### Fluxo de Exceção
5.1. Caso não haja comunicados publicados, o sistema deve exibir a mensagem "Nenhum comunicado encontrado"; <!-- Interação do sistema -->
6.1. Caso o usuário não tenha as permissões necessárias para visualizar comunicados, será exibida a mensagem: "Você não tem permissão para visualizar comunicados". <!-- Interação do sistema -->
