Feature: API test for Open School: discussion-forum-controller
  Description: The purpose of these tests is to cover all flows connected with getting questions and answers to the peers and mentors by id
  Open School Swagger URL: http://open-school-dev.eu-central-1.elasticbeanstalk.com/swagger-ui/index.html

  Background:
    Given Setup Rest Assured
    When Login by valid "userEmail" email and "userPsd" password
    Then Get authorized user's id

  Scenario: Getting question to the peers by Id
    Then Enroll course for the user
    When Find users enrolled courses by course status
    And Create questions for the specified course
    Then Get peersQuestionId
    When Get question by Id
    Then Status code should be 200
    And Validate the provided question response body by json schema
    Then Deleting the question to the peers
    Then Delete enrolled course

  Scenario: Getting question to the peers by Id with wrong questionId impossible
    Then Enroll course for the user
    When Find users enrolled courses by course status
    When Get question by the wrong questionId
    Then Status code should be 400
    And Validate error message
    Then Delete enrolled course

  Scenario: Getting question to the peers by Id with wrong enrolledCourseId impossible
    When Get question by the wrong enrolledCourseId
    Then Status code should be 400
    And Validate error message
    Then Delete enrolled course

  Scenario: Getting answer to the peers by Id
    Then Enroll course for the user
    When Find users enrolled courses by course status
    And Create questions for the specified course
    Then Add answer to the peers question
    When Get answer by Id
    Then Status code should be 200
    And Validate the provided answer response body by json schema
    Then Deleting the question to the peers
    Then Delete enrolled course

  Scenario: Getting answer to the peers by invalidId is not possible
    Then Enroll course for the user
    When Find users enrolled courses by course status
    And Create questions for the specified course
    Then Add answer to the peers question
    When Get answer by invalid Id
    Then Status code should be 400
    And Validate error message
    Then Deleting the question to the peers
    Then Delete enrolled course

  Scenario: Getting question to the mentor by Id
    Then Enroll course for the user
    When Find users enrolled courses by course status
    And Create question to mentor
    Then Get mentorsQuestionId
    When Get question to mentor by Id
    Then Status code should be 200
    And Validate the provided question response body by json schema
    Then Delete the question to the mentor

  Scenario: Getting question to the mentor by Id with wrong mentorQuestionId impossible
    Then Enroll course for the user
    When Find users enrolled courses by course status
    When Get question to mentor by the wrong questionId
    Then Status code should be 400
    And Validate error message
    Then Delete enrolled course

  Scenario: Getting question to the peers by Id with wrong enrolledCourseId impossible
    When Get question to mentor by the wrong enrolledCourseId
    Then Status code should be 400
    And Validate error message
    Then Delete enrolled course