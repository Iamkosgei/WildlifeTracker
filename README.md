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

## ScreenShots
![WildLife](https://user-images.githubusercontent.com/14147462/56939603-ef9a6580-6b11-11e9-96a0-7cc2377783b0.png)

This is the homepage and it comprises a list of all the rangers registered in the system.

![WildLife2](https://user-images.githubusercontent.com/14147462/56939625-16589c00-6b12-11e9-9aca-e0a59e7f7ef9.png)
This page displays a table of all sigthings.

![WildLife3](https://user-images.githubusercontent.com/14147462/56939636-28d2d580-6b12-11e9-9d42-78542a8ae0e1.png)
This is where a table of all animals is displayed and new animals can be added into the system.

![WildLife4](https://user-images.githubusercontent.com/14147462/56939652-39834b80-6b12-11e9-8a7a-1417da5a39cb.png)
The table above displays all available locations in the forest and other locations can be added.


## License
[![MIT Licence](https://badges.frapsoft.com/os/mit/mit-125x28.png?v=103)](LICENSE)