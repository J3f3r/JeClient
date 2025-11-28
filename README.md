# **📌 CRUD de Clientes – Projeto Spring Boot com H2 (Desafio Autoral)**

Este repositório contém meu **primeiro CRUD autoral desenvolvido do zero**, seguindo boas práticas de mercado e requisitos típicos de desafios técnicos para vagas de **estágio em Backend / Java / Spring Boot**.

O objetivo deste projeto é demonstrar minha capacidade atual de:

* Desenvolver uma API REST simples porém **bem estruturada**
* Aplicar boas práticas essenciais (DTO, camadas separadas, validação, tratamento de erros, paginação)
* Escrever código limpo e organizado
* Entregar um projeto funcional baseado em regras de negócio
* Demonstrar que estou pronto para **atuar como estagiário** no mercado atual

---

## 🚀 **Tecnologias Utilizadas**

* **Java 21**
* **Spring Boot 3**
* **Spring Web**
* **Spring Data JPA**
* **H2 Database**
* **Maven**
* **Validation (Jakarta)**

---

## 📋 **Regras de Negócio (conforme o desafio)**

A API deve permitir operações CRUD completas em um recurso de **Clientes**, contendo:

### **Atributos do Cliente**

```java
id: Long  
name: String  
cpf: String  
income: Double  
birthDate: LocalDate  
children: Integer
```

### **Regras obrigatórias**

✔ Deve conter pelo menos **10 clientes no seed inicial** (import.sql)
✔ Busca paginada de clientes
✔ Validações:

* nome não pode estar vazio
* CPF válido
* renda não negativa
* data de nascimento no passado
* filhos ≥ 0
  ✔ Resposta HTTP correta em cada cenário (200, 201, 204, 404, 422)

---

## 🧪 **Checklist de funcionamento**

A API foi projetada para atender todos os cenários abaixo:

### **GET /clients/{id}**

✔ Cliente existente → retorna 200
✔ Cliente inexistente → retorna 404

### **GET /clients?page=&size=&sort=**

✔ Paginação funcionando corretamente
✔ Ordenação por nome configurada no Postman

### **POST /clients**

✔ Inserção com dados válidos → 201
✔ Inserção inválida → retorna 422 com mensagens específicas

### **PUT /clients/{id}**

✔ Atualização completa de cliente válido
✔ Atualização de cliente inexistente → 404
✔ Atualização inválida → 422

### **DELETE /clients/{id}**

✔ Exclusão de cliente existente → 204
✔ Exclusão de cliente inexistente → 404

---

## 🗄️ **Configuração do Banco H2**

A aplicação utiliza banco **em memória (H2)** para facilitar testes locais.

Acesse via navegador:

```
http://localhost:8080/h2-console
```

Credenciais:

```
JDBC URL: jdbc:h2:mem:testdb
User: sa
Password: (vazio)
```

---

## 📦 **Como rodar o projeto**

### **1 — Clonar o repositório**

```bash
git clone https://github.com/seu-usuario/seu-repo.git
```

### **2 — Rodar com Maven**

```bash
mvn spring-boot:run
```

### **3 — Testar no Postman**

Exemplos:

#### **GET por ID**

```
GET http://localhost:8080/clients/1
```

#### **GET paginado**

```
GET http://localhost:8080/clients?page=0&size=6&sort=name
```

#### **POST**

```json
{
  "name": "Joana Silva",
  "cpf": "12345678901",
  "income": 3500.00,
  "birthDate": "1990-04-20",
  "children": 2
}
```

---

## 🏗️ **Estrutura do Projeto (Camadas)**

```
src/main/java/com.jeferson.jeclient
│
├── controllers   -> Endpoints REST
├── dto           -> Transferência de dados (clean code)
├── entities      -> Entidades JPA
├── repositories  -> Acesso ao banco de dados
├── service       -> Regras de negócio
```

---

## ⭐ **Por que este projeto é importante para mim?**

Este é o **meu primeiro CRUD autoral** usando:

* Boas práticas
* Camadas bem definidas
* Validações reais
* Paginação
* Tratamento de exceções
* Banco em memória
* Seed automático via `import.sql`

Desenvolvi este projeto para mostrar que:

✔ Sei construir APIs organizadas
✔ Entendo os fundamentos do Spring
✔ Consigo ler, interpretar e implementar requisitos
✔ Estou preparado para contribuir em uma equipe como **estagiário de Backend / Java**

---

## 👤 **Autor**

**Jeferson de Souza Martins**
Desenvolvedor Backend Jr (em formação)
Focado em **Java, Spring Boot, SQL, Azure e boas práticas**
LinkedIn: *https://www.linkedin.com/in/jeferson-martins-48b9b690/*

---

