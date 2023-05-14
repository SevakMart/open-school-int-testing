Feature: API test for Open School: discussion-forum-controller
  Description: The purpose of these tests is to cover all flows connected with getting questions and answers to the peers by id
  Open School Swagger URL: http://open-school-dev.eu-central-1.elasticbeanstalk.com/swagger-ui/index.html

  Background:
    Given Setup Rest Assured
    When Login by valid "userEmail" email and "userPsd" password
    Then Get authorized user's id
    Then Enroll course for the user
    When Find users enrolled courses by course status
    And Create questions for the specified course

  Scenario: Find all answers related to the provided question
    Then Add answer to the peers question
    When Get all answers to peers
    Then Status code should be 200
    And Validate all answers response body by json schema
    Then Deleting the question to the peers
    Then Delete enrolled course

  Scenario: Getting all answers related to the provided question without registered impossible
    Then Add answer to the peers question
    When Get all answers to peers without registration
    Then Status code should be 401
    And Validate error message about unauthorized user
    Then Deleting the question to the peers
    Then Delete enrolled course

  Scenario: Find all questions to peers related to the provided question
    When Get all questions to peers
    Then Status code should be 200
    And Validate all questions response body by json schema
    Then Deleting the question to the peers
    Then Delete enrolled course

  Scenario: Getting all questions to peers related to the provided question without registered impossible
    When Get all questions to peers without registration
    Then Status code should be 401
    And Validate error message about unauthorized user
    Then Deleting the question to the peers
    Then Delete enrolled course