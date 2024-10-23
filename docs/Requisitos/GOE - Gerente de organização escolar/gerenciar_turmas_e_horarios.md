## Gerenciar Turmas e Horários
### Usuários
- GOE

### Funcionalidades
- **Criar Turma**
- **Alterar Turma**
- **Apagar Turma**
- **Visualizar Turmas**

---

### 1. Criar Turma

#### Fluxo Normal
1. O Gestor abre o portal acadêmico. <!-- Interface do usuário -->
2. O Gestor realiza o login na plataforma. <!-- Interface do usuário -->
3. Após abrir a interface de gestor, será exibida a opção "Gerenciar Turmas e Horários". <!-- Interação do sistema -->
4. O Gestor seleciona a opção "Criar Turma"; <!-- Interface do usuário -->
5. Será exibida uma interface onde o Gestor pode preencher as informações da nova turma (nome da turma, curso, turno, horário, etc.). <!-- Interface do usuário -->
6. O Gestor preenche os dados necessários e clica em "Salvar"; <!-- Interface do usuário -->
7. O sistema valida os dados e cria a nova turma no banco de dados. <!-- Interação do sistema -->
8. O sistema exibe uma mensagem de confirmação "Turma criada com sucesso"; <!-- Interação do sistema -->

#### Fluxo de Exceção
5.1. Caso o Gestor não tenha as permissões necessárias para criar uma turma, será exibida a mensagem: "Você não tem permissão para gerenciar turmas". <!-- Interação do sistema -->
6.1. Caso algum campo obrigatório não seja preenchido ou esteja incorreto, o sistema deve alertar o Gestor e destacar o campo que precisa de correção. <!-- Interação do sistema -->
8.1. Caso ocorra um erro ao criar a turma, o sistema deve exibir uma mensagem "Erro ao criar turma, tente novamente"; <!-- Interação do sistema -->

---

### 2. Alterar Turma

#### Fluxo Normal
1. O Gestor abre o portal acadêmico. <!-- Interface do usuário -->
2. O Gestor realiza o login na plataforma. <!-- Interface do usuário -->
3. Após abrir a interface de gestor, será exibida a opção "Gerenciar Turmas e Horários". <!-- Interação do sistema -->
4. O Gestor seleciona a opção "Alterar Turma"; <!-- Interface do usuário -->
5. O sistema exibe uma lista de todas as turmas cadastradas; <!-- Interação do sistema -->
6. O Gestor seleciona a turma que deseja alterar; <!-- Interface do usuário -->
7. O sistema abre um painel com as informações da turma selecionada; <!-- Interação do sistema -->
8. O Gestor realiza as alterações desejadas e clica em "Salvar"; <!-- Interface do usuário -->
9. O sistema valida as alterações e atualiza as informações no banco de dados. <!-- Interação do sistema -->
10. O sistema exibe uma mensagem de confirmação "Turma alterada com sucesso"; <!-- Interação do sistema -->

#### Fluxo de Exceção
5.1. Caso o Gestor não tenha as permissões necessárias para alterar uma turma, será exibida a mensagem: "Você não tem permissão para gerenciar turmas". <!-- Interação do sistema -->
8.1. Caso algum campo obrigatório não seja preenchido ou esteja incorreto, o sistema deve alertar o Gestor e destacar o campo que precisa de correção. <!-- Interação do sistema -->
10.1. Caso ocorra um erro ao alterar a turma, o sistema deve exibir uma mensagem "Erro ao alterar turma, tente novamente"; <!-- Interação do sistema -->

---

### 3. Apagar Turma

#### Fluxo Normal
1. O Gestor abre o portal acadêmico. <!-- Interface do usuário -->
2. O Gestor realiza o login na plataforma. <!-- Interface do usuário -->
3. Após abrir a interface de gestor, será exibida a opção "Gerenciar Turmas e Horários". <!-- Interação do sistema -->
4. O Gestor seleciona a opção "Apagar Turma"; <!-- Interface do usuário -->
5. O sistema exibe uma lista de todas as turmas cadastradas; <!-- Interação do sistema -->
6. O Gestor seleciona a turma que deseja apagar; <!-- Interface do usuário -->
7. O sistema solicita confirmação da exclusão; <!-- Interação do sistema -->
8. O Gestor confirma a exclusão; <!-- Interface do usuário -->
9. O sistema remove a turma do banco de dados e exibe uma mensagem de sucesso "Turma apagada com sucesso"; <!-- Interação do sistema -->

#### Fluxo de Exceção
5.1. Caso o Gestor não tenha as permissões necessárias para apagar uma turma, será exibida a mensagem: "Você não tem permissão para gerenciar turmas". <!-- Interação do sistema -->
8.1. Caso ocorra um erro ao apagar a turma, o sistema deve exibir uma mensagem "Erro ao apagar turma, tente novamente"; <!-- Interação do sistema -->

---

### 4. Visualizar Turmas

#### Fluxo Normal
1. O Gestor abre o portal acadêmico. <!-- Interface do usuário -->
2. O Gestor realiza o login na plataforma. <!-- Interface do usuário -->
3. Após abrir a interface de gestor, será exibida a opção "Gerenciar Turmas e Horários". <!-- Interação do sistema -->
4. O Gestor seleciona a opção "Visualizar Turmas"; <!-- Interface do usuário -->
5. O sistema exibe uma lista de todas as turmas cadastradas, incluindo detalhes como Nome da Turma, Curso, Turno e Horário. <!-- Interação do sistema -->
6. O Gestor pode aplicar filtros para buscar turmas específicas (por curso, horário, etc.); <!-- Interface do usuário -->
7. O sistema exibe os resultados filtrados conforme a busca. <!-- Interação do sistema -->
8. O Gestor visualiza as informações das turmas. <!-- Interface do usuário -->

#### Fluxo de Exceção
5.1. Caso não haja turmas cadastradas, o sistema deve exibir a mensagem "Nenhuma turma encontrada"; <!-- Interação do sistema -->
6.1. Caso o Gestor não tenha as permissões necessárias para visualizar turmas, será exibida a mensagem: "Você não tem permissão para visualizar turmas". <!-- Interação do sistema -->
