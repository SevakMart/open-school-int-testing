Feature: API test for Open School: course-controller
  Description: The purpose of these tests is to cover all flows connected with FAQs section
  Open School Swagger URL : http://open-school-dev.eu-central-1.elasticbeanstalk.com/swagger-ui/index.html

  Background:
    Given Setup Rest Assured
    When Login by valid "adminEmail" email and "adminPsd" password

  Scenario: Create course
    And Create course
    Then Status code should be 201
    Then Validate by jsonSchema created course
    Then Delete course

