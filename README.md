# Sistema de Gestão Acadêmica

## Descrição
Sistema web para gestão de alunos, professores, disciplinas, turmas e matrículas acadêmicas. Permite cadastro, edição, remoção, listagem e busca de registros, além de matrículas de alunos em turmas.

## Funcionalidades
- **Alunos:** Cadastro, edição, remoção, listagem e busca por nome.
- **Professores:** Cadastro, edição, remoção, listagem e busca por nome.
- **Disciplinas:** Cadastro, edição, remoção, listagem e busca por nome.
- **Turmas:** Cadastro, edição, remoção, listagem e busca por semestre ou professor.
- **Matrículas:** Matrícula de alunos em turmas, edição, cancelamento, listagem e filtros por aluno ou turma.

## Entidades Principais
- **Aluno:** nome, email, data de nascimento
- **Professor:** nome, email, área de atuação
- **Disciplina:** nome, código, ementa, carga horária
- **Turma:** disciplina, professor, semestre
- **Matrícula:** aluno, turma, data da matrícula, status

## Tecnologias Utilizadas
- Java 17
- Spring Boot 3.2.5 (Web, Data JPA, Thymeleaf)
- MySQL 8+
- Jakarta Validation
- Hibernate Validator
- Maven

## Requisitos
- Java 17+
- MySQL 8+
- Maven 3.6+

## Configuração
1. Clone o repositório:
   ```bash
   git clone <url-do-repositorio>
   ```
2. Configure o banco de dados MySQL:
   - Crie um banco chamado `gestao_academica`.
   - Ajuste o usuário e senha em `src/main/resources/application.properties`:
     ```properties
     spring.datasource.username=SEU_USUARIO
     spring.datasource.password=SUA_SENHA
     ```
3. Execute o projeto:
   ```bash
   mvn spring-boot:run
   ```
4. Acesse no navegador:
   - [http://localhost:8080/alunos](http://localhost:8080/alunos)

## Estrutura de Navegação
- Menu superior para acesso rápido a Alunos, Professores, Disciplinas, Turmas e Matrículas.
- Telas de listagem com busca e ações de editar/remover.
- Telas de cadastro/edição com validação de campos obrigatórios.

## Observações
- O banco de dados é atualizado automaticamente (DDL auto: update).
- O projeto utiliza Thymeleaf para renderização das páginas HTML.
- Mensagens de sucesso/erro são exibidas após operações.

## Licença
Este projeto é acadêmico e livre para uso educacional. 