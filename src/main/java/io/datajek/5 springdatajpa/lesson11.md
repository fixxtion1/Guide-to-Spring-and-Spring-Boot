# Lesson 11: Defining an Entity

## Demonstrated Concepts

### JPA dependency

To use Spring Data JPA, we will add the starter JPA dependency to the pom.xml file:

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>
```

#### `@Entity`

In the `Player` class, we have some attributes of a tennis player like his name, nationality, date of birth, and a number of titles won. We need to tell JPA that the objects of this class need to be mapped to a table in a database. JPA will create a table with the same name as the class and create columns for all the members of the class. Every instance of the `Player` class will become a row in the `Player` table. 

We use the `@Entity` annotation to map this class to the `Player` table.

```java
@Entity
@Table(name="Player")
Public class Player {

}
```

#### `@Table`

In case we want to map a class to a table with a different name, we can use the `@Table` annotation and provide the name of the table to which the bean maps to. However, if the name of the entity and table match, we do not need the `@Table` annotation.

#### `@Id` and `@GeneratedValue`

Every table in a relational database has a primary key. The `@Id` annotation is used to indicate the primary key.

We can also let JPA generate the primary key value when we insert rows. The `@GeneratedValue` annotation will automatically generate Id values.

```java
@Entity
public class Player {
    @Id
    @GeneratedValue
    private int id;
    private String name;
    private String nationality;
    private Date birthDate;
    private int titles;
    // ...
}
```

Now whenever a new row is inserted, we do not need to supply the `Id` value. We need another constructor that initializes all attributes except `Id`.

```java
//constructor without Id attribute
public Player(String name, String nationality, Date birthDate, int titles) {
    super();
    this.name = name;
    this.nationality = nationality;
    this.birthDate = birthDate;
    this.titles = titles;
}
```

#### `@Column`

`@Column` annotation is used to define column mappings. It mentions the name of the column that matches an attribute of the class. For example:

```java
@Entity
public class Player {
    @Id
    @GeneratedValue
    private int id;
    private String name;
 
    @Column(name="nationality")
    private String nationality;
 
    private Date birthDate;
    private int titles;
    //...
}
```

However, if the column and attribute names match, the column mapping annotation is not needed.

#### JPA schema creation

The Spring Boot autoconfiguration triggers a schema update and creates a table by the same name as the class marked with the `@Entity` annotation. 

When using JPA, we do not need to create a table. We will comment out the table creation query in `schema.sql` as it is not needed anymore.

After running the application, it can be confirmed from the H2 console — `http://localhost:8080/h2-console` (using JDBC URL: `jdbc:h2:mem:testdb`) — that a `Player` table has been created.

> __Note from `ginny100`:__ 
> - In this repo, the package `io.datajek.springdata.tennisplayer` is for Chapter 4 and `io.datajek.springdatajpa.tennisplayer` is for Chapter 5.
> 
> - Chapter 4 requires the `schema.sql` and `data.sql` files in order to run successfully, but Chapter 5 does not. Therefore, I highly recommend create a whole new project using Spring Initializr dedicated for Chapter 5 with the `io.datajek.springdatajpa.tennisplayer` package in the source code and the `schema.sql` and `data.sql` files removed.

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
