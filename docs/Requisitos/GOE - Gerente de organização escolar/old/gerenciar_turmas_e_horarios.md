## Requisito 3: Gerenciar Turmas e Horários
### Usuários
- Gestores

### Fluxo Normal
1. O Gestor abre o portal acadêmico. <!-- Interface do usuário -->
2. O Gestor realiza o login na plataforma. <!-- Interface do usuário -->
3. Após abrir a interface de gestor, será exibida a opção Gerenciar Turmas e Horários. <!-- Interação do sistema -->
4. Após o gestor clicar, será exibida uma interface com todas as turmas e horários. <!-- Interação do sistema -->
5. O gestor poderá usar um filtro para procurar a turma e horário desejado. <!-- Interface do usuário -->
6. O gestor clicará na turma desejada e será aberto outro painel para realizar as modificações. <!-- Interação do sistema -->

### Fluxo de Exceção
5.1. Caso o gestor selecione um filtro inexistente, será exibida a mensagem: "Curso não encontrado ou inexistente". <!-- Interação do sistema -->
6.1. Caso o gestor não tenha as permissões necessárias para acessar determinada turma, será exibida a mensagem: "Você não tem permissão para gerenciar turmas". <!-- Interação do sistema -->
6.2. Caso o gestor tenha permissões, será exibida uma janela de senha; o gestor colocará a senha de seu login e o painel será aberto. <!-- Interação do sistema -->