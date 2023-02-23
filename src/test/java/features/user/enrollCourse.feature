Feature: API test for Open School: user-controller
  Description: The purpose of these tests is to cover all flows connected with the enroll course
  Open School Swagger URL : http://open-school-dev.eu-central-1.elasticbeanstalk.com/swagger-ui/index.html

  Background:
    Given Setup Rest Assured
    When Login by valid "userEmail" email and "userPsd" password

  Scenario:Student enroll course
    Then Get authorized user's id
    Then Enroll course for the user
    Then Status code should be 201
    And Validate enroll course for the user response body by jsonSchema
    Then Get just enrolled courseId
    Then Delete enrolled course

  Scenario:Student is not able to enroll the course by invalid userID
    Then Get authorized user's id
    Then Enroll course for non-existed user
    Then Status code should be 400
    And Validate error message

  Scenario:Student is not able to enroll the course by invalid courseId
    Then Get authorized user's id
    Then Enroll non-existed course for the user
    Then Status code should be 400
    And Validate error message