Feature: API test for Open School: user-controller
  Description: The purpose of these tests is to cover all flows connected with the enroll course
  Open School Swagger URL : http://open-school-dev.eu-central-1.elasticbeanstalk.com/swagger-ui/index.html

  Background:
    Given Setup Rest Assured
    When Login by valid "userEmail" email and "userPsd" password

  Scenario:Find users enrolled courses by course status
    Then Get authorized user's id
    Then Enroll course for the user
    When Find users enrolled courses by course status
    Then Status code should be 200
    Then Delete enrolled course