# Lesson 2: Setting up the H2 Database

## Demonstrated Concepts

### Configuring database connection

The in-memory database H2 has automatically been configured in our application. The URL can be found from the console log. This value is randomly generated each time the server is restarted. 

To make the database URL a constant, we need to configure this in `application.properties` as follows:

```properties
spring.datasource.url=jdbc:h2:mem:testdb
```

Next, we need to connect to the H2 database. One of the reasons for using Spring Boot is that its autoconfiguration feature looks at the H2 dependency and automatically configures a connection to the H2 database. 

The H2 console can be enabled in the `application.properties` file as follows:

```properties
spring.h2.console.enabled=true
```

The database can be viewed in the web browser by typing `localhost:8080/h2-console` or `http://127.0.0.1:8080/h2-console`. 

In the login page that shows up, make sure that the JDBC URL is the same as the one that we provided in the `applications.properties` file (`jdbc:h2:mem:testdb`). If not, change it to `jdbc:h2:mem:testdb` and click connect to go to the database console. This will open up the interface of the database.

### Creating a table

We can create a table in the H2 web console by using a `CREATE TABLE` query, but since this is an in-memory database, all changes will be lost when the application is terminated. A better way is to define the query in a SQL file.

We create an SQL file in __src/main/resources__ called `schema.sql`. This file will be called whenever the application is launched and will initialize the database. It contains all the DDL (Data Definition Language) queries. We will create a table called Player in this file:

```sql
CREATE TABLE Player (
     ID INTEGER NOT NULL,
     Name VARCHAR(255) NOT NULL,
     Nationality VARCHAR(255) NOT NULL,
     Birth_date TIMESTAMP,
     Titles INTEGER,
     PRIMARY KEY (ID)
);
```

After saving this file, if we connect to the H2 console again, we will see the `Player` table with five columns. To view this table, we can write a simple query:

```sql
SELECT * FROM Player;
```

The table is empty at the moment.

### Inserting data

We can insert data in the table from the H2 console but, as mentioned in the previous step, it will be lost when the application stops. 

To avoid inserting data every time we restart the application, we will create another SQL file in the __src/main/resources__ folder and add all our `INSERT` queries to that file. This file is called `data.sql` and it contains all DML (Data Manipulation Language) queries.

```sql
INSERT INTO Player (ID, Name, 
Nationality, Birth_date, Titles)
VALUES(1, 'Djokovic', 'Serbia', '1987-05-22', 81);

INSERT INTO Player (ID, Name, 
Nationality, Birth_date, Titles)
VALUES(2, 'Monfils', 'France', '1986-09-01', 10);

INSERT INTO Player (ID, Name, 
Nationality, Birth_date, Titles)
VALUES(3, 'Isner', 'USA', '1985-04-26', 15);
```

Now if we run the application, the data is read from the `data.sql` file and the `Player` table is populated. We can run the `SELECT * FROM Player;` query to confirm that our table has 3 rows.

In the login page that shows up, make sure that the JDBC URL is the same as the one that we specified in step 1 (`jdbc:h2:mem:testdb`).

> __Note from `ginny100`:__ Since I don't customize the h2 database configuration properties in the `application.properties` file, the information used in the h2-console login page is all default.
> 
> | Field          | Value                 |
> |----------------|-----------------------|
> | Saved Settings | Generic H2 (Embedded) |
> | Setting Name   | Generic H2 (Embedded) |
> | Driver Class   | org.h2.Driver         |
> | JDBC URL       | jdbc:h2:mem:testdb    |
> | User Name      | sa                    |
> | Password       |                       |
