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
  and repository.
  Seting up GlobalHandleException which is implemented and tested.
  Setting up a simple CorsConfig that is implemented but not tested.
  Seting up a simple RateLimiter that is implemented and tested.
  I also added as part of the extra deliverable a GetMapping where user can look for low quantity based on the threshold that was inserted as a parameter.


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
