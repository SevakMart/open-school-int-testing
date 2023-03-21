Feature: API test for Open School: course-controller
  Description: The purpose of these tests is to cover all flows connected with adding questions to the
  Open School Swagger URL: http://open-school-dev.eu-central-1.elasticbeanstalk.com/swagger-ui/index.html

  Background:
    Given Setup Rest Assured
    When Login by valid "userEmail" email and "userPsd" password
    Then Get authorized user's id

  Scenario: Creating a new answer to the peers question
    Then Enroll course for the user
    When Find users enrolled courses by course status
    And Create questions for the specified course
    Then Add answer to the peers question
    Then Status code should be 201
    And Validate the answer response body by json schema
    Then Delete the question to the peers

  Scenario: Creating a new answer to not existing questionId is not possible
    Then Add answer to not existing question
    Then Status code should be 400
    And Validate error message

  Scenario: Creating a new answer to not enrolled course is not possible
    Then Enroll course for the user
    When Find users enrolled courses by course status
    And Create questions for the specified course
    Then Add answer to not enrolled course
    Then Status code should be 403
    And Validate error message when course is not enrolled
    Then Delete the question to the peers
