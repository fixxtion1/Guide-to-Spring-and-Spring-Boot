# Lesson 9: Connecting to Other Databases

## Overview

We have been using an in-memory H2 database because it is easy to set up and use. Now it’s time to learn how to switch to another database. In real-life applications, we might want to connect to Oracle, MySql, or SQL server databases. Using Spring Boot, switching databases is very straightforward. We just need to add some dependencies and change some property values.

This lesson assumes that the database is installed and the schema is properly populated.

## Demonstrated Concepts

### Replacing the H2 dependency

The first step is to remove the H2 dependency from `pom.xml` and replace it with the dependency of the database we wish to connect to. Suppose we wish to connect to MySql. The dependency for MySql is given below. Dependencies for other databases are available online.

```xml
<dependency>
   <groupId>mysql</groupId>
   <artifactId>mysql-connector-java</artifactId>
</dependency>
```

### Configuring property values

For any database, we need to configure its URL, username, and password. These values are specified in the `application.properties` file.

```properties
spring.jpa.hibernate.ddl-auto=none 
spring.datasource.url = jdbc:mysql://localhost:3306/movie_example
spring.datasource.username = demo
spring.datasource.password = demo
```

Since we already have a schema and don't want Hibernate to create it for us, we have set `spring.jpa.hibernate.ddl-auto` setting to none. Other options for this setting are `create-only`, `drop`, `create`, `validate`, and `update`. If we use `create` instead of `none`, Hibernate would look at the entities and create the schema itself (after dropping any already existing schema).

The `username` and `password` are used to connect to the data source.

After making these changes, restart the application and Spring Boot will connect to the database of your choice.