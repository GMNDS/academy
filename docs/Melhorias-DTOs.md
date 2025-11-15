# Melhorias nos DTOs de Resposta

## Problema Identificado

Anteriormente, os endpoints GET da API retornavam os **models completos** com todas as entidades relacionadas aninhadas. Isso causava:

- **JSON verboso e desnecessário**: Por exemplo, ao buscar um estudante, retornava todo o objeto `InstitutionModel` com todos seus campos, quando apenas o nome era necessário.
- **Problemas de performance**: Serialização de objetos grandes e complexos.
- **Exposição de dados sensíveis**: Retorno de informações que não deveriam ser expostas.
- **Referências circulares**: Potencial para erros de serialização JSON.

## Solução Implementada

Foram criados **DTOs de Resposta** (Response DTOs) para todos os controllers, retornando apenas os dados necessários:

### DTOs Criados

1. **StudentResponseDTO**
   - Retorna: id, ra, name, photo, **institutionName**, **courseName**, semester, averageGrade, email, role, active
   - Evita retornar objetos completos de `InstitutionModel` e `CourseModel`

2. **SubjectResponseDTO**
   - Retorna: id, name, **courseId**, **courseName**, code, totalClasses, **professorId**, **professorName**
   - Evita retornar objetos completos de `CourseModel` e `ProfessorModel`

3. **StudentGradeResponseDTO**
   - Retorna: id, **studentId**, **studentName**, **gradeId**, **gradeName**, gradeWeight, **subjectId**, **subjectName**, score
   - Evita retornar objetos completos de `StudentModel`, `GradeModel` e `SubjectModel`

4. **InstitutionResponseDTO**
   - Retorna: id, name, acronym, type, active

5. **ProfessorResponseDTO**
   - Retorna: id, name

6. **GradeResponseDTO**
   - Retorna: id, name, weight, **institutionId**, **institutionName**
   - Evita retornar objeto completo de `InstitutionModel`

7. **ExamResponseDTO**
   - Retorna: id, **subjectId**, **subjectName**, **subjectCode**, examDate, type
   - Evita retornar objeto completo de `SubjectModel`

8. **TaskResponseDTO**
   - Retorna: id, **studentId**, **studentName**, **subjectId**, **subjectName**, title, description, dueDate, completed, completedAt
   - Evita retornar objetos completos de `StudentModel` e `SubjectModel`

9. **AbsenceResponseDTO**
   - Retorna: id, **studentId**, **studentName**, **subjectId**, **subjectName**, totalClasses, attendances, percentage
   - Evita retornar objetos completos de `StudentModel` e `SubjectModel`

### Controllers Atualizados

Todos os métodos GET dos seguintes controllers foram atualizados:

- ✅ `StudentController`
- ✅ `SubjectController`
- ✅ `StudentGradeController`
- ✅ `InstitutionController`
- ✅ `ProfessorController`
- ✅ `GradeController`
- ✅ `ExamController`
- ✅ `TaskController`
- ✅ `AbsenceController`
- ✅ `CourseController` (já estava usando `CourseResponseDTO`)

## Benefícios

### Antes (Exemplo - GET /students/1)
```json
{
  "id": 1,
  "ra": "20210012345",
  "name": "Orion Steele",
  "institution": {
    "id": 1,
    "name": "Faculdade de Tecnologia de Mauá",
    "acronym": "FATEC",
    "type": "PUBLIC",
    "createdAt": "2025-01-15T10:30:00Z",
    "active": true
  },
  "course": {
    "id": 2,
    "name": "Desenvolvimento de Software Multiplataforma",
    "institution": { ... },
    "duration": 6,
    "category": "Exatas",
    "frequency": 5,
    "subjects": [ ... ]
  },
  "semester": 3,
  "averageGrade": 8.5,
  ...
}
```

### Depois (Exemplo - GET /students/1)
```json
{
  "id": 1,
  "ra": "20210012345",
  "name": "Orion Steele",
  "photo": "https://example.com/photo.jpg",
  "institutionName": "Faculdade de Tecnologia de Mauá",
  "courseName": "Desenvolvimento de Software Multiplataforma",
  "semester": 3,
  "averageGrade": 8.5,
  "email": "orion@gmnds.com",
  "role": "STUDENT",
  "active": true
}
```

### Vantagens

1. **JSON mais limpo e legível**: Apenas dados necessários
2. **Melhor performance**: Menos dados para serializar/deserializar
3. **Menor consumo de banda**: Respostas menores
4. **Melhor segurança**: Controle preciso sobre quais dados são expostos
5. **Evita problemas de serialização**: Sem referências circulares
6. **API mais profissional**: Seguindo boas práticas de REST

## Outras Correções

- Corrigida descrição do campo `grade` em `StudentGradeModel` (linha 27)
  - Antes: "Peso da avaliação associado à nota"
  - Depois: "Avaliação (P1, P2, etc) associada à nota"

## Próximas Recomendações

1. **Considerar criar DTOs de Request específicos** para POST e PUT, ao invés de receber Models completos
2. **Implementar validação de dados** nos DTOs com Bean Validation
3. **Adicionar testes unitários** para os mapeamentos Model → DTO
4. **Considerar usar MapStruct** para automatizar os mapeamentos entre Models e DTOs
5. **Documentar exemplos de resposta** no Swagger para cada endpoint
