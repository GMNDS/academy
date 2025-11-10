### Jornada do Usuário

1. **Criação da Conta (Cadastro Inicial)**
    - Usuário clica em registrar
    - Preenche instituição: Nome, Sigla e Tipo(Faculdade, Ensino Médio, Curso ou outro)
        - Salva esse dado na tabela de instituição, se já existir, utiliza a instituição já existente
    - Preenche curso: Nome, semestres totais, semestre atual, categoria(opcional), frequência semanal(1 dia/ semana)
        - Vincula esse dado a instituição e salva na tabela de curso
    - Preenche RA (opcional)
    - Preenche nome completo
    - Adiciona foto de perfil(opcional)
    - Preenche e-mail
    - Preenche senha
2. **Login**
    - Efetua login com e-mail e senha
    - o sistema gera o token JWT
    - Todas próximas requisições usam esse código
3. **Adicionar Disciplinas**
    - Na seção disciplinas clica no botão (+)
    - Preenche nome da disciplina
    - Preenche código único da disciplina
    - Preenche quantidade total de aulas no semestre
    - Adiciona professor (opcional)
    - Vincula a disciplina ao curso do estudante
    - Salva na tabela de disciplinas (subjects)
4. **Gerenciar Atividades**
    - Na seção de atividades clica no botão (+)
    - Seleciona a disciplina
    - Preenche título da atividade
    - Preenche descrição (opcional)
    - Define data de entrega
      - Salva na tabela de atividades (tasks)
      - Sistema registra data de conclusão automaticamente
      - Marca como concluída quando o estudante finaliza a atividade
4. **Gerenciar Provas**
    - Na seção de provas clica no botão (+)
    - Seleciona a disciplina
    - Define data da prova
    - Define tipo da prova (P1, P2, P3, T, etc.)
      - Salva na tabela de provas (exams)
6. **Gerenciar Faltas**
    - Na seção de faltas/frequência
    - Seleciona a disciplina
    - Sistema mostra total de aulas da disciplina
    - Registra número de presenças
    - Sistema calcula automaticamente a porcentagem de presença
    - Exibe alertas se a frequência estiver abaixo do mínimo exigido
    - Dados salvos na tabela de faltas (absences)
7. **Gerenciar Notas**
    - Na seção de notas
    - Seleciona a disciplina
    - Adiciona notas para diferentes avaliações (P1, P2, trabalhos, etc.)
    - Sistema calcula média ponderada automaticamente
    - Dados salvos na tabela de notas (grades)
