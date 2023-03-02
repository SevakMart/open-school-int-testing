Feature: API test for Open School: mentor-controller
  Description: The purpose of these tests is to cover all flows connected with mentors
  Open School Swagger URL : http://open-school-dev.eu-central-1.elasticbeanstalk.com/swagger-ui/index.html

  Background:
    Given Setup Rest Assured
    When Login by valid "userEmail" email and "userPsd" password

  Scenario: Find all mentors without request body
    When Get all mentors
    Then Status code should be 200

  Scenario: Check find all mentors' response body by jsonSchema
    When Get all mentors
    Then Status code should be 200
    Then Validate mentors response by JsonSchema

  Scenario: Check for any page the default size of mentors
    When Get all mentors
    Then Status code should be 200
    Then Validate the default size of any page

  Scenario: Find all mentors by page
    When Get all mentors by page
    Then Status code should be 200

  Scenario: Find all mentors by size
    When Get all mentors by size
    Then Status code should be 200
    Then Validate the count of response elements' size

  Scenario: Find all mentors by page and size
    When Get all mentors by page and by size
    Then Status code should be 200

  Scenario: Find all mentors by page, size and by default ordering
    When Get all mentors by page, size and default sorting type
    Then Status code should be 200

  Scenario: Find all mentors by page, size and sorting type[desc]
    When Get all mentors by page, size and sorting type
    Then Status code should be 200

  Scenario: Check the count of mentors from response
    When Get all mentors
    Then Status code should be 200
    Then Validate the count of mentors from response

  Scenario: Find all mentors by first page
    When Get all mentors by first page
    Then Status code should be 200

  Scenario Outline: Find mentors by name
    When Get mentors by <name>
    Then Status code should be 200

    Examples:
      | name            |
      | "Mentor"        |
      | "Ment"          |
      | "M"             |
      | "AnotherMentor" |
      | "Anoth"         |
      | "Mentoryan"     |
      | "surname"       |
      | "A"             |

  Scenario Outline: Find mentors by non-existent name
    When Get mentors by <name>
    Then Status code should be 200
    Then Validate that response body returns empty content
    Examples:
      | name   |
      | "Test" |
      | "1"    |









