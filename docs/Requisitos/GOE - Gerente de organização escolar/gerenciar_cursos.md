## Gerenciar Cursos
### Usuários
- GOE

### Funcionalidades
- **Criar Curso**
- **Alterar Curso**
- **Deletar Curso**
- **Visualizar curso**

---

### 1. Criar Curso

#### Fluxo Normal
1. O usuário abre o sistema acadêmico; <!-- Interface do usuário -->
2. O usuário faz o login como ADM ou Gestor; <!-- Interface do usuário -->
3. O usuário entra na aba de "Gestão"; <!-- Interface do usuário -->
4. O usuário seleciona a opção "Cursos"; <!-- Interface do usuário -->
5. O usuário clica em "Adicionar Novo Curso"; <!-- Interface do usuário -->
6. O usuário preenche as informações necessárias do curso (nome, descrição, código, etc.); <!-- Interface do usuário -->
7. O usuário clica em "Salvar"; <!-- Interface do usuário -->
8. O sistema verifica se todos os campos obrigatórios foram preenchidos corretamente e cria o curso; <!-- Interação do sistema -->
9. O sistema exibe uma mensagem de confirmação "Curso criado com sucesso"; <!-- Interação do sistema -->

#### Fluxo de Exceção
6.1. Caso algum campo obrigatório não seja preenchido ou esteja com erro, o sistema deve alertar o usuário e destacar o campo que precisa de correção; <!-- Interação do sistema -->
8.1. Caso ocorra um erro na criação do curso, o sistema deve exibir uma mensagem "Erro ao criar curso, tente novamente"; <!-- Interação do sistema -->

---

### 2. Alterar Curso

#### Fluxo Normal
1. O usuário abre o sistema acadêmico; <!-- Interface do usuário -->
2. O usuário faz o login como ADM ou Gestor; <!-- Interface do usuário -->
3. O usuário entra na aba de "Recursos"; <!-- Interface do usuário -->
4. O usuário seleciona a opção "Cursos"; <!-- Interface do usuário -->
5. O usuário seleciona o curso que deseja alterar; <!-- Interface do usuário -->
6. O usuário clica em "Editar"; <!-- Interface do usuário -->
7. O usuário altera as informações desejadas do curso (nome, descrição, código, etc.); <!-- Interface do usuário -->
8. O usuário clica em "Salvar"; <!-- Interface do usuário -->
9. O sistema valida as alterações e atualiza o curso no banco de dados; <!-- Interação do sistema -->
10. O sistema exibe uma mensagem de confirmação "Curso alterado com sucesso"; <!-- Interação do sistema -->

#### Fluxo de Exceção
7.1. Caso algum campo obrigatório não seja preenchido ou esteja incorreto, o sistema deve alertar o usuário e destacar o campo com erro; <!-- Interação do sistema -->
9.1. Caso ocorra um erro ao salvar as alterações, o sistema deve exibir uma mensagem "Erro ao alterar curso, tente novamente"; <!-- Interação do sistema -->

---

### 3. Deletar Curso

#### Fluxo Normal
1. O usuário abre o sistema acadêmico; <!-- Interface do usuário -->
2. O usuário faz o login como ADM ou Gestor; <!-- Interface do usuário -->
3. O usuário entra na aba de "Recursos"; <!-- Interface do usuário -->
4. O usuário seleciona a opção "Cursos"; <!-- Interface do usuário -->
5. O usuário seleciona o curso que deseja deletar; <!-- Interface do usuário -->
6. O usuário clica em "Deletar"; <!-- Interface do usuário -->
7. O sistema solicita confirmação da exclusão do curso; <!-- Interação do sistema -->
8. O usuário confirma a exclusão; <!-- Interface do usuário -->
9. O sistema remove o curso do banco de dados e exibe uma mensagem de sucesso "Curso deletado com sucesso"; <!-- Interação do sistema -->

#### Fluxo de Exceção
7.1. Caso o curso possua turmas ou alunos matriculados, o sistema deve impedir a exclusão e exibir a mensagem "Este curso não pode ser deletado enquanto houver turmas ou alunos vinculados"; <!-- Interação do sistema -->
9.1. Caso ocorra um erro ao deletar o curso, o sistema deve exibir uma mensagem "Erro ao deletar curso, tente novamente"; <!-- Interação do sistema -->

---

### 4. Consultar Turmas e Alunos com Base no Curso e Semestre

#### Fluxo Normal
1. O usuário abre o sistema acadêmico; <!-- Interface do usuário -->
2. O usuário faz o login como ADM ou Gestor; <!-- Interface do usuário -->
3. O usuário entra na aba de "Recursos"; <!-- Interface do usuário -->
4. O usuário seleciona a opção "Cursos"; <!-- Interface do usuário -->
5. O usuário seleciona o curso que deseja consultar; <!-- Interface do usuário -->
6. O usuário seleciona o semestre desejado; <!-- Interface do usuário -->
7. O usuário seleciona a opção "Alunos"; <!-- Interface do usuário -->
8. O usuário pesquisa pelo aluno com base no nome ou disciplina; <!-- Interface do usuário -->
9. O sistema exibe a lista de alunos filtrada conforme o critério de pesquisa; <!-- Interação do sistema -->
10. O sistema exibe informações detalhadas dos alunos, incluindo nome, matrícula, status acadêmico (matriculado, trancado, etc.); <!-- Interação do sistema -->
11. O usuário visualiza as informações; <!-- Interface do usuário -->

#### Fluxo de Exceção
5.1. Caso o aluno tenha reprovado em alguma matéria, ele pode se encontrar em mais de um semestre distinto, e o sistema deve exibir uma mensagem de alerta indicando que o aluno foi encontrado em múltiplos semestres. O usuário poderá escolher em qual semestre deseja visualizar o aluno; <!-- Interação do sistema -->
7.1. Caso o aluno não esteja mais matriculado, tenha concluído ou trancado o curso, ele não aparecerá na pesquisa, pois o filtro padrão é com base no semestre atual. O sistema deve permitir que o usuário altere o filtro para incluir alunos com status acadêmico diferentes (ex. "Concluído", "Trancado"); <!-- Interação do sistema -->
9.1. Se nenhum aluno for encontrado com o critério de pesquisa, o sistema deve exibir uma mensagem informando "Nenhum aluno encontrado para os critérios informados"; <!-- Interação do sistema -->
