Feature: API test for Open School login functionality
  Description: The purpose of these tests are to cover login flows for users
  Open School Swagger URL : http://open-school-dev.eu-central-1.elasticbeanstalk.com/swagger-ui/index.html

  Scenario: User with valid credentials is able to login
    Given Setup Rest Assured
    When  Login by valid Test1234* password and anidarbinyan14@gmail.com email