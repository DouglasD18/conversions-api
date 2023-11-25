# Conversions API

## Contexto
Projeto final do curso Java Web do programa Santandr Coders 2023 da Ada Tech. Esse projeto é uma API CRUD que realiza convrsão de moedas através de requisição a uma [API externa](https://docs.awesomeapi.com.br/api-de-moedas#outras-conversoes).  

## Tecnologias 
 - Java
 - Spring-boot
 - Spring-JPA
 - Spring-boot Test
 - Lombok
 - H2

## Rotas Disponíveis

A API possui as seguintes rotas:

### 1. Adicionar Conversão

```
Endpoint: POST http://localhost:8080/conversion
```

### 2. Listar Conversões

```
Endpoint: GET http://localhost:8080/conversion
```

### 3. Alterar Conversão

```
Endpoint: POST http://localhost:8080/conversion/{id}
```

### 4. Deletar Conversão

```
Endpoint: POST http://localhost:8080/conversion/{id}
```