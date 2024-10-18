## Gerenciar Matrículas
### Usuários
- Gestores

### Fluxo Normal
1. O Gestor abre o portal acadêmico. <!-- Interface do usuário -->
2. O Gestor realiza o login na plataforma. <!-- Interface do usuário -->
3. Após abrir a interface de gestor, será exibida a opção gerenciar matrículas. <!-- Interação do sistema -->
4. Serão exibidas as opções de modificar matrículas, novas matrículas e apagar matrículas. <!-- Interação do sistema -->
   4.1. Se o Gestor selecionar modificar matrículas, será exibida uma interface com todos os cursos, onde ele poderá escolher o curso, turno e matrícula desejada. <!-- Interação do sistema -->
   4.2. Se o Gestor selecionar novas matrículas, serão exibidas as opções de Nome, E-mail, Senha, Confirme a Senha, Data de Nascimento e um dropdown para o perfil do usuário (aluno, professor ou coordenador). <!-- Interface do usuário -->
   4.3. Se o Gestor selecionar apagar matrículas, será exibida uma interface com todos os cursos, onde ele poderá escolher o curso, turno e matrícula desejada. <!-- Interação do sistema -->
   4.4. Após o gestor selecionar apagar em algum usuário selecionado, será exibida a mensagem: "Você tem certeza que quer apagar o usuário? A ação não poderá ser revertida". <!-- Interação do sistema -->
5. Após o gestor fazer as modificações desejadas, a configuração é enviada ao banco de dados para a alteração. <!-- Armazenamento de dados -->

### Fluxo de Exceção
4.1-1. Caso não tenha sido criado nenhum curso ou pesquisado um curso inexistente, será exibida a mensagem: "Curso não encontrado ou inexistente". <!-- Interação do sistema -->
4.1-2. Caso o gestor não tenha as permissões necessárias para modificar um usuário, será exibida a mensagem: "Você não tem permissão para gerenciar usuários". <!-- Interação do sistema -->
4.1-3. Caso o gestor tenha permissões, será exibida uma janela de senha; o gestor colocará a senha de seu login e o usuário será modificado. <!-- Interação do sistema -->
4.2-1. Caso o gestor não tenha as permissões necessárias para criar um usuário, será exibida a mensagem: "Você não tem permissão para gerenciar usuários". <!-- Interação do sistema -->
4.2-2. Caso o gestor tenha permissões, será exibida uma janela de senha; o gestor colocará a senha de seu login e a tela de registros será aberta. <!-- Interação do sistema -->
4.3-1. Caso o gestor não tenha as permissões necessárias para apagar um usuário, será exibida a mensagem: "Você não tem permissão para gerenciar usuários". <!-- Interação do sistema -->
4.3-2. Caso o gestor tenha permissões, será exibida uma janela de senha; o gestor colocará a senha de seu login e o usuário será apagado. <!-- Interação do sistema -->
4.3-3. Caso o gestor selecione um usuário que seja outro gestor, coordenador ou professor, será exibida a mensagem: "Este usuário não pode ser apagado, entre em contato com sua instituição". <!-- Interação do sistema -->