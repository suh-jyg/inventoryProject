# Inventory Project

### Requirements:
- H2 (preferably v1.4.200)
- JDK 11
- Maven
- IDE (preferably IntelliJ)

### Precedure:
1. Download H2 database (v1.4.200 for safety) in the [link] (https://www.h2database.com/html/download-archive.html). If accidentally reinstalled H2, delete ~/test.mv.db in case it gives an error. 
2. In H2/bin directory, give authorizations by `chmod 755 h2.sh` in terminal, then run H2 by `./h2.sh`. For windows, run `./h2.bat`
3. Clone this repository: `https://github.com/suh-jyg/inventoryProject.git`
4. Get to the inventoryProject directory by `cd inventoryProject>`
5. Run by: `mvn clean spring-boot:run`. Or, if using IntelliJ, go to the `InventoryProjectApplication.java` files, then press Green arrow that is next to `SpringApplication.run(...)` after resolving maven dependencies. 
6. Open the browser and type: [http://localhost:8080](http://localhost:8080)
7. If having an issue with connecting to the db, type: [http://localhost:8080/h2-console](http://localhost:8080/h2-console) and under `JDBC URL` section, type `jdbc:h2:mem:testdb`.

### About this project:
- Basic CRUD functionality, which creates, edit, delete, view list of the inventories
- Ability to create locations, and assign an inventory to a location.
- Unit testing and integrated testing to ensure that the above functionality is working as intended. 
- Designed the project as scalable as possible, and version controlled. 

### It can be developed further by:
- Applying restrictions to the variables (such as: cannot be null, or no duplicates, but then error handling should be implemented accordingly)
- Allowing location to have multiple inventories. 
- More front-end side implementation can be done using bootstrap. The project designed html files to be scalable by fragmentizing the html files. 


### Technologies:
- Spring Boot
- Spring Data JPA
- H2 Database
- Thymeleaf

