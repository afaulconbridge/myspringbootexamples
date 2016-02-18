mvn spring-boot:run

you will need to have a neo4j server running and specify the URL via 
an application.properties file e.g see src/main/resources/application.properties.example
NOTE: Anything in that server will be wiped on start.

Visit localhost:8080 to get the front page, and localhost:80800/profile to the start of
the restful self-documentation

