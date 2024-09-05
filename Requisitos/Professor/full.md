## Lançar notas
### Usuários
- Professor

### Fluxo Normal 
1.	O professor abre o portal acadêmico;
2.	O professor faz o seu login no portal acadêmico;
3.	O professor acessa o painel do professor;
4.	O professor abre a página de Atividades/provas;
5.	O professor seleciona a turma no menu do centro;
6.	Será aberto um painel com todas atividades enviadas pela turma.
7. O professor seleciona a atividade/prova para visualização
8. Na visualização terá os integrantes e se ele tinha presença no dia do lançamento da atividade
9. O professor atribui a nota para a atividade/prova.
10. O professor clica em enviar;
11. Os dados são enviados para o servidor e adicionados no banco de dados
12. O professor recebe a confirmação do lançamento da nota. 


### Fluxo de Exceção
9. Na entrada de nota somente será permitida a nota definida no sistema (0 a 10) por exemplo.
10. Se o professor não preencheu a nota receberá um erro informando que a nota deverá ser preenchida
11. Se o servidor devolver algum erro será será recebida uma mensagem com o erro e solicitado para efetuar o envio novamente

## Lançar Faltas
### Usuários
- Professor

### Fluxo Normal 
1.	O professor abre o portal acadêmico;
2.	O professor faz o seu login no portal acadêmico;
3.	O professor acessa o painel de docente;
4.	O professor abre a página de lançamento de faltas;
5.	O professor seleciona a turma no menu do centro;
6.	O professor seleciona a data;
7.	O professor seleciona a quantidade de faltas de cada aluno;
8.	O professor clica em enviar;
9.	O professor recebe a confirmação do lançamento de faltas. 


### Fluxo de Exceção
8.	Se o professor não selecionar a quantidade de faltas uma mensagem de erro é gerada na tela, informando que ele deve preencher todos os campos.
9.	Se houver um erro no servidor durante o lançamento das faltas, uma mensagem de erro será exibida para o usuário.

## Criar atividades
### Usuários
  - Professor
### Fluxo Normal

1. O professor abre o portal acadêmico
2. O professor fará o [login](../Login/README.md) no sistema 
3. O professor seleciona o painel do professor
4. O professor selecionar a turma
5. O professor seleciona a opção de atividades
6. O professor selecionar a opção de criar atividade
7. Será aberta uma opção para o professor selecionar se será uma atividade aberta ou multipla escolha
8. 
   1. Se o professor selecionar a opçao de multipla ele seguirá por [multipla escolha](multipla_escolha.md). 
   2. Se ele seleciona a opcao de atividade aberta seguir por [atividade aberta](atividade_aberta.md)
9.  Aparecerá um resumo com as informaçoes da atividade
10. Clicar em confirmar envio da atividade ou editar
11. Será enviado os dados para o servidor e adicionado ao banco de dados 

- A data de ínicio da atividade deve ser automática
### Fluxo de exceção
11. Se houver um erro na resposta do servidor aparecerá uma tela em vermelho informando o erro e pedindo para tentar novamente

## Adicionar feedback a um aluno

### Usuários
- Professor

### Fluxo Normal
1. Professor(a) abre o portal acadêmico.
2. Professor(a) faz o seu login na plataforma. 
3. Professor(a) abra painel de professor.                             
4. Abrir gestão de alunos.
5. Escolher a sala do aluno específico.
6. Professor(a) seleciona o aluno.  
7. Professor(a) clica na opção "anotações e feedback"
8. Professor(a) escreve anotação/feedback sobre aluno. 
9. Professor(a) escolhe se quer mante-la privada ou compartilhar com o aluno.
   1.  Caso o/a professor(a) decida compartilhar a anotação do aluno com o próprio, clicar em salvar, seguido por compartilhar com aluno.
   2. Caso não [de tal forma deixando a anotação exclusíva para sí], clicar apenas em salvar.
### Fluxo De exceção
8. Limite de caractéres (500), caso excedido a seguinte mensagem aparecerá "limite de caractéres excedido" 
9. 
   1.  Caso o aluno tenha optado por não receber feedbacks essa opção não estará disponível 
   2.  Caso não preencha a área de anotação do estudante, aparecerá uma mensagem "Para concluir essa ação é necessario preencher o espaço acima" [Retorna ao passo 8](#fluxo-normal)


## Comunicar imprevisto
### Usuários
  - Professor
### Fluxo Normal
1. Professor abre portal acadêmico
2. Professor se [autentica](autenticacao.md) no portal escolar
3. Professor abre o painel do professor
4. Professor clica em comunicar imprevisto no menu a esquerda
5. É aberto um editor de texto embutido para o professor comunicar o imprevisto
6. Professor escreve o comunicado
7. Professor seleciona a turma no menu a direita
8. Professor seleciona a aula no menu a direita
9. Professor clica em enviar
10. Mensagem é enviada para o servidor e adicionada no banco de dados
11. É enviado um webhook que gera uma notificação no aplicativo e modificado no banco de dados de false para true uma tabela de envio de webhook
### Fluxo de exceção
9. Se o professor clicar em enviar sem algum dos campos preenchidos é gerada uma mensagem em vermelho informando que ele deve preencher todos os campos corretamente.
10. 
    1.  Se a resposta que o servidor devolver for um erro é informada uma mensagem em vermelho informando que houve um erro e para enviar novamente
    2.  Se a mensagem já tiver no banco de dados verifica-se se a tabela de envio de webhook está como true se estiver é informado uma mensagem em veemelho informando que a mensagem já foi enviada
11. Se a resposta do webhook for um erro é informada uma mensagem em vermelho informando que houve um erro e para enviar a notificação e fazer o envio novamente

## Adicionar Conteúdo

### Usuários:
- Professor

### Fluxo Normal:
1. Professor abre o portal acadêmico;
2. Professor faz o seu login na plataforma;
3. Professor abre painel do professor;
4. Professor clica em adicionar conteúdo;
5. Abre a página referente a adição de conteúdo;
6. Professor escreve título do conteúdo no editor de texto destinado ao título;
7. Professor escreve o conteúdo em outro editor de texto destinado ao próprio conteúdo, com possibilidade de anexação de imagem, áudio ou vídeos;
8. Professor seleciona a turma no menu a direita;
9. Professor seleciona a aula no menu a direita;
10. Professor clica em enviar;
11. Os dados são enviados para o servidor e enviados para o banco de dados
12. É enviado um webhook que gera uma notificação no aplicativo.

### Fluxo de exceção:
10. Se o professor clicar em enviar sem algum dos campos preenchidos é gerada
uma mensagem em vermelho informando que ele deve preencher todos os
campos corretamente;
11. 
    1. Se a resposta que o servidor devolver for um erro é informada uma mensagem em vermelho informando que houve um erro e envia a sugestão de enviar novamente
    2. Se mensagem já tiver no banco de dados, verifica-se se a tabela de enviode webhook está como true, se estiver é informando uma mensagem em
vermelho informando que a mensagem já foi enviada
12. Se a reposta do webhook for um erro é informada uma mensagem em
vermelho, informando que houve um erro e para enviar a notificação e fazer o envio novamente.
