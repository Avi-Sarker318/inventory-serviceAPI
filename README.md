10-8-25

This project is based on Spring Boot- Based Inventory Management API that allows basic CRUD operations for product data.
Its designed following clean architecture principles - separating:
controller,
services,
entities,
DTO,
and repository

The current Version include
  controller,
  service,
  entities,
  DTO,
  config,
  response,
  and repository
  Setup GlobalHandleException which is implemented and tested
  Setup a simple CorsConfig that is implemented but not tested
  Setup a simple RateLimiter that is implemented and tested
  Added a Repository where user can look for low quantity based on the threshold that was inserted as a parameter.


Features:
  CRUD-Create, Read, Update, and Delete endpoints for Product.
  DTO mapping for clean data transfer.
  Lombok annotations for boilerpoint reduction.
  Basic validations for product.
  Basic error input handlers for product

Next Steps:
  Adding more new features.
  creating a better corsconfig and ratelimiter
  test corsconfig
  use more tests for postman


  Although most of this is done, I have tested everything making sure it works. 
