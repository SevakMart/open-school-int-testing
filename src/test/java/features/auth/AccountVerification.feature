Feature: API test for Open School account verification functionality
  Description: The purpose of these tests is to cover account verification login flow
  Open School Swagger URL : http://open-school-dev.eu-central-1.elasticbeanstalk.com/swagger-ui/index.html

  Background:
    Given Setup Rest Assured
    Given Prepare user for account verification

  Scenario: User is able to resend email for account verification
    Then Resend email as the previous has already expired
    And Verify account verification success status code
    Then Delete the user

  Scenario: User is not able to resend email for account verification, if the email is invalid
    Then Resend invalid email for verification
    Then Status code should be 400
    Then Validate error message
    Then Delete the user

  Scenario: Verify the created user's email
    Then Verify email
    And Verify account verification success status code
    Then Delete the user

  Scenario: Verify the created user's email is not possible when the token is invalid
    Then Verify email with invalid token
    Then Status code should be 400
    Then Validate error message about invalid token
    Then Delete the user

  Scenario: Verify the created user's email is not possible when the verification token is expired
    Then Verify email when the verification token is expired
    Then Status code should be 400
    Then Validate error message about invalid token
    Then Delete the user

  Scenario: After verification the user isn't able to resend email for account verification
    Then Verify email
    Then Resend email to the verified user
    Then Status code should be 400
    And Validate error message that this email is already verified
    Then Delete the user