# Criar uma Prova
## Usuário

- Professor

## Etapas (Fluxo)

1. O Usuário abre o portal acadêmico;
2. O usuário realiza a autenticação no portal;
3. O usuário abre o "painel do professor";
4. O usuário selecionará a opção "Criar Prova" no menu de opções a sua esquerda;
5. No pop-up (tela de opção) que aparecerá no centro da página, haverá 3 opções: "Formulário de Prova"(a); "Criar Prova via Editor de Texto"(b); "Upload de um arquivo PDF/Word/Formulário"(c) e ele deverá escolher uma;
	a1. Caso o usuário escolha a opção "Formulário de Prova", abrirá uma nova janela;
	a2. O usuário escreve suas perguntas e alternativas no formulário e a nota de cada exercício;
	a3. O usuário, se quiser, pode selecionar algumas ferramentas de edição de texto e alterações na estrutura do formulário;
	a4. O usuário confirma a criação do formulário e seleciona concluir no campo inferior direito da página.
	b1. Ao selecionar "Criar Prova via Editor de Texto", abrirá uma nova janela de edição de texto;
	b2. Usuário escreve no editor de texto embutido;
	b3. O usuário confirma a edição e seleciona a opção "concluir Edição";
	c1. Ao selecionar "Upload de um arquivo PDF/Word/Formulário", abrirá uma nova janela;
	c2. O usuário envia seu arquivo de prova no campo para Upload;
	c3. O usuário clica em "Concluir Upload";
6. Ao concluir a edição/upload da prova, uma nova janela fllutuante que deve ser preenchido com a data de conclusão da prova, o total a receber de nota, o curso e a turma/módulo;
7. O usuário aperta em confirmar prova;
8. A prova é enviada para os alunos com acesso ao portal acadêmico do aluno e adicionada na base de dados;
9. É enviado uma notificação para os alunos da turma na aplicação móvel e Web.

## Fluxo de Exceção
### Exceção concluir ação

  6.1. Caso o usuário não preencha um ou mais campos (Nota, Curso e Turma), a ação de concluir a ação de cirar a prova não será cumprida, aparecerá um aviso "Não houve preenchimento dos campos obrigatórios" e os campos ficaram com suas bordas em vermelho;
	6.2. Caso o usuário não preehca corretamente os campos Nota, Curso e Turma devidamente com os dados necessários, a ação de criar a prova não será realizada e aparecerá uma notificação "Informe os valores de acordo com o campo";
	6.3 Não haverá opção para o usuário preencher mais valores do que é permitido em campo. Exemplo, caso ele tente informar no campo Nota um valor acima de 100, ele não será permitido e aparecerá uma notificação "Valor inválido. Forneca um correto".

### Exceção de Login

  2.1. Caso o usuário utilize o E-mail e/ou a senha incorreta, o login não será completado e será solicitado que o mesmo reinsira novamente seus dados;
	2.2. Caso o usuário não preencha os campos de login e senha, o mesmo não conseguira realizar o acesso, mesmo tentando efeturar a autenticação;
	2.3. Caso o usuário passe um valor no campo de Login diferente de um e-mail, como um código malicisoso (Tipo SQL Injection), o mesmo não irá concluir a autenticação e nem conseguirá preehcer o campo com estes valores.
