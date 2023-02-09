# Lesson 3: SELECT Query

## Overview

Spring provides `JdbcTemplate`, which makes it easy to write and execute a query. It also provides the `BeanPropertyRowMapper` which maps rows of a table to a bean.

<img src="images/img2.png" width="500">

Let's learn how to map the data coming from the database to a bean in our application. Next, we will modify our query to return the row that matches an input argument.

In our application, The `Player` class is a bean and the data coming from the `Player` table in H2 will be mapped to this class.

The class `PlayerDao` interacts with the database. Since this class belongs to the data layer, we will use the `@Repository` annotation instead of the generic `@Component` annotation. `PlayerDao` methods execute various queries to manipulate rows of the `Player` table.

## Demonstrated Concepts

### `SELECT *` query

The `getAllPlayers` method in the `PlayerDao` class returns all rows from the `Player` table. The return type will be `List&ltPlayer`. This method will execute the `SELECT *` query. We will autowire the `JdbcTemplate` in the `PlayerDao` class. `JdbcTemplate` offers a number of methods.

```java
@Autowired
JdbcTemplate jdbcTemplate;
```

We make use of the query method of `JdbcTemplate` to execute a `SELECT *` query.

A row mapper is used to match the data coming from the database to the attributes of the bean. The `BeanPropertyRowMapper` is the default row mapper defined by Spring.

```java
public List<Player> getAllPlayers() {
    String sql = "SELECT * FROM PLAYER";
    return jdbcTemplate.query(sql, new BeanPropertyRowMapper<Player> (Player.class));
}
```

### Executing the query

To run this query, we use the `CommandLineRunner`, which is an interface in Spring Boot. `CommandLineRunner` has only one method called `run`, which is launched as soon as the context is loaded.

Our `TennisPlayerApplication` implements the `CommandLineRunner`. 

We autowire the `PlayerDao` class to use an object of this class to call the `getAllPlayers` method inside the `run` method of the `CommandLineRunner`. 

A logger will display the list of players returned.

```java
@SpringBootApplication
public class TennisPlayerApplication implements CommandLineRunner {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    PlayerDao dao;
 
    public static void main(String[] args) {
        SpringApplication.run(TennisPlayerApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        logger.info("All Players Data: {}", dao.getAllPlayers());
    }
}
```

Run the application and look for `“All Players Data:”` towards the end of the terminal output. It can be seen that three rows have been retrieved from the database and are displayed in the log.

The database can be viewed in the web browser.

We have an embedded database in our classpath. Spring Boot autoconfiguration sees the database and autoconfigures a `datasource`. This leads to a `JdbcTemplate` getting autoconfigured.

### `SELECT` with a `WHERE` clause

If we search by the primary key, we will get one row back. In this case, instead of using the query method, we will use the `queryForObject` method of `JdbcTemplate`. This method accepts a list of parameters. We will create a list of objects and pass it to the method. The parameter `id` will be substituted in the query and a `Player` object is returned.

```java
public Player getPlayerById(int id) {
    String sql = "SELECT * FROM PLAYER WHERE ID = ?";
    return jdbcTemplate.queryForObject(sql, 
                                       new BeanPropertyRowMapper<Player>(Player.class), 
                                       new Object[] {id});
}
```

To execute this query, we will call `getPlayerById` in the `run` method and pass an `id`, for example, 3 to it:

```java
@Override
public void run(String... args) throws Exception {
    //logger.info("All Players Data: {}", dao.getAllPlayers());
    logger.info("Player with Id 3: {}", dao.getPlayerById(3));
}
```








