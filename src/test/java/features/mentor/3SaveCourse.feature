Feature: API test for Open School: user-mentor-controller
  Description: The purpose of these tests is to cover all flows connected with save courses for mentors and searching mentor's courses
  Open School Swagger URL : http://open-school-dev.eu-central-1.elasticbeanstalk.com/swagger-ui/index.html

  Background:
    Given Setup Rest Assured
    When Login by valid "userEmail" email and "userPsd" password

  Scenario: Save a course for a mentor
    Then Get authorized user's id
    Then Save a course for mentor
    Then Status code should be 200
    Then Validate save course for mentors response body by jsonSchema
    And Delete the course saved by the mentor
    Then Check that saved course is deleted


  Scenario: Save a not-existing course for a mentor is impossible
    Then Get authorized user's id
    Then Save a not-existing course for a mentor
    Then Status code should be 400
    Then Validate error message about saving not-existing course for a mentor

  Scenario: Find mentor's courses
    Then Get authorized user's id
    And Get mentor's courses
    Then Status code should be 200

  Scenario: Get not-existing mentor's courses is impossimble
    When Get not-existing mentor's courses
    Then Status code should be 400
    And Validate error message about getting not-existing mentor's courses
