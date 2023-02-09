# Lesson 5: UPDATE Query

## Overview

In this lesson, we learn how to update an existing record in the database.

## Demonstrated Concepts

### JdbcTemplate `Update` method

The `update` method of the `JdbcTemplate` is used to execute `INSERT` as well as `UPDATE` queries. The only difference is in the query, which will change from `INSERT` to an `UPDATE` query.

```java
public int updatePlayer(Player player) {
    String sql = "UPDATE PLAYER " +
                 "SET Name = ?, Nationality = ?, Birth_date = ? , Titles = ? " +
                 "WHERE ID = ?";

    return jdbcTemplate.update( sql, new Object[] { 
                                   player.getName(), 
                                   player.getNationality(), 
                                   new Timestamp(player.getBirthDate().getTime()), 
                                   player.getTitles(), 
                                   player.getId() }
                              );
}
```

Notice that the order of getter methods match the order in which the values will be passed to the query.

We execute the `UPDATE` query in the `run` method.

```java
@Override
public void run(String... args) throws Exception {
    //Inserting a player
    logger.info("Inserting Player 4: {}", dao.insertPlayer( 
                        new Player(4, "Thiem", "Austria", 
                                   new Date(System.currentTimeMillis()), 17))); 
 
    //Updating a player
    logger.info("Updating Player with Id 4: {}",  dao.updatePlayer(
                        new Player(4, "Thiem", "Austria", 
                                   Date.valueOf("1993-09-03"), 17)));
 
    //View player by Id
    logger.info("Players with Id 4: {}", dao.getPlayerById(4));
}
```









