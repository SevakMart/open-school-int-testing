Feature: API test for Open School password reset and forget functionality
  Description: The purpose of these tests is to cover password reseting flow for users
  Open School Swagger URL : http://open-school-dev.eu-central-1.elasticbeanstalk.com/swagger-ui/index.html

  Background:
    Given Setup Rest Assured

  Scenario: User with valid email is able to get token via email in case of user forgets its password
    Given Request of password forget with "anidarbinyan14@gmail.com" email
    Then Status code should be 200
    And Verify forgot password success message


  Scenario: User with invalid email is not able to get token via email in case of user forgets its password
    Given Request of password forget with "anidarbinyan@gmail.com" email
    Then Status code should be 400
    And Verify forgot password error message

  Scenario: User can reset password
    Given Request of password forget with "anidarbinyan14@gmail.com" email
    Then Get reset password token by "anidarbinyan14@gmail.com" email
    Then Make request with token, new password and confirmed password "Test1234*"
    And Verify reset password success message
