Feature: API test for Open School: FAQs-controller
  Description: The purpose of these tests is to cover all flows connected with FAQs section
  Open School Swagger URL : http://open-school-dev.eu-central-1.elasticbeanstalk.com/swagger-ui/index.html

  Background:
    Given Setup Rest Assured
    When Login by valid "adminEmail" email and "adminPsd" password

  Scenario: Adding FAQs with the mentor and admin of the specified course
    And Create course
    And Create FAQs
    Then Status code should be 201
    And Validate response body by json schema
    Then Delete just created FAQs
    Then Delete course

  Scenario Outline: Each FAQ has one answer and  symbols including spaces
    And Create course
    And Create FAQs which answer has <symbolsCount>
    Then Status code should be 201
    And Validate response body by json schema
    Then Delete just created FAQs
    Then Delete course
    Examples:
      | symbolsCount |
      | 499          |
      | 500          |

  Scenario Outline: Each FAQ doesn't caontain symbols including spaces greater than 500
    And Create course
    And Create FAQs which answer does not contain <symbolsCount> symbols
    Then Status code should be 400
    And Validate error message about symbols size
    Then Delete course
    Examples:
      | symbolsCount |
      | 501          |
      | 502          |

  Scenario: Adding FAQs with the mentor and admin of the specified course without courseId is impossible
    And Create FAQs without courseId argument
    Then Status code should be 400
    Then Validate error message about creating FAQs by invalid request arguments

  Scenario: Adding FAQs with the mentor and admin of the specified course without answer is impossible
    And Create FAQs without answer
    Then Status code should be 400
    Then Validate error message about creating FAQs without answer

  Scenario: Adding FAQs with the mentor and admin of the specified course without question is impossible
    And Create FAQs without question
    Then Status code should be 400
    Then Validate error message about creating FAQs without question

  Scenario: Updating FAQs with the mentor and admin of the specified course
    And Create course
    And Create FAQs
    Then Status code should be 201
    And Update FAQs
    Then Status code should be 200
    And Validate response body by json schema
    Then Delete just created FAQs
    Then Delete course

  Scenario: Updating FAQs with the mentor and admin of the specified course
    And Create course
    And Create FAQs
    Then Status code should be 201
    And Update FAQs
    Then Status code should be 200
    And Validate response body by json schema
    Then Delete just created FAQs
    Then Delete course

  Scenario: Updating only question with the mentor and admin of the specified course
    And Create course
    And Create FAQs
    Then Status code should be 201
    And Update FAQs question
    Then Status code should be 200
    And Validate response body by json schema
    Then Delete just created FAQs
    Then Delete course

  Scenario: Updating only answer with the mentor and admin of the specified course
    And Create course
    And Create FAQs
    Then Status code should be 201
    And Update FAQs question
    Then Status code should be 200
    And Validate response body by json schema
    Then Delete just created FAQs
    Then Delete course

  Scenario: Updating FAQs when the mentor is not a mentor of this course
    And Update FAQs by not existed course
    Then Status code should be 400
    Then Validate error message

  Scenario: Find all FAQs only users with admin role
    And Get all FAQs
    Then Status code should be 200
    Then Validate getting FAQs by json schema

  Scenario: Find all FAQs only users with admin role by page and size
    And Get all FAQs by page and size
    Then Status code should be 200
    Then Validate getting FAQs by json schema

  Scenario: Find FAQs related to the selected courses
    And Create course
    And Create FAQs
    Then Status code should be 201
    And Get FAQs related to the selected courses
    Then Status code should be 200
    Then Validate getting FAQs by json schema
    Then Delete just created FAQs
    Then Delete course

  Scenario: Find all FAQs with mentor role is not possible
    When Login by valid "mentorEmail" email and "mentorPsd" password
    And Get all FAQs
    Then Status code should be 403
    Then Check the error message
