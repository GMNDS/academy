## Gerenciar Matrículas
### Usuários
- GOE

### Funcionalidades
- **Modificar Matrículas**
- **Criar Novas Matrículas**
- **Apagar Matrículas**
- **Visualizar Matrículas**

---

### 1. Modificar Matrículas

#### Fluxo Normal
1. O Gestor abre o portal acadêmico. <!-- Interface do usuário -->
2. O Gestor realiza o login na plataforma. <!-- Interface do usuário -->
3. Após abrir a interface de gestor, será exibida a opção gerenciar matrículas. <!-- Interação do sistema -->
4. O Gestor seleciona a opção "Modificar Matrículas"; <!-- Interface do usuário -->
5. Será exibida uma interface com todos os cursos, onde ele poderá escolher o curso, turno e matrícula desejada. <!-- Interação do sistema -->
6. O Gestor faz as modificações desejadas e clica em "Salvar"; <!-- Interface do usuário -->
7. O sistema valida as alterações e atualiza as informações no banco de dados. <!-- Interação do sistema -->
8. O sistema exibe uma mensagem de confirmação "Matrícula modificada com sucesso"; <!-- Interação do sistema -->

#### Fluxo de Exceção
5.1. Caso não tenha sido criado nenhum curso ou o curso selecionado seja inexistente, será exibida a mensagem: "Curso não encontrado ou inexistente". <!-- Interação do sistema -->
6.1. Caso o Gestor não tenha as permissões necessárias para modificar um usuário, será exibida a mensagem: "Você não tem permissão para gerenciar usuários". <!-- Interação do sistema -->
7.1. Caso ocorra um erro ao salvar as alterações, o sistema deve exibir uma mensagem "Erro ao modificar matrícula, tente novamente"; <!-- Interação do sistema -->

---

### 2. Criar Novas Matrículas

#### Fluxo Normal
1. O Gestor abre o portal acadêmico. <!-- Interface do usuário -->
2. O Gestor realiza o login na plataforma. <!-- Interface do usuário -->
3. Após abrir a interface de gestor, será exibida a opção gerenciar matrículas. <!-- Interação do sistema -->
4. O Gestor seleciona a opção "Novas Matrículas"; <!-- Interface do usuário -->
5. Serão exibidas as opções de Nome, E-mail, Senha, Confirme a Senha, Data de Nascimento e um dropdown para o perfil do usuário (aluno, professor ou coordenador). <!-- Interface do usuário -->
6. O Gestor preenche as informações necessárias e clica em "Salvar"; <!-- Interface do usuário -->
7. O sistema valida os dados e cria a nova matrícula no banco de dados. <!-- Interação do sistema -->
8. O sistema exibe uma mensagem de confirmação "Matrícula criada com sucesso"; <!-- Interação do sistema -->

#### Fluxo de Exceção
5.1. Caso o Gestor não tenha as permissões necessárias para criar um usuário, será exibida a mensagem: "Você não tem permissão para gerenciar usuários". <!-- Interação do sistema -->
6.1. Caso algum campo obrigatório não seja preenchido ou esteja incorreto, o sistema deve alertar o Gestor e destacar o campo que precisa de correção. <!-- Interação do sistema -->
7.1. Caso ocorra um erro ao criar a nova matrícula, o sistema deve exibir uma mensagem "Erro ao criar matrícula, tente novamente"; <!-- Interação do sistema -->

---

### 3. Apagar Matrículas

#### Fluxo Normal
1. O Gestor abre o portal acadêmico. <!-- Interface do usuário -->
2. O Gestor realiza o login na plataforma. <!-- Interface do usuário -->
3. Após abrir a interface de gestor, será exibida a opção gerenciar matrículas. <!-- Interação do sistema -->
4. O Gestor seleciona a opção "Apagar Matrículas"; <!-- Interface do usuário -->
5. Será exibida uma interface com todos os cursos, onde ele poderá escolher o curso, turno e matrícula desejada. <!-- Interação do sistema -->
6. Após selecionar um usuário, será exibida a mensagem: "Você tem certeza que quer apagar o usuário? A ação não poderá ser revertida"; <!-- Interação do sistema -->
7. O Gestor confirma a exclusão; <!-- Interface do usuário -->
8. O sistema remove o usuário do banco de dados e exibe uma mensagem de sucesso "Matrícula apagada com sucesso"; <!-- Interação do sistema -->

#### Fluxo de Exceção
5.1. Caso o Gestor não tenha as permissões necessárias para apagar um usuário, será exibida a mensagem: "Você não tem permissão para gerenciar usuários". <!-- Interação do sistema -->
6.1. Caso o Gestor selecione um usuário que seja outro gestor, coordenador ou professor, será exibida a mensagem: "Este usuário não pode ser apagado, entre em contato com sua instituição". <!-- Interação do sistema -->
8.1. Caso ocorra um erro ao apagar o usuário, o sistema deve exibir uma mensagem "Erro ao apagar matrícula, tente novamente"; <!-- Interação do sistema -->

---

### 4. Visualizar Matrículas

#### Fluxo Normal
1. O Gestor abre o portal acadêmico. <!-- Interface do usuário -->
2. O Gestor realiza o login na plataforma. <!-- Interface do usuário -->
3. Após abrir a interface de gestor, será exibida a opção gerenciar matrículas. <!-- Interação do sistema -->
4. O Gestor seleciona a opção "Visualizar Matrículas"; <!-- Interface do usuário -->
5. O sistema exibe uma lista de todas as matrículas cadastradas, incluindo detalhes como Nome, E-mail, Curso e Status. <!-- Interação do sistema -->
6. O Gestor pode aplicar filtros para buscar matrículas específicas (por curso, status, etc.); <!-- Interface do usuário -->
7. O sistema exibe os resultados filtrados conforme a busca. <!-- Interação do sistema -->
8. O Gestor visualiza as informações das matrículas. <!-- Interface do usuário -->

#### Fluxo de Exceção
5.1. Caso não haja matrículas cadastradas, o sistema deve exibir a mensagem "Nenhuma matrícula encontrada"; <!-- Interação do sistema -->
6.1. Caso o Gestor não tenha as permissões necessárias para visualizar matrículas, será exibida a mensagem: "Você não tem permissão para visualizar matrículas". <!-- Interação do sistema -->
