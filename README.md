# BIKESTORE
Boilerplate Microservice with Java 8 and Spring Boot with most part of all the basic configurations needed to deploy a microservice.

## Documentation
In order to deploy it, it is necessary only to create a database on SQL server, launch the scripts of the folder Documentation->SQL-Query 
(in order: create objects, load data, and Procedure Rename), and then create a user with the permission on that database with the username and password to overwrite 
in the file bikestores\src\main\resources\application-dev.properties. 

After that you have only to deploy with Spring Boot Application and the profile dev.

It is important to underline that this is only a boilerplate microservice, there is not a defined business, some developments are forced to try all the possible configurations 
in a Spring Boot Microservice.

## Where can I get the latest release?
You can download source and binaries from [Github page](https://github.com/LucaNocella1993/bikestores.git).


## Release Note
+ 1.0.0-SNAPSHOT
    + Initial commit of the repository
    

## N.B.
In Eclipse for the error "execution not covered by lifecycle configuration",
select Window > Preferences > Maven > Errors/Warnings > execution not covered by lifecycle configuration > Ignore.

## Contacts:

Luca Nocella

Java Developer – Naples – Italy

e-mail: luca.nocella@hotmail.com

[Linkedin page](https://www.linkedin.com/in/luca-nocella-6488aa153/)