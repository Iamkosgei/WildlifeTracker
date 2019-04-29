# The Salon
A java spark app for a hair salon. Where the owner is able to add a list of the stylists, and for each stylist, 
add clients who see that stylist. The stylists work independently, so each client only belongs to a single stylist.

## Technologies and frameworks used
    1. java 11
    2. spark core 2.12
    3. Gradle 4.10
    4. Spark Template Velocity
    5. Junit 5
    6. Postgres database

## Database

In PSQL:

    CREATE TABLE animals(id SERIAL PRIMARY KEY,health varchar, age varchar, type varchar,name varchar);
    CREATE TABLE locations(id SERIAL PRIMARY KEY, name varchar);
    CREATE TABLE rangers(id SERIAL PRIMARY KEY, firstname varchar, lastname varchar , badgenumber int);
    CREATE TABLE sightings(id SERIAL PRIMARY KEY, ranger varchar , location varchar, animalid int);
    
## Testing

   ```java
    gradle test
```

## License
[![MIT Licence](https://badges.frapsoft.com/os/mit/mit-125x28.png?v=103)](LICENSE)