# README

## Structure

* config: project configuration files: basic swagger configuration and sheldlock task configuration
* controller: controllers of the application - interface-driven controllers
    * dto: all Data Transfer Objects (POJO's) for this layer
    * mapper: mappers between dto layer(controllers) and utils
* dao: class to access for the database   
* exception: centralized manager of exceptions for the application
* model: all POJO's of the domain for the application
* service: the business of the application 
* task: implementation the sheduled task

### Main project folders

* db: Files to create a basic database
* postman: it has an example Postman project to interact with emails
* src: main java files to solve the problem
* test: test files of the core of the project

## Swagger

Basic Swagger documentation can be accessed by: http://localhost:8080/swagger-ui/index.html


## Database - H2

Can be accessed by: http://localhost:8080/h2-console/


## How to execute project

This project is a Spring Boot project packaged in jar can be executed by terminal or directly with the desired IDE 