-- Institutions primeiro (sem dependências)
INSERT INTO institutions (name, acronym, type, created_at, active) VALUES
    ('Universidade de Tecnologia', 'UNITEC', 0, NOW(), true),
    ('Centro de Aprendizado Digital', 'CAD', 1, NOW(), true);

-- Courses (institution_id referencia o ID gerado acima)
INSERT INTO courses (name, category, duration, frequency, institution_id, created_at, active) VALUES
    ('Java Fundamentals', 'PROGRAMMING', 4, 2, 1, NOW(), true),
    ('Spring Boot Avançado', 'FRAMEWORK', 6, 3, 2, NOW(), true);

-- Users (students)
INSERT INTO students (ra, name, email, password, semester, average_grade, course_id, institution_id, role, created_at, active) VALUES
    ('2024001', 'Orion', 'orion@gmnds.com', '$2a$10$w5ATnNBeF1BOC8R8q8t/5.xHPUl/nneNgVgnx1FrGgcdyKqp9qVRC', 3, 8.5, 1, 1, 0, NOW(), true),
    ('2024002', 'Lara', 'lara@gmnds.com', '$2a$10$w5ATnNBeF1BOC8R8q8t/5.xHPUl/nneNgVgnx1FrGgcdyKqp9qVRC', 5,  9.2, 2, 2, 1, NOW(), true);

-- Professors
INSERT INTO professors (name) VALUES
    ('Ana Souza'),
    ('Carlos Lima'),
    ('Mariana Oliveira');

-- Subjects (principalmente para o curso de Lara = course_id 2)
INSERT INTO subjects (name, course_id, code, total_classes, teacher_id) VALUES
    ('Spring Core',       2, 'SPR101', 60, (SELECT id FROM professors WHERE name = 'Ana Souza')),
    ('Spring Data JPA',   2, 'SPR202', 60, (SELECT id FROM professors WHERE name = 'Carlos Lima')),
    ('Spring Security',   2, 'SPR303', 60, (SELECT id FROM professors WHERE name = 'Mariana Oliveira'));

-- Pesos de avaliação (grades) para a instituição da Lara (id=2)
INSERT INTO grades (name, weight, institution_id) VALUES
    ('P1', 0.3, 2),
    ('P2', 0.3, 2),
    ('T',  0.2, 2),
    ('P3', 0.2, 2);

-- Provas (exams) para as disciplinas do curso da Lara
INSERT INTO exams (subject_id, exam_date, type) VALUES
    ((SELECT subject_id FROM subjects WHERE code = 'SPR101'), DATE '2025-03-15', 'P1'),
    ((SELECT subject_id FROM subjects WHERE code = 'SPR202'), DATE '2025-03-22', 'P1'),
    ((SELECT subject_id FROM subjects WHERE code = 'SPR303'), DATE '2025-03-29', 'P1'),
    ((SELECT subject_id FROM subjects WHERE code = 'SPR101'), DATE '2025-05-10', 'P2'),
    ((SELECT subject_id FROM subjects WHERE code = 'SPR202'), DATE '2025-05-17', 'P2'),
    ((SELECT subject_id FROM subjects WHERE code = 'SPR303'), DATE '2025-05-24', 'P2');

-- Tarefas (tasks) para a Lara
INSERT INTO tasks (student_id, subject_id, title, description, due_date, completed, completed_at) VALUES
    ((SELECT id FROM students WHERE email = 'lara@gmnds.com'), (SELECT subject_id FROM subjects WHERE code = 'SPR101'), 'Revisar Beans e Ciclo de Vida', 'Leitura do capítulo 3 e anotações', TIMESTAMP '2025-03-10 23:59:00', true,  TIMESTAMP '2025-03-08 20:00:00'),
    ((SELECT id FROM students WHERE email = 'lara@gmnds.com'), (SELECT subject_id FROM subjects WHERE code = 'SPR202'), 'Projeto JPA - Repositórios', 'Criar repositórios e consultas',    TIMESTAMP '2025-03-20 23:59:00', false, NULL),
    ((SELECT id FROM students WHERE email = 'lara@gmnds.com'), (SELECT subject_id FROM subjects WHERE code = 'SPR303'), 'Configurar OAuth2',              'Configurar fluxo Authorization Code', TIMESTAMP '2025-04-05 23:59:00', false, NULL),
    ((SELECT id FROM students WHERE email = 'lara@gmnds.com'), (SELECT subject_id FROM subjects WHERE code = 'SPR202'), 'Query Methods',                  'Praticar query methods e @Query',    TIMESTAMP '2025-03-28 23:59:00', true,  TIMESTAMP '2025-03-27 21:30:00'),
    ((SELECT id FROM students WHERE email = 'lara@gmnds.com'), (SELECT subject_id FROM subjects WHERE code = 'SPR101'), 'Component Scan',                 'Mapear pacotes e perfis',            TIMESTAMP '2025-04-01 23:59:00', false, NULL);

