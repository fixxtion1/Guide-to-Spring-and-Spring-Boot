# Lesson 7: DDL Queries

## Overview

All the queries we have seen so far have been Data Manipulation Language (DML) queries where we are manipulating data in the table. In a rare scenario, we might want to create a table which is a Data Definition Language (DDL). In that case, we can use the `execute` method of the `JdbcTemplate`.

## Demonstrated Concepts

### Create table query

Let’s say we want to create a `Tournament` table to store details of a tennis tournament. We will create a method called `createTournamentTable` with a `CREATE TABLE` query.

```java
public void createTournamentTable() {
    String sql = "CREATE TABLE TOURNAMENT (ID INTEGER, NAME VARCHAR(50), LOCATION VARCHAR(50), PRIMARY KEY (ID))";
    jdbcTemplate.execute(sql);
    System.out.println("Table created");
}
```

We call the `createTournamentTable` method in the `run` method and check if a table has been created from the output log.

```java
@Override
public void run(String... args) throws Exception {
        dao.createTournamentTable();
}
```

We can verify if the table has been created from the H2 console which shows an empty Tournament table.