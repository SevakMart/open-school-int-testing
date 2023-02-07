Feature: API test for Open School: user-mentor-controller
  Description: The purpose of these tests is to cover all flows connected with saved mentors
  Open School Swagger URL : http://open-school-dev.eu-central-1.elasticbeanstalk.com/swagger-ui/index.html

  Background:
    Given Setup Rest Assured
    When Login by valid "adminEmail" email and "adminPsd" password who needs to save mentor

  Scenario: Save mentors for users
    Then Get authorized user's id
    Then Save mentor for user
    Then Status code should be 201
    Then Validate saved mentor's response body by jsonSchema

  Scenario: Save another mentor for a user who already has a saved mentor
    Then Get authorized user's id
    Then Save mentor for user who already has a saved mentor
    Then Status code should be 201
    Then Validate saved mentor's response body by jsonSchema

  Scenario: Save mentor for users with invalid mentor Id is not possible
    Then Get authorized user's id
    Then Save mentor for user by invalid Mentor Id
    Then Status code should be 400
    Then Validate saved mentor's response message when mentor Id is invalid

  Scenario: Find saved mentors by userId
    Then Get authorized user's id
    Then Get saved mentors with authorized user's id
    Then Status code should be 200
    Then Validate saved mentor's by userId response body by jsonSchema

  Scenario: Find saved mentors by invalid userId is not possible
    Then Get saved mentors filling invalid userId
    Then Status code should be 400
    Then Validate  get saved mentor's by invalid userId response error message

  Scenario: Search saved mentors without naming
    Then Get authorized user's id
    Then Get saved mentors without naming
    Then Status code should be 200
    Then Validate saved mentor's by userId response body by jsonSchema

  Scenario Outline: Search saved mentors by existing name
    Then Get authorized user's id
    Then Get saved mentors by <name>
    Then Status code should be 200
    Then Validate saved mentor's by userId response body by jsonSchema
    Then Validate the count of mentors by name from response
    Examples:
      | name        |
      | "Arevik"    |
      | "arevik"    |
      | "vik"       |
      | "Arakelyan" |
      | "Mentor"   |
      | "mentoryan"   |
      | "ment"       |

  Scenario Outline: Search saved mentors by invalid names
    Then Get authorized user's id
    Then Get saved mentors by <name>
    Then Status code should be 200
    Then Validate saved mentor's by userId response body by jsonSchema
    Then Validate if the response body contains all saved mentors
    Examples:
      | name       |
      | " "        |
      | "     "    |
      | "ddsdfsd " |
      | "-1 "      |
      | "### "     |

  Scenario: Check the response name is the same as the one being searched for
    Then Get authorized user's id
    Then Get saved mentors name
    Then Status code should be 200
    Then Validate the response name is the same as the one being searched for

  Scenario: Find saved mentors by userId who definitely has saved mentors
    Then Get authorized user's id
    Then Get saved mentor by userId
    Then Status code should be 200
    Then Validate that the response body is not empty






