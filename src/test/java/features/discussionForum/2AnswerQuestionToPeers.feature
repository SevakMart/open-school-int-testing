Feature: API test for Open School: discussion-forum-controller
  Description: The purpose of these tests is to cover all flows connected with adding answers to the peers
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

  Scenario Outline: Validate that the questions to the peers should have up to 5oo symbols including spaces
    Then Enroll course for the user
    When Find users enrolled courses by course status
    And Create questions for the specified course
    When Create answer where the user has up to <symbolsCount> symbols
    Then Status code should be 201
    And Validate the answer response body by json schema
    Then Delete the question to the peers
    Examples:
      | symbolsCount |
      | 499          |
      | 500          |

  Scenario Outline: Creating answer to the peers question with the symbols greater than 500 is not possible
    Then Enroll course for the user
    When Find users enrolled courses by course status
    And Create questions for the specified course
    When Create answer where the user has up to <symbolsCount> symbols
    Then Status code should be 400
    Then Validate error message about question length
    Then Delete the question to the peers
    Examples:
      | symbolsCount |
      | 501         |
      | 502          |