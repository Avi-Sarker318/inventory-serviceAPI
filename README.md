10-7-25

This project is based on Spring Boot- Based Inventory Management API that allows basic CRUD operations for product data.
Its designed following clean architecture principles - separating:
-controller
-services
-entities
-DTO
-repository

The current Version include
  -controller
  -service
  -entities
  -DTO
  -Repository
  -Early Setup Global Exception Handler and Api Formatting(not fully implemented or tested yet)


Features:
  CRUD-Create, Read, Update, and Delete endpoints for Product
  DTO mapping for clean data transfer
  Lombok annotations for boilerpoint reduction 
  Basic validations for product

Next Steps:
  Finalize global exception handler logic
  Integrate API response wrapper with all controllers
  Test API with postman
