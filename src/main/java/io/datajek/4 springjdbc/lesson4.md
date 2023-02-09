# Lesson 4: INSERT Query

## Overview

In this lesson, we learn how to write to the database.

## Demonstrated Concepts

### JdbcTemplate `Update` method

The `update` method is used for an `INSERT` query. 

This method takes the SQL query as well as an array of objects containing the values that will be inserted. 

The method returns an `int` value which shows the number of rows affected by the query.

```java
public int insertPlayer(Player player) {
    String sql = "INSERT INTO PLAYER (ID, Name, Nationality,Birth_date, Titles) " + "VALUES (?, ?, ?, ?, ?)";
    return jdbcTemplate.update( sql, new Object[] 
                               { player.getId(), player.getName(), player.getNationality(), 
                                 new Timestamp(player.getBirthDate().getTime()), 
                                 player.getTitles()  
                               });
}
```

We execute the `INSERT` query in the `run` method.

```java
@Override
public void run(String... args) throws Exception {
   logger.info("Inserting Player 4: {}", dao.insertPlayer(new Player (4, "Thiem", "Austria", new Date(System.currentTimeMillis()), 17)));  
   logger.info("All Players Data: {}", dao.getAllPlayers());
}
```

