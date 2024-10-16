## Criar atividade múltipla escolha
### Usuários
- Professor

### Fluxo Normal
1. O professor abre o portal acadêmico. <!-- Interface do usuário -->
2. O professor faz o login no sistema. <!-- Interações do sistema -->
3. O professor seleciona o painel do professor. <!-- Interface do usuário -->
4. O professor seleciona a turma. <!-- Interface do usuário -->
5. O professor seleciona a opção de atividades. <!-- Interface do usuário -->
6. O professor seleciona a opção de criar atividade. <!-- Interface do usuário -->
7. O professor seleciona a opção de atividade múltipla escolha. <!-- Interface do usuário -->
8. Será aberto um formulário que contém:
   1. A descrição da atividade. <!-- Interface do usuário -->
   2. As escolhas possíveis. <!-- Interface do usuário -->
   3. Uma opção de próximo para adicionar novas escolhas. <!-- Interface do usuário -->
   4. Uma opção de enviar para lançar a atividade. <!-- Interações do sistema -->
   5. Uma opção de salvar rascunho para salvar mas não lançar. <!-- Interações do sistema -->
   6. Uma opção de cancelar a criação da atividade. <!-- Interface do usuário -->
9. O professor clica em confirmar envio da atividade. <!-- Interações do sistema -->
10. Será enviado os dados para o servidor e adicionados ao banco de dados. <!-- Armazenamento de dados -->
11. O professor recebe uma confirmação de que a atividade foi criada. <!-- Interações do sistema -->

### Fluxo de Exceção
11. Se o professor não preencher a descrição ou as escolhas, aparecerá uma mensagem de erro informando que esses campos são obrigatórios. <!-- Interações do sistema -->
12. Se o servidor retornar um erro, aparecerá uma tela em vermelho informando o erro e pedindo para tentar novamente. <!-- Interações do sistema -->