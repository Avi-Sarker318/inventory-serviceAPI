10-13-2025

Author 
Avi

How to Run the Project

1. Prerequisites
   Java 17+ Installed
     Verify with: java --version
   Maven installed
     Verify with: mvn -v
   Postgres running locally(or on your VM)
     You'll need a database created(e.g., inventorydb)
2. Clone the Repository
   git clone https://github.com/Avi-Sarker318/inventory-serviceAPI.git
   cd inventory-serviceAPI
3. Configure Database Connection
   Open the file
     src/main/resources/application.properties
         spring.datasource.url=jdbc:postgresql://localhost:5432/inventorydb
         spring.datasource.username=postgres
         spring.datasource.password=yourpassword
         spring.jpa.hibernate.ddl-auto=update
         spring.jpa.show-sql=true
4. Build the Project
     mvn clean install
5. Run the Applicaiton
     mvn spring-boot:run
     or just run it on intellij
6. Access the API
     open your browser or postman and test
       http://localhost:8000/api/products
   POST       Create a product          /api/products
   GET        Read all products         /api/products  // get by id /api/products/{id}
   PUT        Update a Product          /api/products/{id}
   DELETE     Delete a Product          /api/products/{id}


Notes:
I added rate limiter, fingerprintdevice, and webconfig.
I used webconfig to get my code to read the limiter first before moving to the controller
the limit for global and ip is 10 and it will reset every minute.
I added the fingerprintdevice to show useragents that are using the api and fetching data from it.
