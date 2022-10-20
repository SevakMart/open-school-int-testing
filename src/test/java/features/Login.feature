Feature: API test for Open School login functionality
  Description: The purpose of these tests are to cover login flows for users
  Open School Swagger URL : http://open-school-dev.eu-central-1.elasticbeanstalk.com/swagger-ui/index.html

  Background:
    Given Setup Rest Assured

  Scenario Outline: User with valid credentials is able to login
    When Login by valid "<password>" password and "<email>" email
    Then Status code should be <statusCode>
    And Validate login success response values

    Examples:
      | password  | email                      | statusCode |
      | Test123@# | openschooltest78@gmail.com | 200        |

