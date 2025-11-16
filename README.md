## **Sobre o “Academy”**

Conheça o Academy, a nossa plataforma que permite aos estudantes gerenciar suas atividades, exames, provas, eventos acadêmicos e muito mais.

É muito fácil de usar, você adiciona seus dados iniciais como por exemplo sua faculdade e as matérias e então pode controlar suas faltas, notas, tarefas, entre outros, recebendo notificações programadas para que você não esqueça o que importa sem precisar ficar olhando o site da faculdade sempre.

> **CHEGA DE PERDER PRAZOS**

E estamos só começando, hoje você precisa colocar seus dados, amanhã eles serão coletados automaticamente e mais no futuro sua escola, faculdade ou curso poderá integrar nosso sistema diretamente, sem deixar de usar o que ele já usa.

não  vamos parar por aqui, não importa se você é anão ou não tem uma mão, o nosso site é feito para todos, sem exceção.

E você acha que acabou? Estudantes de vários cursos poderão se aprimorar trocando conhecimento em nosso fórum exclusivo do Academy. Não importa se você é de São Paulo ou Teresina, o nível de conhecimento só vai pra cima.

## Redis

- O projeto usa Redis para cache (`spring.cache.type=redis`). Se você vir mensagens de log sugerindo que o Spring Data Redis não conseguiu atribuir repositórios, isso pode ser porque você possui repositórios JPA (ex.: `JpaRepository`) e não pretende usar Redis como armazenamento chave-valor.
- Para evitar essas mensagens, a variação `spring.data.redis.repositories.enabled=false` está configurada em `application.properties` — assim o Redis é usado apenas como cache.