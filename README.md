# 🌐 FloodGuard - Backend (Spring Boot)

Este repositório contém o servidor backend do projeto **FloodGuard**, um sistema de previsão de enchentes desenvolvido como projeto de extensão no curso de Análise e Desenvolvimento de Sistemas.

O backend foi construído em **Java 21+** com **Spring Boot 3.4**, integrando funcionalidades de API REST, segurança com Spring Security e persistência de dados com Spring Data JPA.

---

## ⚙️ Tecnologias Utilizadas

- Java 21 (ou superior)
- Spring Boot 3.4.5
- Spring Web
- Spring Data JPA
- Spring Security
- MySQL
- OpenAPI (Swagger 3 via SpringDoc)

---

## 🧩 Dependências Principais

Conforme `pom.xml`:

- `spring-boot-starter-web`
- `spring-boot-starter-data-jpa`
- `spring-boot-starter-security`
- `springdoc-openapi-starter-webmvc-ui`
- `mysql-connector-j`
- `spring-boot-devtools` (hot reload)

---

## 📁 Estrutura de Pacotes

```
src/main/java/com/floodguard/floodguard_server/

├── config         → Configurações de segurança, CORS, beans personalizados etc.
├── controller     → Controladores REST que recebem e respondem às requisições.
├── dto            → Data Transfer Objects usados entre as camadas da aplicação.
├── model          → Entidades JPA representando as tabelas do banco de dados.
├── repository     → Interfaces que estendem JpaRepository para persistência.
├── service        → Regras de negócio e lógica central da aplicação.
```

---

## 🔧 Configuração

### 1. Clone o repositório:
```bash
git clone https://github.com/floodguard-app/server.git
cd floodguard-server
```

### 2. Configure o banco de dados:

No `application.properties` ou `application.yml`, adicione:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/floodguard_db
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

### 3. Execute o projeto:
Com o Maven:
```bash
./mvnw spring-boot:run
```

Ou diretamente pela sua IDE (ex: IntelliJ, Eclipse, VS Code).

---

## 🧪 Testes

- Use `@SpringBootTest` para testes de integração.
- As dependências de teste já estão configuradas via `spring-boot-starter-test`.

---

## 🔍 Documentação da API

Acesse a documentação gerada automaticamente por Swagger:
```
http://localhost:8080/swagger-ui.html
```

---

## 👨‍💻 Equipe

- Bruno de Almeida Otero  
- Gabriel Jefferson  
- Gustavo Dias  
- Leonardo Correia

---

## 📄 Licença

Projeto acadêmico. Livre para fins educacionais.