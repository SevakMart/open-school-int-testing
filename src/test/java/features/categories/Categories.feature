Feature: API test for Open School: category-controller
  Description: The purpose of these tests is to cover all flows connected with categories
  Open School Swagger URL : http://open-school-dev.eu-central-1.elasticbeanstalk.com/swagger-ui/index.html

  Background:
    Given Setup Rest Assured

  Scenario Outline: Find all categories and relevant subcategories by students
    When Login by valid "<password>" password and "<email>" email
    When Get all categories and relevant subcategories
    Then Status code should be <statusCode>
    Then Validate response JsonSchema "<schemaPath>"
#    Then Validate categories' response "<title>" values
    Examples:
      | password  | email                      | statusCode | schemaPath                                |
      | Test123@# | openschooltest78@gmail.com | 200        | schema/getAllCategoriesRequestSchema.json |

  Scenario Outline: Find all categories and relevant subcategories by admins
    When Login by valid "<password>" password and "<email>" email
    When Get all categories and relevant subcategories
    Then Status code should be <statusCode>
#    Then Validate response JsonSchema "<schemaPath>"
    Examples:
      | password  | email                    | statusCode | schemaPath                                |
      | Test1111! | anidarbinyan14@yahoo.com | 200        | schema/getAllCategoriesRequestSchema.json |


  Scenario Outline: Find all categories and relevant subcategories without login
    When Get all categories and relevant subcategories without login
    Then Status code should be <statusCode>
    Examples:
      | statusCode |
      | 401        |

  Scenario Outline: Find all categories by title mapped by subcategories
    When Login by valid "<password>" password and "<email>" email
    When Get subCategory by "<title>"
    Then Status code should be <statusCode>
#    Then Validate categories' response "<title>" values
    Examples:
      | password  | email                      | title    | statusCode | schemaPath                                    |
      | Test123@# | openschooltest78@gmail.com | Speaking | 200        | schema/getPostedSubCategoryRequestSchema.json |

