Feature: API test for Open School: category-controller
  Description: The purpose of these tests is to cover all flows connected with creation  and delete SubCategory
  Open School Swagger URL : http://open-school-dev.eu-central-1.elasticbeanstalk.com/swagger-ui/index.html

  Background:
    Given Setup Rest Assured

  Scenario Outline: Create new SubCategory
    When Login by valid "<password>" password and "<email>" email
    When Create new category with parentId "<parentCategoryId>" path "<filePath>" title "<title>"
    Then Status code should be 201
    Then Validate subcategory creation response JsonSchema
    Examples:
      | password  | email                    | parentCategoryId | title        | filePath                 |
      | Test1111! | anidarbinyan14@yahoo.com | 4                | TestSubgsgsg | src/test/image/new1.webp |