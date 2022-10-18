Feature: API test for Open School login functionality
  Description: The purpose of these tests is to cover login flows for users
  Open School Swagger URL : http://open-school-dev.eu-central-1.elasticbeanstalk.com/swagger-ui/index.html

  Background:
    Given Setup Rest Assured

  Scenario Outline: User with valid credentials is able to login
    When Login by valid "<password>" password and "<email>" email
    Then Status code should be <statusCode>
    And Validate login success response values

    Examples:
      | password     | email                        | statusCode   |
      | Test1234*    | anidarbinyan14@gmail.com     | 200          |
      | Password1!   | sevakmart@gmail.com          | 200          |


  Scenario Outline: User with invalid credentials is not able to login
    When Login by invalid "<password>" password and "<email>" email
    Then Status code should be <statusCode>
    And Validate login error response values
    And Verify login error message

    Examples:
      | password     | email                        | statusCode   |
      | Tgggg        | test                         | 401          |
      | Tgggg        | anidarbinyan14@gmail.com     | 401          |
