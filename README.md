10-10-25

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
Working on Rate Limit GlobalBucket and IPBucket. Tested but not working. working on errors.
will start dealing with fingerprintdevices as well have not started
  I also added as part of the extra deliverable a GetMapping where user can look for low quantity based on the threshold that was inserted as a parameter.


Features:
  CRUD-Create, Read, Update, and Delete endpoints for Product.
  DTO mapping for clean data transfer.
  Lombok annotations for boilerpoint reduction.
  Basic validations for product.
  Basic error input handlers for product

Next Steps:
Trying to make rate limiter to work and get fingerprint

