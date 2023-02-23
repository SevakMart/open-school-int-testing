Feature: API test for Open School: peers-question-controller and mentor-question-controller
  Description: The purpose of these tests is to cover all flows connected with adding questions to the
  Open School Swagger URL : http://open-school-dev.eu-central-1.elasticbeanstalk.com/swagger-ui/index.html

  Background:
    Given Setup Rest Assured
    When Login by valid "userEmail" email and "userPsd" password

  Scenario: Creating a new questions to the peers
    Then Get authorized user's id
    Then Enroll course for the user
    And Create questions for the specified course
    Then Status code should be 201
    And Validate the response body by json schema
    Then Delete the question to the peers

  Scenario: Creating a new questions to the peers with invalid course is not possible
    Then Get authorized user's id
    And Create questions for the non-existed course
    Then Status code should be 400
    And Validate error message

  Scenario Outline: Validate that the questions to the peers should have up to 5oo symbols including spaces
    Then Get authorized user's id
    Then Enroll course for the user
    And Create question where the user has up to <symbolsCount> symbols
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
    And Create question where the user has up to <symbolsCount> symbols
    Then Status code should be 400
    Examples:
      | symbolsCount |
      | 501          |
      | 502          |

  Scenario: Creating a new questions to the mentor who has already saved this course
    When Login by valid "mentorEmail" email and "mentorPsd" password
    Then Get authorized user's id
    Then Save a course for mentor
    And Create questions for the mentor
    Then Status code should be 201
    And Validate the response body by json schema
    Then Delete the question to the peers
    And Delete the course saved by the mentor

  Scenario: Creating a new questions to the mentor with invalid course is not possible
    When Login by valid "mentorEmail" email and "mentorPsd" password
    Then Get authorized user's id
    And Creation questions for the mentor with invalid course
    Then Status code should be 201
    And Validate the response body by json schema
    Then Delete the question to the peers
    And Delete the course saved by the mentor

  Scenario: Creating a new answer to the question
    Then Get authorized user's id
    Then Enroll course for the user
    And Create questions for the specified course
    Then Status code should be 201
    And Validate the response body by json schema
    Then Delete the question to the peers