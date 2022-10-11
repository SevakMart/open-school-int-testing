Feature: API test for Open School login functionality
  Description: The purpose of these tests is to cover login flows for users
  Open School Swagger URL : http://open-school-dev.eu-central-1.elasticbeanstalk.com/swagger-ui/index.html

  Scenario Outline: User with valid credentials is able to login
    Given Setup Rest Assured
    When Login by valid <password> password and <email> email
    Then Status code should be <statusCode>
    Then Validate response values

    Examples:
      | password     | email                        | statusCode   |
      | Test1234*    | anidarbinyan14@gmail.com     | 200          |
      | Tgggg        | test                         | 401          |
      | Tgggg        | anidarbinyan14@gmail.com     | 401          |


  Scenario: Test student's registration API
    Given Setup Rest Assured
    When Register random student
    Then Validate entity creation status
    Then Validate student registration response


  Scenario Outline: Student with invalid credentials is not able to register
    Given Setup Rest Assured
    When Check registration fail if data is not correct "<firstname>" firstname, "<lastname>" lastname, "<email>" email, "<password>" password
    Then Status code should be <statusCode>

    Examples:
      | firstname    | lastname   | email              |  password  | statusCode|
      | Test         |   t        | testmail           |     hjk1   |  400      |