Feature: API test for Open School: peers-answer-controller and mentor-answer-controller
  Description: The purpose of these tests is to cover all flows connected with answers to the mentors and the peers
  Open School Swagger URL : http://open-school-dev.eu-central-1.elasticbeanstalk.com/swagger-ui/index.html

  Background:
    Given Setup Rest Assured
    When Login by valid "userEmail" email and "userPsd" password

  Scenario: Creating a answer to the peers' questions
    Then Get authorized user's id
    Then Enroll course for the user
    And Create questions for the specified course
    Then Add answer for the just creating question
    Then Status code should be 201
    And Validate the response body by json schema
    Then Delete the question to the peers
