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