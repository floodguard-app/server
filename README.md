# 🌊 FloodGuard - Backend (Spring Boot API)

Este repositório contém o servidor backend do projeto FloodGuard, um sistema de suporte à gestão de riscos de enchentes. O backend fornece uma API RESTful para gerenciar usuários, autenticação e outros dados relevantes para o sistema.

O servidor foi construído em **Java 21+** com **Spring Boot 3.4.5**, utilizando **Spring Security** para autenticação baseada em **JWT** e **Spring Data JPA** para persistência de dados com **MySQL**.

---

## ⚙️ Tecnologias Utilizadas

- Java 21 (ou superior)
- Spring Boot 3.4.5
- Spring Web
- Spring Data JPA
- Spring Security
- MySQL
- JSON Web Token (JWT) para autenticação
- OpenAPI 3 (Swagger UI via SpringDoc)

---

## 🧩 Dependências Principais

Conforme `pom.xml`:

- `spring-boot-starter-web`: Para construção de APIs RESTful.
- `spring-boot-starter-data-jpa`: Para integração com o banco de dados MySQL.
- `spring-boot-starter-security`: Para segurança e autenticação (incluindo JWT).
- `springdoc-openapi-starter-webmvc-ui`: Para geração automática da documentação da API (Swagger UI).
- `mysql-connector-j`: Driver JDBC para MySQL.
- `spring-boot-devtools`: Para hot reload durante o desenvolvimento.
- `io.jsonwebtoken:jjwt-api`, `jjwt-impl`, `jjwt-jackson`: Bibliotecas JWT para geração e validação de tokens.
- `org.hibernate.validator`: Para validação de dados.

---

## 📁 Estrutura de Pacotes

```
src/main/java/com/floodguard/floodguard_server/
├── config         → Configurações de segurança (Spring Security), incluindo a configuração do filtro JWT.
├── controller     → Controladores REST que recebem as requisições HTTP (ex: `UsuarioController`).
├── dto            → Data Transfer Objects (ex: `UsuarioDTO`, `UsuarioLoginDTO`, `UsuarioCadastroDTO`).
├── exception      → Classes de exceção personalizadas (ex: `EmailAlreadyExistsException`).
├── model          → Entidades JPA que representam tabelas do banco (ex: `Usuario`, `Alerta`, `Bairro`).
├── repository     → Interfaces `JpaRepository` para persistência.
├── security       → JWT (`JwtUtil`, `JwtAuthenticationFilter`, `UserDetailsServiceImpl`).
├── service        → Lógica de negócio da aplicação (ex: `UsuarioService`).
```

---

## 🔧 Configuração

1. Clone o repositório:

```bash
git clone https://github.com/seu_usuario/seu_repositorio.git
cd floodguard-server
```

2. Configure o banco de dados:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/nome_do_seu_banco
spring.datasource.username=seu_usuario_do_banco
spring.datasource.password=senha_do_seu_usuario

spring.jpa.hibernate.ddl-auto=none
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
```

3. Configure o JWT Secret:

```properties
jwt.secret=suaChaveSecretaSuperSeguraAqui
jwt.expiration=86400000
jwt.refreshExpiration=604800000
```

⚠️ **IMPORTANTE**: A chave secreta (`jwt.secret`) deve ser longa, aleatória e mantida em segredo. **NÃO use chaves fracas em produção.**

4. Execute o projeto:

Com Maven:

```bash
./mvnw spring-boot:run
```

Ou pela IDE, execute a classe `FloodguardServerApplication`.

---

## 🔑 Autenticação

A API utiliza autenticação baseada em JWT.

### Cadastro de Usuário

```
POST /api/usuarios/comum/cadastro
```

### Login

```
POST /api/usuarios/login
```

### Acesso a Recursos Protegidos

Inclua o token JWT no cabeçalho da requisição:

```
Authorization: Bearer <seu_token_jwt>
```

---

## 🔍 Documentação da API

Acesse via Swagger UI:

[http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

---

## 🧪 Testes

- Utilize `@SpringBootTest` para testes de integração.
- As dependências estão incluídas via `spring-boot-starter-test`.

---

## 👨‍💻 Equipe

- Bruno de Almeida Otero  
- Gabriel Jefferson  
- Gustavo Dias  
- Leonardo Correia  

---

## 📄 Licença

Projeto acadêmico. Livre para fins educacionais.