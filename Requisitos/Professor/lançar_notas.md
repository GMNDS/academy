## Lançar Faltas
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