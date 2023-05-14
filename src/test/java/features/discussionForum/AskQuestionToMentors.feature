Feature: API test for Open School: discussion-forum-controller
  Description: The purpose of these tests is to cover all flows connected with asking questions to the mentors
  Open School Swagger URL: http://open-school-dev.eu-central-1.elasticbeanstalk.com/swagger-ui/index.html

  Background:
    Given Setup Rest Assured
    When Login by valid "userEmail" email and "userPsd" password
    Then Get authorized user's id

  Scenario: Creating a new questions to the mentor
    Then Enroll course for the user
    When Find users enrolled courses by course status
    And Create question to mentor
    Then Status code should be 201
    And Validate the response body by json schema
    Then Delete the question to the mentor

  Scenario: Creating a new questions to the mentor with not enrolled course is not possible
    And Create question to mentor for the not enrolled course
    Then Status code should be 403
    And Validate error message when course is not enrolled

  Scenario: Creating a new questions to the mentor with invalid course is not possible
    And Create question to the mentor with non-existed course
    Then Status code should be 400
    And Validate error message

  Scenario Outline: Validate that the questions to the mentor should have up to 500 symbols including spaces
    Then Enroll course for the user
    When Find users enrolled courses by course status
    And Create question to mentor where the user has up to <symbolsCount> symbols
    Then Get mentorsQuestionId
    Then Status code should be 201
    And Validate the response body by json schema
    Then Delete the question to the mentor
    Examples:
      | symbolsCount |
      | 499          |
      | 500          |

  Scenario Outline: Creating questions to the mentor with the symbols greater than 500 is not possible
    Then Enroll course for the user
    When Find users enrolled courses by course status
    And Create question to mentor where the user has up to <symbolsCount> symbols
    Then Status code should be 400
    Then Validate error message about question length
    Then Delete enrolled course
    Examples:
      | symbolsCount |
      | 501          |
      | 502          |
