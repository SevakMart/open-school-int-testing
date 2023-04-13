Feature: API test for Open School: course-controller
  Description: The purpose of these tests is to cover all flows connected with answering questions to the
  Open School Swagger URL: http://open-school-dev.eu-central-1.elasticbeanstalk.com/swagger-ui/index.html

  Background:
    Given Setup Rest Assured
    When Login by valid "userEmail" email and "userPsd" password

  Scenario: Creating a new questions to the peers
    Then Get authorized user's id
    Then Enroll course for the user
    When Find users enrolled courses by course status
    And Create questions for the specified course
    Then Status code should be 201
    And Validate the response body by json schema
    Then Delete the question to the peers

  Scenario: Creating a new questions to the peers with not enrolled course is not possible
    Then Get authorized user's id
    And Create questions for the not enrolled course
    Then Status code should be 403
    And Validate error message when course is not enrolled

  Scenario: Creating a new questions to the peers with invalid course is not possible
    Then Get authorized user's id
    And Create questions for the non-existed course
    Then Status code should be 400
    And Validate error message

  Scenario Outline: Validate that the questions to the peers should have up to 5oo symbols including spaces
    Then Get authorized user's id
    Then Enroll course for the user
    When Find users enrolled courses by course status
    And Create question where the user has up to <symbolsCount> symbols
    Then Get peersQuestionId
    Then Status code should be 201
    And Validate the response body by json schema
    Then Delete the question to the peers
    Examples:
      | symbolsCount |
      | 499          |
      | 500          |

  Scenario Outline: Creating questions to the peers with the symbols greater than 500 is not possible
    Then Get authorized user's id
    Then Enroll course for the user
    When Find users enrolled courses by course status
    And Create question where the user has up to <symbolsCount> symbols
    Then Status code should be 400
    Then Validate error message about question length
    Then Delete enrolled course
    Examples:
      | symbolsCount |
      | 501          |
      | 502          |
