# Lesson 15: Spring Data JPA

## Overview

Spring Data identified the duplication of code when writing repositories and created some predefined repositories. The developer provides the entity type and its primary key and Spring Data comes up with the CRUD methods for the entity. Spring Data JPA adds a layer of abstraction over the JPA provider (Hibernate in our case).

The `JpaRepository` interface extends the `Repository` interface. It contains the API provided by `CrudRespository` as well as the `PagingAndSortingRepository` for CRUD operations along with pagination and sorting of records.

Using Spring Data, we can run the same application again without writing an implementation for any of the CRUD operations. The `JpaRepository` provides us with methods needed to perform those operations. This results in a significant reduction in boilerplate code.

> Note from `ginny100`: The __io.datajek.springdatajpaapp.tennisplayer__ package is dedicated for this lesson.

<img src="images/img5.png" width="500">

## Demonstrated Concepts

### JpaRepository interface

We create an interface that extends the `JpaRepository`. We call this interface `PlayerSpringDataRepository`. We need to specify the entity that will be managed by this repository, as well as the primary key of the entity as follows:

```java
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerSpringDataRepository extends JpaRepository<Player, Integer>{

    //no implementation required!
}
```

Simply by extending the `JpaRepository`, we get all basic CRUD operations without having to write any implementation.

To use this repository we've just created, we create a copy of the `TennisPlayerApplication` and call it `TennisPlayerSpringDataApplication`. We also autowire the `PlayerSpringDataRepository` in place of the `PlayerRepository`.

#### `save` method

To insert and update an object, Spring Data has a `save` method that works in the same manner as the `merge` method of the `EntityManager`.

```java
//Inserting rows
logger.info("Inserting Player: {}", repo.save(new Player("Djokovic", "Serbia", Date.valueOf("1987-05-22"), 81)));
logger.info("Inserting Player: {}", repo.save(new Player("Monfils", "France", Date.valueOf("1986-09-01"), 10)));
logger.info("Inserting Player: {}", repo.save(new Player("Thiem", "Austria", new Date(System.currentTimeMillis()), 17)));
```

```java
//Updating row
logger.info("Updating Player with Id 3: {}", repo.save(new Player(3, "Thiem", "Austria", Date.valueOf("1993-09-03"), 17)));
```

#### `findById` method

Spring Data has a method called `findById`, which takes in the primary key and returns an object.

```java
//Finding by id
logger.info("Player with Id 2: {}", repo.findById(2));
```

#### `findAll` method

It also has a `findAll` method which returns all entity objects.

```java
//Finding all
logger.info("All Players Data: {}", repo.findAll());
```

#### `deleteById` method

For deletion, Spring Data has a `deleteById` method that takes the primary key of the record to be deleted.

```java
//Deleting by id
repo.deleteById(2);
```

#### Other keywords

The CRUD methods in Spring Data are annotated with `@Transactional`. Spring Data can parse a method name and create a query from it. In the code widget below, we have a method `findByNationality` in the `PlayerSpringDataRepository` for which we have not provided any implementation. This method name is converted to the following JPQL query using the JPA Criteria API:

```sql
select p from Player p where p.nationality = ?1
```

We can use keywords such as `And`, `Or`, `GreaterThan`, `LessThan`, `Like`, `IsNull`, `Not` etc., in the method name and `OrderBy` clause can be used to sort the results.