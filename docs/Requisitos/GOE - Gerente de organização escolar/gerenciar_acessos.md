## Gerenciar Acessos
### Usuários
- GOE
### Funcionalidades
- **Criar Acesso**
- **Alterar Acesso**
- **Apagar Acesso**
- **Visualizar Acessos**

---

### 1. Criar Acesso

#### Fluxo Normal
1. O usuário abre o sistema acadêmico. <!-- Interface do usuário -->
2. O usuário faz login na conta de ADM ou Gestor. <!-- Interface do usuário -->
3. O usuário entra na aba de "Gerenciar Acessos". <!-- Interação do sistema -->
4. O usuário clica em "Adicionar Novo Acesso". <!-- Interface do usuário -->
5. O usuário preenche as informações necessárias (Nome do usuário, e-mail, nível de acesso, etc.). <!-- Interface do usuário -->
6. O usuário confirma as informações. <!-- Interface do usuário -->
7. O usuário clica em "Salvar Acesso". <!-- Interação do sistema -->
8. O sistema valida as informações e cria o novo acesso. <!-- Interação do sistema -->
9. O sistema exibe uma mensagem de confirmação "Acesso criado com sucesso"; <!-- Interação do sistema -->

#### Fluxo de Exceção
5.1. Caso falte alguma informação obrigatória (Nome, e-mail, nível de acesso), o sistema não permitirá a criação e exibirá a mensagem: "Preencha todos os campos obrigatórios para prosseguir". <!-- Interação do sistema -->
8.1. Caso ocorra um erro ao criar o acesso, o sistema exibirá a mensagem "Erro ao criar acesso, tente novamente"; <!-- Interação do sistema -->

---

### 2. Alterar Acesso

#### Fluxo Normal
1. O usuário abre o sistema acadêmico. <!-- Interface do usuário -->
2. O usuário faz login na conta de ADM ou Gestor. <!-- Interface do usuário -->
3. O usuário entra na aba de "Gerenciar Acessos". <!-- Interação do sistema -->
4. O usuário escolhe o acesso que deseja alterar da lista de acessos. <!-- Interface do usuário -->
5. O sistema abre um painel com as informações do acesso selecionado. <!-- Interação do sistema -->
6. O usuário realiza as alterações necessárias (Nome, e-mail, nível de acesso). <!-- Interface do usuário -->
7. O usuário confirma as informações. <!-- Interface do usuário -->
8. O usuário clica em "Salvar Alterações". <!-- Interface do usuário -->
9. O sistema valida as informações e atualiza o acesso. <!-- Interação do sistema -->
10. O sistema exibe uma mensagem de confirmação "Acesso alterado com sucesso"; <!-- Interação do sistema -->

#### Fluxo de Exceção
4.1. Caso o acesso não seja encontrado, o sistema deve exibir a mensagem: "Acesso não encontrado". <!-- Interação do sistema -->
6.1. Caso falte alguma informação obrigatória (Nome, e-mail, nível de acesso), o sistema não permitirá a alteração e exibirá a mensagem: "Preencha todos os campos obrigatórios para prosseguir". <!-- Interação do sistema -->
9.1. Caso ocorra um erro ao salvar as alterações, o sistema exibirá uma mensagem "Erro ao alterar acesso, tente novamente"; <!-- Interação do sistema -->

---

### 3. Apagar Acesso

#### Fluxo Normal
1. O usuário abre o sistema acadêmico. <!-- Interface do usuário -->
2. O usuário faz login na conta de ADM ou Gestor. <!-- Interface do usuário -->
3. O usuário entra na aba de "Gerenciar Acessos". <!-- Interação do sistema -->
4. O usuário escolhe o acesso que deseja apagar da lista de acessos. <!-- Interface do usuário -->
5. O sistema solicita confirmação da exclusão do acesso. <!-- Interação do sistema -->
6. O usuário confirma a exclusão. <!-- Interface do usuário -->
7. O sistema remove o acesso do banco de dados e exibe a mensagem "Acesso apagado com sucesso"; <!-- Interação do sistema -->

#### Fluxo de Exceção
4.1. Caso o acesso não seja encontrado, o sistema deve exibir a mensagem: "Acesso não encontrado". <!-- Interação do sistema -->
7.1. Caso ocorra um erro ao apagar o acesso, o sistema exibirá a mensagem "Erro ao apagar acesso, tente novamente"; <!-- Interação do sistema -->

---

### 4. Visualizar Acessos

#### Fluxo Normal
1. O usuário abre o sistema acadêmico. <!-- Interface do usuário -->
2. O usuário faz login na conta de ADM ou Gestor. <!-- Interface do usuário -->
3. O usuário entra na aba de "Gerenciar Acessos". <!-- Interação do sistema -->
4. O sistema exibe uma lista de todos os acessos cadastrados, incluindo detalhes como nome, e-mail e nível de acesso. <!-- Interação do sistema -->
5. O usuário pode aplicar filtros para buscar acessos específicos (por nome, e-mail, etc.). <!-- Interface do usuário -->
6. O sistema exibe os resultados filtrados conforme a busca. <!-- Interação do sistema -->

#### Fluxo de Exceção
4.1. Caso não haja acessos cadastrados, o sistema deve exibir a mensagem "Nenhum acesso encontrado"; <!-- Interação do sistema -->
5.1. Caso o usuário não tenha as permissões necessárias para visualizar acessos, será exibida a mensagem: "Você não tem permissão para visualizar acessos". <!-- Interação do sistema -->
