Feature: API test for Open School register functionality
  Description: The purpose of these tests is to cover registration flow
  Open School Swagger URL : http://open-school-dev.eu-central-1.elasticbeanstalk.com/swagger-ui/index.html

  Background:
    Given Setup Rest Assured


  Scenario: Test student's registration API
    When Register random student
    Then Validate entity creation status
    Then Validate student registration response


  Scenario Outline: Student with invalid credentials is not able to register
    When Check registration fail if data is not correct "<firstname>" firstname, "<lastname>" lastname, "<email>" email, "<password>" password
    Then Status code should be <statusCode>

    Examples:
      | firstname    | lastname   | email              |  password  | statusCode|
      | Test         |   t        | testmail           |     hjk1   |  400      |