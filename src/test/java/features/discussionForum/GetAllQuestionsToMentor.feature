Feature: API test for Open School: discussion-forum-controller
  Description: The purpose of these tests is to cover all flows connected with getting all questions to mentor
  Open School Swagger URL: http://open-school-dev.eu-central-1.elasticbeanstalk.com/swagger-ui/index.html

  Background:
    Given Setup Rest Assured
    When Login by valid "userEmail" email and "userPsd" password
    Then Get authorized user's id
    Then Enroll course for the user
    When Find users enrolled courses by course status
    And Create question to mentor

  Scenario: Find all questions to mentors
    When Get all questions to mentors
    Then Status code should be 200
    And Validate all answers response body by json schema
    Then Delete the question to the mentor
    Then Delete enrolled course

  Scenario: Find all questions to mentors without registration is impossible
    When Get all questions to mentors without registration
    Then Status code should be 401
    And Validate error message about unauthorized user
    Then Delete the question to the mentor
    Then Delete enrolled course