# Lesson 14: JPQL Named Query

## Overview

In lesson 13, we implement all CRUD operations except for the method corresponding to a `SELECT *` query. We will now implement this method using a named query in JPQL.

A named query is defined on an entity, which in our case is the `Player` class. The named query will be used in the `getAllPlayers` method.

## Demonstrated Concepts

### `#NamedQuery`

To create a named query, we use the `@NamedQuery` annotation on the Player class. This annotation requires two parameters: the name of the query and the query itself. 

When using JPA, we write the query in JPQL instead of SQL. JPQL uses entities in place of tables. Since we want to return a list of `Player` objects, the query will be `"SELECT p FROM Player p"`.

```java
@Entity
@NamedQuery(name="get_all_players", query="select p from Player p")
public class Player {
    //...
}
```

### Using a named query for `SELECT *` query

In the `getAllPlayers` method, we use the `createNamedQuery` method. We need to pass the name of the query and specify what the query will return. This query will return objects of the `Player` class. The `createNamedQuery` returns a `TypedQuery`, which we will assign to a variable called `getAll`. Then, we can use the `getResultList` method to return a list of players:

```java
public List<Player> getAllPlayers() {
    TypedQuery<Player> getAll = entityManager.createNamedQuery("get_all_players", Player.class);
    return getAll.getResultList();
}
```

Then, we  call `getAllPlayers` in the `run` method to display the records in the `Player` table.

```java
@Override
public void run(String... args) throws Exception {
    logger.info("Inserting Player: {}", repo.insertPlayer(new Player(
                                     "Djokovic", "Serbia", Date.valueOf("1987-05-22"), 81)));
    logger.info("Inserting Player: {}", repo.insertPlayer(new Player(
                                     "Monfils", "France", Date.valueOf("1986-09-01"), 10)));
    logger.info("Inserting Player: {}", repo.insertPlayer(new Player(
                                     "Thiem", "Austria", Date.valueOf("1993-09-03"), 17))); 
    logger.info("All Players Data: {}", repo.getAllPlayers());
}
```