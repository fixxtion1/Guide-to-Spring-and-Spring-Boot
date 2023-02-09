# Lesson 1: Creating a JDBC Starter Project

## Overview

Spring JDBC makes talking to databases easy by eliminating the need for establishing a connection, handling expectations, and closing connections. Spring provides a template class called `JdbcTemplate` to interact with databases which offers a wide array of methods to perform storage and retrieval of data. The `JdbcTemplate` class hides all the boilerplate code for connection management and error handling whereby the developer can focus on the SQL query.

Using the Spring `JdbcTemplate`, a Java application can store and retrieve objects of a class to a table in the database.

<img src="images/img1.png" width="500">

> Note from `ginny100`: In this repo, the package `io.datajek.springdata.tennisplayer` is for Chapter 4 and `io.datajek.springdatajpa.tennisplayer` is for Chapter 5.


## Demonstrated Concepts

### Spring Boot JDBC

Spring Boot simplifies development and makes JDBC programming easy.

- When using Spring Boot, only one dependency (`spring-boot-starter-jdbc`) is needed in the `pom.xml` file as compared to multiple dependencies in Spring (`spring-context`, `spring-jdbc`, etc.).
- Spring Boot automatically initializes the `datasource` bean whereas it needs to be created using XML or Java configuration in Spring.
- Spring Boot also autoconfigures the `JdbcTemplate` and other template beans that need to be explicitly registered in Spring.
- Spring Boot automatically creates the database schema specified in the schema.sql file. The schema needs to be explicitly configured if Spring is used.

### In-memory database

In-memory databases are faster because they are stored in memory and do not require disk access. The in-memory database lives as long as the application is running. When the application is stopped, the database is removed from memory. 

In-memory databases are easy to use as they do not require any setup like installing a server, creating a database, defining a schema, etc. that are required with databases like Oracle or MySQL. 

We can easily swap the in-memory database with a traditional database with a few changes in the `pom.xml` file. 

The in-memory database can be viewed in a web browser.

### Using Spring Boot Initializer

To set up a starter project for JDBC, go to [Spring Initializr](http://start.spring.io/). The Group Id of the project is springdata and the Artifact Id is tennis-player. We will add the following dependencies:

- JDBC API for querying the database 
- H2 Database that supports JDBC API access 
- Spring Web for the web console offered by H2

After generating the project and importing it in the IDE, we can see the three dependencies that we just added in the `pom.xml` file. When the application is run, Tomcat starts on port 8080 because of the web dependency.












