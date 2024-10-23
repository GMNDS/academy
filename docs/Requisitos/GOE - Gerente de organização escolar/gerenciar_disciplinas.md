## Gerenciar Disciplinas
### Usuários
- Administrador (ADM) ou Gestor

### Funcionalidades
- **Criar Disciplina**
- **Alterar Disciplina**
- **Deletar Disciplina**
- **Visualizar Disciplinas**

---

### 1. Criar Disciplina

#### Fluxo Normal
1. O usuário abre o sistema acadêmico; <!-- Interface do usuário -->
2. O usuário faz o login como ADM ou Gestor; <!-- Interface do usuário -->
3. O usuário entra na aba de "Recursos"; <!-- Interface do usuário -->
4. O usuário seleciona a opção "Cursos"; <!-- Interface do usuário -->
5. O usuário seleciona o curso que deseja consultar; <!-- Interface do usuário -->
6. O usuário seleciona a sessão "Disciplinas"; <!-- Interface do usuário -->
7. O usuário clica em "Adicionar Disciplina"; <!-- Interface do usuário -->
8. O usuário preenche as informações necessárias da disciplina (nome, código, etc.); <!-- Interface do usuário -->
9. O usuário clica em "Salvar"; <!-- Interface do usuário -->
10. O sistema valida as informações e cria a disciplina; <!-- Interação do sistema -->
11. O sistema exibe uma mensagem de confirmação "Disciplina criada com sucesso". <!-- Interação do sistema -->

#### Fluxo de Exceção
8.1. Caso algum campo obrigatório não seja preenchido corretamente, o sistema deve exibir uma mensagem "Campo obrigatório não preenchido", solicitando correção. <!-- Interação do sistema -->
10.1. Caso ocorra um erro ao criar a disciplina, o sistema deve exibir uma mensagem "Erro ao criar disciplina, tente novamente". <!-- Interação do sistema -->

---

### 2. Alterar Disciplina

#### Fluxo Normal
1. O usuário abre o sistema acadêmico; <!-- Interface do usuário -->
2. O usuário faz o login como ADM ou Gestor; <!-- Interface do usuário -->
3. O usuário entra na aba de "Recursos"; <!-- Interface do usuário -->
4. O usuário seleciona a opção "Cursos"; <!-- Interface do usuário -->
5. O usuário seleciona o curso que deseja consultar; <!-- Interface do usuário -->
6. O usuário seleciona a sessão "Disciplinas"; <!-- Interface do usuário -->
7. O usuário abre a disciplina específica; <!-- Interface do usuário -->
8. O usuário clica em "Modificar"; <!-- Interface do usuário -->
9. O usuário altera as informações necessárias, como nome da disciplina, código, professor responsável, etc.; <!-- Interface do usuário -->
10. O usuário clica em "Salvar"; <!-- Interface do usuário -->
11. O sistema valida as alterações e atualiza as informações da disciplina; <!-- Interação do sistema -->
12. O sistema exibe uma mensagem de confirmação "Disciplina alterada com sucesso". <!-- Interação do sistema -->

#### Fluxo de Exceção
9.1. Caso o professor a ser atribuído à disciplina não esteja no banco de dados, o sistema deve exibir uma mensagem "Informações não encontradas. Revise as informações ou cadastre o professor no sistema". <!-- Interação do sistema -->
11.1. Caso ocorra um erro ao salvar as alterações, o sistema deve exibir uma mensagem "Erro ao alterar disciplina, tente novamente". <!-- Interação do sistema -->

---

### 3. Deletar Disciplina

#### Fluxo Normal
1. O usuário abre o sistema acadêmico; <!-- Interface do usuário -->
2. O usuário faz o login como ADM ou Gestor; <!-- Interface do usuário -->
3. O usuário entra na aba de "Recursos"; <!-- Interface do usuário -->
4. O usuário seleciona a opção "Cursos"; <!-- Interface do usuário -->
5. O usuário seleciona o curso que deseja consultar; <!-- Interface do usuário -->
6. O usuário seleciona a sessão "Disciplinas"; <!-- Interface do usuário -->
7. O usuário abre a disciplina específica; <!-- Interface do usuário -->
8. O usuário clica em "Deletar"; <!-- Interface do usuário -->
9. O sistema solicita confirmação da exclusão da disciplina; <!-- Interação do sistema -->
10. O usuário confirma a exclusão; <!-- Interface do usuário -->
11. O sistema remove a disciplina e exibe uma mensagem de confirmação "Disciplina deletada com sucesso". <!-- Interação do sistema -->

#### Fluxo de Exceção
9.1. Caso a disciplina tenha alunos ou professores vinculados, o sistema deve exibir uma mensagem "Esta disciplina não pode ser deletada enquanto houver alunos ou professores vinculados". <!-- Interação do sistema -->
11.1. Caso ocorra um erro ao deletar a disciplina, o sistema deve exibir uma mensagem "Erro ao deletar disciplina, tente novamente". <!-- Interação do sistema -->

### Visualizar Disciplinas
#### Usuários
- Administrador (ADM) ou Gestor

#### Funcionalidades
- **Visualizar Disciplinas**

---

#### Fluxo Normal
1. O usuário abre o sistema acadêmico; <!-- Interface do usuário -->
2. O usuário faz o login como ADM ou Gestor; <!-- Interface do usuário -->
3. O usuário entra na aba de "Recursos"; <!-- Interface do usuário -->
4. O usuário seleciona a opção "Cursos"; <!-- Interface do usuário -->
5. O usuário seleciona o curso que deseja consultar; <!-- Interface do usuário -->
6. O usuário seleciona a sessão "Disciplinas"; <!-- Interface do usuário -->
7. O sistema exibe uma lista das disciplinas cadastradas para o curso; <!-- Interação do sistema -->
8. O usuário pode selecionar uma disciplina específica para visualizar detalhes como nome, código, professor responsável, carga horária, etc.; <!-- Interface do usuário -->
9. O sistema exibe os detalhes da disciplina selecionada. <!-- Interação do sistema -->

#### Fluxo de Exceção
7.1. Caso não haja disciplinas cadastradas para o curso, o sistema exibe uma mensagem "Nenhuma disciplina encontrada para este curso". <!-- Interação do sistema -->
9.1. Caso ocorra um erro ao carregar os detalhes da disciplina, o sistema exibe uma mensagem "Erro ao carregar os detalhes da disciplina, tente novamente". <!-- Interação do sistema -->