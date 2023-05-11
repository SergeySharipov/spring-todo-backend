CREATE TABLE task(
    id INTEGER IDENTITY NOT NULL PRIMARY KEY,
    title VARCHAR(300) NOT NULL,
    description VARCHAR(1000) NOT NULL,
    completed BIT(1),
);