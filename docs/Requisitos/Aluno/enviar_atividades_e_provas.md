## Enviar atividades e provas
### Usuários
- Aluno

### Fluxo Normal
1. Aluno acessa o aplicativo/site academy e realiza sua autenticação na tela de Login, informando seu e-mail e senha; <!-- Interface do usuário -->
2. Ao logar, o usuário é encaminhado para a página inicial e, para acessar a sessão de envio de atividades e provas, clica no botão com o ícone de nome “Atividades” na parte inferior da tela; <!-- Interação do sistema -->
3. Caso já esteja logado, tem duas formas de acessar as “Atividades”, tanto pelo ícone no menu na parte inferior da página ou indo para as “Configurações” e selecionando a opção “Atividades”; <!-- Interface do usuário -->
4. Acessando a tela de atividades, o usuário deve encontrar onde está a atividade lançada pelo docente, ou na aba “Pendente” ou na aba “Atrasado”; <!-- Interação do sistema -->
5. Ao ir em uma das abas, aparecerão na tela as atividades que Aluno tem pendente. Clicando/tocando em cima delas, poderá adicionar a atividade via anexo e enviá-la para correção do professor responsável; <!-- Interação do sistema -->
6. Ao clicar, abrirá uma nova janela mostrando os detalhes da atividade, como data de entrega, data de vencimento, matéria e docente responsável da atividade; <!-- Interação do sistema -->
7. Abaixo das informações da atividade estará um link solicitando o envio de um anexo; clicar e escolher o anexo necessário ou deixar vazio caso a atividade não necessite de anexo; <!-- Interface do usuário -->
8. Para continuar o envio da atividade, deve-se ir até o canto inferior da tela no botão “Enviar atividade”. Caso não tenha anexo, aparecerá uma mensagem “Não há anexo no envio, deseja mesmo enviar?” colocando a opção de continuar ou não o envio da atividade; caso queira, informando que sim, a atividade é enviada e ela fica registrada na aba “Concluída”. <!-- Interação do sistema -->

### Fluxo de Exceção
1.1. Caso Aluno informe e-mail e/ou senha incorreta, a autenticação não será realizada e aparecerá uma mensagem pop-up informando “Senha e/ou e-mail incorreta. Favor, tente novamente. Caso persista, solicite o seu reset de senha ou criação de usuário”; <!-- Interação do sistema -->
1.2. Caso Aluno não informe nada no login ou deixe um dos inputs sem entrada, não será autenticado e aparecerá uma mensagem pop-up informando “Preencha todos os campos para realizar o Login!”; <!-- Interação do sistema -->
8.1. Caso Aluno não indique um anexo e a atividade necessite de um, a aplicação fará uma validação. Se for necessário, ao clicar no botão “Enviar atividade”, não irá continuar e aparecerá uma mensagem informando: “O Anexo é obrigatório para esta atividade. Favor anexar o arquivo solicitado e depois tentar novamente enviar a atividade.” Caso não, aparecerá uma mensagem de confirmação se deseja enviar a atividade sem anexo e, se sim, é enviado. <!-- Interação do sistema -->