# Lesson 6: DELETE Query

## Overview

In this lesson, we learn how to delete a record from the database.

## Demonstrated Concepts

### JdbcTemplate `Update` method

We will yet again use the `update` method of the `JdbcTemplate` class. This time, we will write a query to delete a row from a table.

```java
public int deletePlayerById(int id) {
    String sql="DELETE FROM PLAYER WHERE ID = ?";
    return jdbcTemplate.update(sql, new Object[] {id});
}
```

We execute the `DELETE` query in the `run` method

```java
@Override
public void run(String... args) throws Exception {
    logger.info("Deleting Player with Id 2: {}", dao.deletePlayerById(2));
    logger.info("All Players Data: {}", dao.getAllPlayers());
}
```