-- Frequências (absences) para a Lara
-- Nota: percentage é calculada no modelo ao persistir via JPA; como aqui é SQL puro, já inserimos o valor calculado
INSERT INTO absences (student_id, subject_id, total_classes, attendances, percentage) VALUES
    ((SELECT id FROM students WHERE email = 'lara@gmnds.com'), (SELECT subject_id FROM subjects WHERE code = 'SPR101'), 60, 54, 90.0),
    ((SELECT id FROM students WHERE email = 'lara@gmnds.com'), (SELECT subject_id FROM subjects WHERE code = 'SPR202'), 60, 51, 85.0),
    ((SELECT id FROM students WHERE email = 'lara@gmnds.com'), (SELECT subject_id FROM subjects WHERE code = 'SPR303'), 60, 56, 93.33);

-- Notas por avaliação (student_grades) para a Lara nas disciplinas
INSERT INTO student_grades (student_id, grade_id, subject_id, score) VALUES
    -- SPR101 (Spring Core)
    ((SELECT id FROM students WHERE email = 'lara@gmnds.com'), (SELECT id FROM grades WHERE name = 'P1' AND institution_id = 2), (SELECT subject_id FROM subjects WHERE code = 'SPR101'), 8.7),
    ((SELECT id FROM students WHERE email = 'lara@gmnds.com'), (SELECT id FROM grades WHERE name = 'P2' AND institution_id = 2), (SELECT subject_id FROM subjects WHERE code = 'SPR101'), 9.1),
    ((SELECT id FROM students WHERE email = 'lara@gmnds.com'), (SELECT id FROM grades WHERE name = 'T'  AND institution_id = 2), (SELECT subject_id FROM subjects WHERE code = 'SPR101'), 9.5),
    ((SELECT id FROM students WHERE email = 'lara@gmnds.com'), (SELECT id FROM grades WHERE name = 'P3' AND institution_id = 2), (SELECT subject_id FROM subjects WHERE code = 'SPR101'), 8.9),
    -- SPR202 (Spring Data JPA)
    ((SELECT id FROM students WHERE email = 'lara@gmnds.com'), (SELECT id FROM grades WHERE name = 'P1' AND institution_id = 2), (SELECT subject_id FROM subjects WHERE code = 'SPR202'), 8.3),
    ((SELECT id FROM students WHERE email = 'lara@gmnds.com'), (SELECT id FROM grades WHERE name = 'P2' AND institution_id = 2), (SELECT subject_id FROM subjects WHERE code = 'SPR202'), 8.8),
    ((SELECT id FROM students WHERE email = 'lara@gmnds.com'), (SELECT id FROM grades WHERE name = 'T'  AND institution_id = 2), (SELECT subject_id FROM subjects WHERE code = 'SPR202'), 9.2),
    ((SELECT id FROM students WHERE email = 'lara@gmnds.com'), (SELECT id FROM grades WHERE name = 'P3' AND institution_id = 2), (SELECT subject_id FROM subjects WHERE code = 'SPR202'), 8.6),
    -- SPR303 (Spring Security)
    ((SELECT id FROM students WHERE email = 'lara@gmnds.com'), (SELECT id FROM grades WHERE name = 'P1' AND institution_id = 2), (SELECT subject_id FROM subjects WHERE code = 'SPR303'), 9.0),
    ((SELECT id FROM students WHERE email = 'lara@gmnds.com'), (SELECT id FROM grades WHERE name = 'P2' AND institution_id = 2), (SELECT subject_id FROM subjects WHERE code = 'SPR303'), 9.4),
    ((SELECT id FROM students WHERE email = 'lara@gmnds.com'), (SELECT id FROM grades WHERE name = 'T'  AND institution_id = 2), (SELECT subject_id FROM subjects WHERE code = 'SPR303'), 9.7),
    ((SELECT id FROM students WHERE email = 'lara@gmnds.com'), (SELECT id FROM grades WHERE name = 'P3' AND institution_id = 2), (SELECT subject_id FROM subjects WHERE code = 'SPR303'), 9.1);
