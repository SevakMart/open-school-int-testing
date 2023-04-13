Feature: API test for Open School: course-controller
  Description: The purpose of these tests is to cover all flows connected with deleting questions to the peers
  Open School Swagger URL: http://open-school-dev.eu-central-1.elasticbeanstalk.com/swagger-ui/index.html

  Background:
    Given Setup Rest Assured
    When Login by valid "userEmail" email and "userPsd" password
    Then Get authorized user's id
    Then Enroll course for the user
    When Find users enrolled courses by course status
    And Create questions for the specified course

  Scenario: Deleting question to the peers
    Then Deleting the question to the peers
    Then Status code should be 204
    Then Delete enrolled course

  Scenario: Deleting question to the peers with wrong question id is not possible
    Then Deleting the question to the peers with wrong id
    Then Status code should be 400
    And Validate error message
    Then Delete the question to the peers