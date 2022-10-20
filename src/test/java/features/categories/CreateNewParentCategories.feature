Feature: API test for Open School: category-controller
  Description: The purpose of these tests is to cover all flows connected with creation categories
  Open School Swagger URL : http://open-school-dev.eu-central-1.elasticbeanstalk.com/swagger-ui/index.html

  Background:
    Given Setup Rest Assured
    When Login by valid Test1111! password and hdiloyan88@gmail.com email
    Then Set Admin Token

  Scenario Outline: Create new Category
    When Create Category where File path is "<filePath>" and image "<title>"
    Then Status code should be <statusCode>
    Then Validate response JsonSchema "schema/getPostedParentCategoryRequestSchema.json"
    #Then Validate new categories' response values
    Examples:
      | statusCode   | filePath                   | title  |
      | 201          | src/test/image/new1.webp   | Test   |

#  Scenario Outline: Create new category without attaching an image
#    When Create new category with "<title>"
#    Then Status code should be <statusCode>
#
#    Examples:
#      | statusCode   | title  |
#      | 400          | Test   |
#
##  Scenario Outline: Create new category without admin role
##    Given Setup Rest Assured
##    When Login by valid Test1234* password and anidarbinyan14@gmail.com email
##    When Create new category by user
##    Then Status code should be <statusCode>
##
##    Examples:
##      | statusCode   |
##      | 403          |
#
