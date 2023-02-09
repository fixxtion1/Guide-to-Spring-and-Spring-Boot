--This file content is only needed for Chapter 4 lessons. Delete it in Chapter 5

CREATE TABLE Player (
     ID INTEGER NOT NULL,
     Name VARCHAR(255) NOT NULL,
     Nationality VARCHAR(255) NOT NULL,
     Birth_date TIMESTAMP,
     Titles INTEGER,
     PRIMARY KEY (ID)
);