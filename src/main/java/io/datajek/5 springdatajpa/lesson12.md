# Lesson 12: Creating a Repository

## Overview

In JPA terms, a repository is a class that manages the entities.

## Demonstrated Concepts

### `@Repository` annotation

The `PlayerRepository` class is created to manage the `Player` entity and to store and retrieve data. We mark this class with the `@Repository` annotation.

```java
@Repository
public class PlayerRepository {

}
```

### Enabling transaction management

Database queries contain multiple steps. We will also enable transaction management to allow all steps in a query to succeed. In case of an error or runtime exception, all steps will be rolled back. Transactions are implemented at the business layer, but in this example, we will implement them at the repository level. 

Spring provides all the boilerplate code to start, commit, and roll back a transaction, which can also be integrated with JPA’s transaction management. This is enabled using the `@Transactional` annotation on a method or a class.

```java
@Repository
@Transactional
public class PlayerRepository {

}
```

Using this annotation on the `PlayerRepository` class, all the methods will be executed in a transactional context. So if we have `INSERT` and `UPDATE` in a single method, something called an `EntityManager` will keep track of both of them. If one of them fails, the whole operation will be rolled back.

### `EntityManager` and `@PersistenceContext` annotation

A JPA `EntityManager` manages connection to a database as well as to database operations.

`EntityManager` is associated with a `PersistenceContext`. All operations that are performed in a specific session are stored inside the `PersistenceContext`. `EntityManager` is the interface to the `PersistenceContext`. All operations on the entity go through the `EntityManager`. 

We declare an `EntityManager` object in our class and mark it with the `@PersistenceContext` annotation.

```java
public class PlayerRepository{
    @PersistenceContext
    EntityManager entityManager;
    //...
}
```

`EntityManager` provides a number of methods that perform `SELECT`, `INSERT`, `UPDATE`, and `DELETE` queries.

After creating an instance of `EntityManager`, we are ready to perform operations on the database.