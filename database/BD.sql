CREATE DATABASE academy_v1
    WITH 
    ENCODING = 'UTF8'
    LC_COLLATE = 'Portuguese_Brazil.1252'
    LC_CTYPE = 'Portuguese_Brazil.1252';

-- Conecte-se ao banco antes de criar as tabelas
\c academy_v1;

-- ========================
-- TABELA: Instituição
-- ========================
CREATE TABLE instituicao (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    endereco VARCHAR(255) NOT NULL,
    cnpj VARCHAR(14) UNIQUE
);

-- ========================
-- TABELA: Curso
-- ========================
CREATE TABLE curso (
    id SERIAL PRIMARY KEY,
    nome_curso VARCHAR(255) NOT NULL,
    categoria VARCHAR(100),
    duracao INT,               -- duração em semestres
    frequencia_minima INT,     -- percentual mínimo de presença exigido
    instituicao_id INT REFERENCES instituicao(id)
);

-- ========================
-- TABELA: Usuário
-- ========================
CREATE TABLE usuario (
    ra SERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    foto TEXT,
    instituicao_id INT REFERENCES instituicao(id),
    curso_id INT REFERENCES curso(id),
    semestre INT,
    nota_media DECIMAL(3,1),
    faltas INT,
    email VARCHAR(255) UNIQUE
);

-- ========================
-- TABELA: Professor
-- ========================
CREATE TABLE professor (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE,
    instituicao_id INT REFERENCES instituicao(id)
);

-- ========================
-- TABELA: Disciplina
-- ========================
CREATE TABLE disciplina (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    curso_id INT REFERENCES curso(id),
    frequencia_maxima INT,
    professor_id INT REFERENCES professor(id)
);

-- ========================
-- TABELA: Notas
-- ========================
CREATE TABLE notas (
    id SERIAL PRIMARY KEY,
    ra_aluno INT REFERENCES usuario(ra),
    disciplina_id INT REFERENCES disciplina(id),
    p1 DECIMAL(4,2),
    p2 DECIMAL(4,2),
    t DECIMAL(4,2),
    p3 DECIMAL(4,2),
    media_final DECIMAL(4,2)
);

-- ========================
-- TABELA: Provas
-- ========================
CREATE TABLE provas (
    id SERIAL PRIMARY KEY,
    disciplina_id INT REFERENCES disciplina(id),
    data_prova DATE NOT NULL,
    tipo VARCHAR(50) -- Ex: P1, P2, P3 ou Trabalho
);

-- ========================
-- TABELA: Tarefas
-- ========================
CREATE TABLE tarefas (
    id SERIAL PRIMARY KEY,
    disciplina_id INT REFERENCES disciplina(id),
    nome VARCHAR(255) NOT NULL,
    descricao TEXT,
    data_entrega DATE NOT NULL
);

-- ========================
-- TABELA: Frequência
-- ========================
CREATE TABLE frequencia (
    id SERIAL PRIMARY KEY,
    ra_aluno INT REFERENCES usuario(ra),
    disciplina_id INT REFERENCES disciplina(id),
    aulas_ministradas INT,
    presencas INT,
    percentual DECIMAL(5,2)
);
