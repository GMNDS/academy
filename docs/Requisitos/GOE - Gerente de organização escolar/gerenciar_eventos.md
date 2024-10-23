## Gerenciar Eventos
### Usuários
- GOE

### Funcionalidades
- **Criar Evento**
- **Alterar Evento**
- **Apagar Evento**
- **Visualizar Evento**

---

### 1. Criar Evento

#### Fluxo Normal
1. O usuário efetua login com autorização cabível (Administração ou Diretoria). <!-- Interface do usuário -->
2. O usuário acessa o aplicativo. <!-- Interface do usuário -->
3. O usuário seleciona a opção "Eventos". <!-- Interação do sistema -->
4. O usuário clica em "Adicionar Evento". <!-- Interface do usuário -->
5. O usuário escreve o título do novo evento. <!-- Interface do usuário -->
6. O usuário descreve o evento, podendo adicionar anexos (imagens, vídeos, etc.). <!-- Interface do usuário -->
7. O usuário seleciona o público que poderá visualizar o novo evento. <!-- Interface do usuário -->
8. O usuário clica em "Publicar". <!-- Interação do sistema -->
9. O sistema valida os dados e publica o evento. <!-- Interação do sistema -->
10. O sistema exibe uma mensagem de confirmação "Evento publicado com sucesso"; <!-- Interação do sistema -->

#### Fluxo de Exceção
5.1. Caso o campo de título esteja em branco, o sistema não permitirá a publicação e exibirá a mensagem: "O campo de título é obrigatório". <!-- Interação do sistema -->
6.1. Caso o campo de descrição esteja em branco, o sistema não permitirá a publicação e exibirá a mensagem: "O campo de descrição é obrigatório". <!-- Interação do sistema -->
7.1. Caso não seja selecionado o público, o sistema não permitirá a publicação e exibirá a mensagem: "É necessário selecionar o público que poderá visualizar o evento". <!-- Interação do sistema -->
9.1. Caso ocorra um erro ao publicar o evento, o sistema exibirá a mensagem "Erro ao publicar evento, tente novamente"; <!-- Interação do sistema -->

---

### 2. Alterar Evento

#### Fluxo Normal
1. O usuário efetua login com autorização cabível (Administração ou Diretoria). <!-- Interface do usuário -->
2. O usuário acessa o aplicativo. <!-- Interface do usuário -->
3. O usuário seleciona a opção "Eventos". <!-- Interação do sistema -->
4. O usuário escolhe o evento que deseja alterar da lista de eventos publicados. <!-- Interface do usuário -->
5. O sistema abre um painel com as informações do evento selecionado. <!-- Interação do sistema -->
6. O usuário realiza as alterações desejadas (título, descrição, anexos, público). <!-- Interface do usuário -->
7. O usuário clica em "Salvar Alterações". <!-- Interface do usuário -->
8. O sistema valida as informações e atualiza o evento. <!-- Interação do sistema -->
9. O sistema exibe uma mensagem de confirmação "Evento alterado com sucesso"; <!-- Interação do sistema -->

#### Fluxo de Exceção
5.1. Caso o evento não seja encontrado, o sistema deve exibir a mensagem: "Evento não encontrado". <!-- Interação do sistema -->
6.1. Caso o campo de título esteja em branco, o sistema não permitirá a alteração e exibirá a mensagem: "O campo de título é obrigatório". <!-- Interação do sistema -->
7.1. Caso o campo de descrição esteja em branco, o sistema não permitirá a alteração e exibirá a mensagem: "O campo de descrição é obrigatório". <!-- Interação do sistema -->
8.1. Caso ocorra um erro ao salvar as alterações, o sistema exibirá uma mensagem "Erro ao alterar evento, tente novamente"; <!-- Interação do sistema -->

---

### 3. Apagar Evento

#### Fluxo Normal
1. O usuário efetua login com autorização cabível (Administração ou Diretoria). <!-- Interface do usuário -->
2. O usuário acessa o aplicativo. <!-- Interface do usuário -->
3. O usuário seleciona a opção "Eventos". <!-- Interação do sistema -->
4. O usuário escolhe o evento que deseja apagar da lista de eventos publicados. <!-- Interface do usuário -->
5. O sistema solicita confirmação da exclusão do evento. <!-- Interação do sistema -->
6. O usuário confirma a exclusão. <!-- Interface do usuário -->
7. O sistema remove o evento do banco de dados e exibe a mensagem "Evento apagado com sucesso"; <!-- Interação do sistema -->

#### Fluxo de Exceção
5.1. Caso o evento não seja encontrado, o sistema deve exibir a mensagem: "Evento não encontrado". <!-- Interação do sistema -->
7.1. Caso ocorra um erro ao apagar o evento, o sistema exibirá a mensagem "Erro ao apagar evento, tente novamente"; <!-- Interação do sistema -->

---

### 4. Visualizar Evento

#### Fluxo Normal
1. O usuário efetua login com autorização cabível (Administração ou Diretoria). <!-- Interface do usuário -->
2. O usuário acessa o aplicativo. <!-- Interface do usuário -->
3. O usuário seleciona a opção "Eventos". <!-- Interação do sistema -->
4. O sistema exibe uma lista de todos os eventos publicados, incluindo detalhes como título, descrição, público e anexos. <!-- Interação do sistema -->
5. O usuário pode aplicar filtros para buscar eventos específicos (por data, público, etc.). <!-- Interface do usuário -->
6. O sistema exibe os resultados filtrados conforme a busca. <!-- Interação do sistema -->

#### Fluxo de Exceção
4.1. Caso não haja eventos publicados, o sistema deve exibir a mensagem "Nenhum evento encontrado"; <!-- Interação do sistema -->
5.1. Caso o usuário não tenha as permissões necessárias para visualizar eventos, será exibida a mensagem: "Você não tem permissão para visualizar eventos". <!-- Interação do sistema -->
