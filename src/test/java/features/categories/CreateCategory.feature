Feature: API test for Open School: category-controller
  Description: The purpose of these tests is to cover all flows connected with creation categories
  Open School Swagger URL : http://open-school-dev.eu-central-1.elasticbeanstalk.com/swagger-ui/index.html

  Background:
    Given Setup Rest Assured

  Scenario: Create new parentCategory
    When Login by valid "adminEmail" email and "adminPsd" password
    When Create parentCategory with provided file
    Then Status code should be 201
    Then Validate response by JsonSchema

  Scenario: New category creation is not possible without attaching an image
    When Login by valid "adminEmail" email and "adminPsd" password
    When Invalid filePath during parentCategory creation
    Then Status code should be 400
    Then Validate new category creation without image failure response message

  Scenario: New category creation is not possible without valid title
    When Login by valid "adminEmail" email and "adminPsd" password
    When Invalid title during parentCategory creation
    Then Status code should be 400
    Then Validate new category creation without title failure response message

  Scenario: Create new SubCategory
    When Login by valid "adminEmail" email and "adminPsd" password
    When Create parentCategory with provided file
    When Create subCategory
    Then Status code should be 201
    Then Validate subcategory creation response JsonSchema

  Scenario: Create new category without admin role
    When Login by valid "userEmail" email and "userPsd" password
    When Fail parentCategory creatiion without admin role
    Then Status code should be 403

  Scenario: Creation subCategory which is a parentCategory for another category is not possible
    When Login by valid "adminEmail" email and "adminPsd" password
    Then Create subcategory whose parentCategory is subCategory for another category
    Then Status code should be 400
    Then Validate response message of nested category creation's fail
