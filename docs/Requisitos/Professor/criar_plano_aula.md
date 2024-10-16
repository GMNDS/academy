## Criar plano de aula

### Usuários
- Professor
### Fluxo normal
1. O Professor abre a portal acadêmico [sistema]
(home.md)
2. O Professor fará o [login](autenticacao.md) no sistema
3. O professor seleciona o painel do professor
4. O professor seleciona a turma
5. O professor seleciona a opção "PLANO DE AULAS"
6. Será aberto um calendário com opções de adicionar aulas e remover aulas.
7. O professor seleciona uma data
8. Será aberto um painel com aulas já salvas com nome e horas, opção de editar e criar aulas em um menu a direita.
   1. Se ele clicar em criar abrirá um painel onde ele poderá colocar uma descrição breve da atividade, e opcionalmente o horário
   2. Se ele clicar em editar abrirá um painel onde ele poderá editar a descrição e horário
   3. Se ele clicar em remover abrirá um campo de confirmação para confirmar a exclusão, clicando em sim será excluído, clicando em não a operação será cancelada
   4.  O professor  poderá selecionar uma aula e irá decidir se arrasta o bloco da aula ou apenas ajusta o horário para encaixar no calendário, logo após arrastar ou ajustar o horário, o bloco aparece automaticamente no calendário.
9.  O professor clica em enviar 
10. Dosdos enviados para o servidor e adicionados no banco de dados
11. É enviado um webhook que gera uma notificação no aplicativo e modificado no banco de dados de false para true uma tabela de envio de webhook
## Fluxo de exceçao
   
8. 
   1.
      - Limite de 280 caracteres
      - Aulas não podem ter o mesmo nome da outra
      - Não pode colocar dois blocos de aulas no mesmo horário.
   1. 
      - Limite de 280 caracteres
      - Aulas não podem ter o mesmo nome da outra
      - Não pode colocar dois blocos de aulas no mesmo horário.
   2. 
   3. 
      - Aulas não podem ter o mesmo nome de outra
      - Não pode colocar dois blocos de aulas no mesmo horário.
      - Se a aula for adicionada a um horário anterior vai aparecer um campo de confirmação
9. Se o professor enviar sem adicionar o campo com a descrição receberá uma mensagem de erro informando que o campo é obrigatório.
10. 
    1.  Se a resposta que o servidor devolver for um erro é informada uma mensagem em vermelho informando que houve um erro e para enviar novamente
    2.  Se a mensagem já tiver no banco de dados verifica-se se a tabela de envio de webhook está como true se estiver é informado uma mensagem em veemelho informando que a mensagem já foi enviada
11. Se a resposta do webhook for um erro é informada uma mensagem em vermelho informando que houve um erro e para enviar a notificação e fazer o envio novamente
 

- Nas configuracoes do aluno ter opcao de ativar ou desativar notificacoes