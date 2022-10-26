Feature: API test for Open School: mentor-controller
  Description: The purpose of these tests is to cover all flows connected with mentors
  Open School Swagger URL : http://open-school-dev.eu-central-1.elasticbeanstalk.com/swagger-ui/index.html

  Background:
    Given Setup Rest Assured
    When Login by valid "Test1234*" password and "anidarbinyan14@gmail.com" email

  Scenario: Find all mentors
    When Get all mentors
    Then Status code should be 200
