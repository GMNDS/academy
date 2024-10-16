## Criar atividade aberta
### Usuários
- Professor

### Fluxo Normal
1. O professor abre o portal acadêmico. <!-- Interface do usuário -->
2. O professor faz o login no sistema. <!-- Interações do sistema -->
3. O professor seleciona o painel do professor. <!-- Interface do usuário -->
4. O professor seleciona a turma. <!-- Interface do usuário -->
5. O professor seleciona a opção de atividades. <!-- Interface do usuário -->
6. O professor seleciona a opção de criar atividade. <!-- Interface do usuário -->
7. O professor seleciona a opção de atividade aberta. <!-- Interface do usuário -->
8. Será aberto o editor embutido com a opção pré-definida de atividade. <!-- Interface do usuário -->
9. O professor digita a descrição da atividade. <!-- Interface do usuário -->
10. O professor seleciona a quantidade de pontos que a atividade vale. <!-- Interface do usuário -->
11. O professor seleciona a data de expiração da atividade (quando o aluno não poderá mais entregar). <!-- Interface do usuário -->
12. O professor seleciona a data de entrega da atividade (quando a atividade fica como atrasada). <!-- Interface do usuário -->
13. O professor seleciona o formato de arquivo dentro dos pré-definidos aceitos para o upload da atividade (ou se será feita no editor embutido). <!-- Interface do usuário -->
14. O professor seleciona a quantidade máxima de pessoas na atividade. <!-- Interface do usuário -->
15. O professor clica em salvar. <!-- Interações do sistema -->
16. Será enviado os dados para o servidor e adicionados ao banco de dados. <!-- Armazenamento de dados -->
17. O professor recebe uma confirmação de que a atividade foi criada. <!-- Interações do sistema -->

### Fluxo de Exceção
11. Se o professor não preencher a descrição ou a quantidade de pontos, aparecerá uma mensagem de erro informando que esses campos são obrigatórios. <!-- Interações do sistema -->
12. Se a data de expiração for anterior à data de entrega, aparecerá uma mensagem de erro informando que as datas estão inconsistentes. <!-- Interações do sistema -->
13. Se o servidor retornar um erro, aparecerá uma tela em vermelho informando o erro e pedindo para tentar novamente. <!-- Interações do sistema -->