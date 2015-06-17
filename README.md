# movie-service

A sample micro-service using Java and Spring, with tests using Cucumber, Serenity BDD, Rest-Assured and Spock

To set up this project on your machine, you will need:

  - [Gradle](https://gradle.org/)
  - [MongoDB](https://www.mongodb.org/)
  
To run the unit, integration acceptance tests, run:
```
gradle verify
```

To run the application, run:
```
gradle run
```

The following URLs can be useful:

  - [http://localhost:8080/health/](http://localhost:8080/health/) - application health checks
  - [http://localhost:8080/movies/admin/seed](http://localhost:8080/movies/admin/seed) - Seed the database with sample data
  - [http://localhost:8080/movies/](http://localhost:8080/movies/) - List the movies in the catalog
  - [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html) - View the Swagger API documentation
