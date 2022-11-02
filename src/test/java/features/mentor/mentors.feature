Feature: API test for Open School: mentor-controller
  Description: The purpose of these tests is to cover all flows connected with mentors
  Open School Swagger URL : http://open-school-dev.eu-central-1.elasticbeanstalk.com/swagger-ui/index.html

  Background:
    Given Setup Rest Assured

  Scenario Outline: Find all mentors
    When Login by valid "<email>" email and "<password>" password
    When Get all mentors
    Then Status code should be 200
    Examples:
      | email     | password |
      | userEmail | userPsd  |
