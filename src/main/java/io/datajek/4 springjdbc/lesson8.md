# Lesson 8: Custom RowMapper

## Overview

we have been using the `BeanPropertyRowMapper` to map the results of the query to our bean. If the database table has a _different structure or column names_, we need to define our custom mapping.

Custom row mappers come in handy when the table definitions are different from the bean definitions.

## Demonstrated Concepts

### RowMapper interface

We can define our own row mapper by implementing the `RowMapper` interface and providing the bean onto which the rows will be mapped. The custom row mapper, `PlayerMapper` is created as an inner class because it will only be used inside `JdbcPlayerDao`. It is best practice to make it `static` and `final`.

```java
@Repository
public class PlayerDao {
    //...
    private static final class PlayerMapper implements RowMapper<Player> {

    }

}
```

The `PlayerMapper` class is _reusable_ and can be used in all methods of the `PlayerDao` class to map rows from the `Player` table to the `Player` bean.

### mapRow method

The `Rowmapper` interface has one method, `mapRow`, for which we will write our custom implementation to initialize a `Player` object. 

This method defines how a row is mapped. It takes two arguments, the first being the result set which `JdbcTemplate` gets after running the query and the second being the row number. The row number of every row in the result set is different. 

The `JdbcTemplate` calls the `mapRow` method for every row in the result set and passes its row number as an argument. The method returns an object of `Player` type.

```java
@Override
public Player mapRow(ResultSet resultSet, int rowNum) throws SQLException {
    Player player = new Player();
    player.setId(resultSet.getInt("id"));
    player.setName(resultSet.getString("name"));
    player.setNationality(resultSet.getString("nationality"));
    player.setBirthDate(resultSet.getTime("birth_date"));
    player.setTitles(resultSet.getInt("titles"));
    return player; 
}
```

### Using custom row mapper

To use `PlayerMapper`, we can simply pass it in any method instead of the `BeanPropertyRowMapper`. We create a method that finds players based on their nationality and use our custom mapper to convert the result set to objects

```java
public List<Player> getPlayerByNationality(String nationality) {
    String sql = "SELECT * FROM PLAYER WHERE NATIONALITY = ?";
    return jdbcTemplate.query(sql, new PlayerMapper(), new Object[] {nationality});
}
```

The `PlayerMapper` is called for every row in the result set and each time it returns a new `Player` object. We do not have to worry about keeping track of the rows. `JdbcTemplate` does that work for us.

Let's call the `getPlayerByNationality` to test our row mapper and find all French players.

```java
@Override
public void run(String... args) throws Exception {
    logger.info("French Players: {}", dao.getPlayerByNationality("France"));
}
```


