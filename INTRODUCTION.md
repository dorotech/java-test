# `Project FAIR`

![Java](https://img.shields.io/badge/-java-%23C21325?style=for-the-badge&logo=java&logoColor=white)
![JUnit](https://img.shields.io/badge/junit-%6FD700.svg?style=for-the-badge&logo=junit&logoColor=white)
![Quarkus](https://img.shields.io/badge/quarkus-3AD5FF?style=for-the-badge&logo=quarkus.js&logoColor=white)
![Dynamodb](https://img.shields.io/badge/dynamodb-%23007ACC.svg?style=for-the-badge&logo=dynamodb&logoColor=white)

## Content Table

<!-- START doctoc generated TOC please keep comment here to allow auto update -->
<!-- DON'T EDIT THIS SECTION, INSTEAD RE-RUN doctoc TO UPDATE -->

- [`Project FAIR`](#project-fair)
  - [Content Table](#content-table)
- [User Instructions](#user-instructions)
    - [Requirements](#requirements)
    - [How to run the server](#how-to-run-the-server)
- [Architecture](#architecture)
    - [Input Validation Layer](#input-validation-layer)
    - [REST API Layer ( Presentation Layer )](#rest-api-layer--presentation-layer-)
    - [Service Layer ( Business Logic Layer )](#service-layer--business-logic-layer-)
    - [Repository Layer](#repository-layer)
    - [Exception Layer](#exception-layer)
    - [Persistence Layer](#persistence-layer)
- [Tests](#tests)
- [Database](#database)
    - [Run Dynamodb database locally](#run-dynamodb-database-locally)
    - [Install Java 11+ above](#install-java-11-above)

<!-- END doctoc generated TOC please keep comment here to allow auto update -->

# User Instructions

### Requirements

In order to run this project you will need to install the LTS version of java and Quarkus, found at [this link](https://quarkus.io/)

### How to run the server

First of all, run follow command below to install project's dependencies 

# Architecture

The system is structured in different logic layers that have specific responssabilities, highly increasing [cohesion](<https://en.wikipedia.org/wiki/Cohesion_(computer_science)#:~:text=In%20computer%20programming%2C%20cohesion%20refers,inside%20a%20module%20belong%20together.&text=In%20contrast%2C%20low%20cohesion%20is,with%20coupling%2C%20a%20different%20concept.>).
The main goal of this architecture is to provide easy ways of unit testing it's components.
This by itself is responsible for granting the system a lot of qualities in terms of decoupling and manuntenability.

### Input Validation Layer

We are going to validate input through the use of `javax.validation.constraints` anotations.

Every input shall be represented on request and response package annotated with validation decorators.

Including Http request's body, query and url params.

> A Proof of Concept will be created to test the idea of creating a middleware per controler method, by doing this we will be able to test url params without creating a validation class

### REST API Layer ( Presentation Layer )

In this layer we are going to use _controllers_ to:

- Handle HTTP requests and translate them into data structures that a service can understand.
- Return a response in the appropriate format

Controllers can throw errors directly, they don't need to craft error responses due to the existence of a exception layer.

Every controller from `controller` package

### Service Layer ( Business Logic Layer )

All business logic is done inside services.

This is the most decoupled layer of the system, as our priority lies in testing this business logic layer to ensure no bugs are present on them.

Services have access to all other services through dependency injection.

Some considerations about services are:

- Services can assume that the input types are correct since they are going to be validated before entering the service
- Every business logic validation that fails or problem that arises should throw an Error directly ( services should not return error objects )

Every controller from `service` package

### Repository Layer

We use this layer to abstract persistent-data access ( in our case, database access ) in such a way that we can mock or replace this layer easily.

We will be using the Repository pattern to do this.

Every controller from `repository` package

### Exception Layer

We respond to exceptions based on different error codes or exception types that are thrown.

We may define which httpStatus codes we return along with some static data based on what kind of error was thrown.

They should all be classes that extends the `ErrorHandler` class.

### Persistence Layer

We are going to use `DynamoDbBean` to even further abstract the database access in such a way that it's easy to define models

Every entity has from `entity` package

# Tests

We will mainly focus on testing Validation classes ( some kinds of DTOs ), services, helpers and controllers.

`test` package: Unitary tests following a BDD flavour of TDD. ( Tests are written before writing code. )

I strongly suggest you get a tool or plugin that can help you to easily run and visualize the result of tests, as this wil greatly improve your workflow.


# Database

### Run Dynamodb database locally

```bash
$ infrastructure docker-compose up
```

### Install Java 11+ above

`Check your operational system and install`
[this link] https://www.oracle.com/java/technologies/downloads/


