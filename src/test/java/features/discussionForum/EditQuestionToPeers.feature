Feature: API test for Open School: course-controller
  Description: The purpose of these tests is to cover all flows connected with updating questions to the
  Open School Swagger URL: http://open-school-dev.eu-central-1.elasticbeanstalk.com/swagger-ui/index.html

  Background:
    Given Setup Rest Assured
    When Login by valid "userEmail" email and "userPsd" password
    Then Get authorized user's id
    Then Enroll course for the user
    When Find users enrolled courses by course status
    And Create questions for the specified course

  Scenario: Updating question to the peers
    Then Edit the question to the peers
    Then Status code should be 200
    And Validate the response body by json schema
    Then Delete the question to the peers

  Scenario: Updating question to the peers without right questionId is not possible
    Then Edit the question to the peers with wrong id
    Then Status code should be 400
    And Validate error message
    Then Delete the question to the peers
