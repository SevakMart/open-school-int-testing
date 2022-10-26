Feature: API test for Open School login functionality
  Description: The purpose of these tests is to cover login flows for users
  Open School Swagger URL : http://open-school-dev.eu-central-1.elasticbeanstalk.com/swagger-ui/index.html

  Background:
    Given Setup Rest Assured

  Scenario Outline: User with valid credentials is able to login
    When Login by valid "<password>" password and "<email>" email
    Then Status code should be 200
    And Validate login success response values

    Examples:
      | password     | email                        |
      | Test1234*    | anidarbinyan14@gmail.com     |


  Scenario Outline: User with invalid credentials is not able to login
    When Login by invalid "<password>" password and "<email>" email
    Then Status code should be 401
    And Validate login error response values
    And Verify login error message

    Examples:
      | password     | email                        |
      | Tgggg        | test                         |
      | Tgggg        | anidarbinyan14@gmail.com     |
