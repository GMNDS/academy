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
