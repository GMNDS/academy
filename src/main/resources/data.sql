-- Institutions primeiro (sem dependências)
INSERT INTO institutions (name, acronym, type, created_at, active) VALUES
    ('Universidade de Tecnologia', 'UNITEC', 0, NOW(), true),
    ('Centro de Aprendizado Digital', 'CAD', 1, NOW(), true);

-- Courses (institution_id referencia o ID gerado acima)
INSERT INTO courses (name, category, duration, frequency, institution_id, created_at, active) VALUES
    ('Java Fundamentals', 'PROGRAMMING', 4, 2, 1, NOW(), true),
    ('Spring Boot Avançado', 'FRAMEWORK', 6, 3, 2, NOW(), true);

-- Users
INSERT INTO students (ra, name, email, password, semester, absences, average_grade, course_id, institution_id, role, created_at, active) VALUES
    ('2024001', 'Orion', 'orion@gmnds.com', '$2a$10$w5ATnNBeF1BOC8R8q8t/5.xHPUl/nneNgVgnx1FrGgcdyKqp9qVRC', 3, 2, 8.5, 1, 1, 0, NOW(), true),
    ('2024002', 'Lara', 'lara@gmnds.com', '$2a$10$w5ATnNBeF1BOC8R8q8t/5.xHPUl/nneNgVgnx1FrGgcdyKqp9qVRC', 5, 0, 9.2, 2, 2, 1, NOW(), true);
