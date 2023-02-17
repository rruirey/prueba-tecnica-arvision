# Simple CRUD

## Description

This is a simple CRUD application that allows you to create, read, update and delete users.

## Installation

To run this project, you will need to have the following installed on your machine:

- Java 17 or later
- Apache Maven 3.6 or later

Once you have these dependencies installed, you can clone the repository and build the project using **Maven**:

Download the repository:

```bash
git clone https://github.com/rruirey/prueba-tecnica-arvision.git
cd prueba-tecnica-arvision
```

Build the project:

```bash
./mvnw clean install
```

Create the **Docker** containers using **Docker Compose**.

```bash
docker-compose -f stack/stack.yml up -d
```

Run the project

```bash
 java -jar target/simple-crud-0.0.1-SNAPSHOT.jar
```

Server is accessible at http://localhost:8080

## API Documentation

The API documentation is available at http://localhost:8080/swagger-ui.html

