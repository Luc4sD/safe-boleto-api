# SafeBoleto API

[!Java CI with Maven](https://github.com/Luc4sD/safe-boleto-api/actions/workflows/ci.yml)

API RESTful desenvolvida com Spring Boot para validação de autenticidade de boletos bancários brasileiros. O sistema verifica a estrutura da linha digitável, a confiabilidade do banco emissor e a validade dos dígitos verificadores (Módulo 10 e Módulo 11), oferecendo um endpoint seguro para consulta e prevenindo fraudes.

## ✨ Principais Funcionalidades

-   **Validação de Linha Digitável**: Endpoint REST para submeter uma linha digitável de 47 dígitos.
-   **Verificação de Banco Emissor**: Checa se o código do banco pertence a uma lista de instituições confiáveis.
-   **Cálculo de Dígitos Verificadores**: Implementa as regras de Módulo 10 e Módulo 11 para validar a integridade do boleto.
-   **Segurança**: Autenticação baseada em JWT para proteger os endpoints.
-   **Documentação de API**: Geração automática de documentação com Swagger (OpenAPI).
-   **Histórico de Validações**: Armazena o resultado de cada validação em um banco de dados PostgreSQL.

## 🛠️ Tecnologias Utilizadas

-   **Backend**: Java 21, Spring Boot 3.3
-   **Persistência**: Spring Data JPA, Hibernate
-   **Banco de Dados**: PostgreSQL
-   **Segurança**: Spring Security, JSON Web Tokens (JWT)
-   **Containerização**: Docker e Docker Compose
-   **Build**: Apache Maven
-   **Documentação**: Springdoc OpenAPI (Swagger)

## 🚀 Como Executar o Projeto

### Pré-requisitos

-   Docker
-   Docker Compose

### 1. Configuração do Ambiente

Na raiz do projeto, crie um arquivo chamado `.env` e preencha com as variáveis de ambiente necessárias. Este arquivo é ignorado pelo Git (`.gitignore`) para proteger suas credenciais.

```env
# Senha para o usuário 'postgres' do banco de dados PostgreSQL
POSTGRES_PASSWORD=sua_senha_segura

# Chave secreta para a geração de tokens JWT (deve ser uma string longa e segura)
JWT_SECRET=Z2lkY29yZS1hcGktc2VjcmV0LWtleS1mb3Itand0LXNlY3VyaXR5LTIwMjQtZXhhbXBsZQo=

# Senha inicial para o usuário 'admin' da aplicação
ADMIN_PASSWORD=admin123
```

### 2. Executando com Docker Compose

Com o Docker em execução, execute o seguinte comando na raiz do projeto. Ele irá construir a imagem da aplicação e subir os contêineres do banco de dados e da API.

```bash
docker-compose up --build
```

A API estará disponível em `http://localhost:8080`.

## 📖 Documentação da API (Swagger)

Após iniciar a aplicação, a documentação completa dos endpoints, incluindo modelos de requisição e resposta, pode ser acessada em:

-   **Swagger UI**: http://localhost:8080/swagger-ui.html

## 🧪 CI - Integração Contínua

Este projeto utiliza GitHub Actions para garantir a qualidade do código. A cada `push` ou `pull request` para a branch `main`, o workflow definido em `.github/workflows/ci.yml` executa os seguintes passos:
1.  Configura o ambiente com JDK 21.
2.  Executa `mvn -B package`, que compila o código e roda todos os testes automatizados.
