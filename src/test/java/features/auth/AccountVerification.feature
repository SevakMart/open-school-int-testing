Feature: API test for Open School account verification functionality
  Description: The purpose of these tests is to cover account verification login flow
  Open School Swagger URL : http://open-school-dev.eu-central-1.elasticbeanstalk.com/swagger-ui/index.html

  Background:
    Given Setup Rest Assured

  Scenario: User resends email for account verification
    Given Prepare user for account verification
    Then Resend email for account verification
    And Verify account verification success status code

  Scenario Outline: Invalid user is not able to resend email for account verification
    Given Invalid user <user> requests for account verification
    And Verify account verification error status code

    Examples:
      | user |
      | null |
      | -1   |
      | 1v   |
      | #*   |
