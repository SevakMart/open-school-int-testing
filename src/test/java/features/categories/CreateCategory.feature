Feature: API test for Open School: category-controller
  Description: The purpose of these tests is to cover all flows connected with creation categories
  Open School Swagger URL : http://open-school-dev.eu-central-1.elasticbeanstalk.com/swagger-ui/index.html

  Background:
    Given Setup Rest Assured

  Scenario Outline: Create new parentCategory
    When Login by valid "<email>" email and "<password>" password
    When Create parentCategory with provided file
    Then Status code should be 201
    Then Validate response by JsonSchema
    Examples:
      | password | email      |
      | adminPsd | adminEmail |

  Scenario Outline: New category creation is not possible without attaching an image
    When Login by valid "<email>" email and "<password>" password
    When Invalid filePath during parentCategory creation
    Then Status code should be 400
    Then Validate new category creation without image failure response message
    Examples:
      | password | email      |
      | adminPsd | adminEmail |

  Scenario Outline: New category creation is not possible without valid title
    When Login by valid "<email>" email and "<password>" password
    When Invalid title during parentCategory creation
    Then Status code should be 400
    Then Validate new category creation without title failure response message
    Examples:
      | password | email      |
      | adminPsd | adminEmail |

  Scenario Outline: Create new SubCategory
    When Login by valid "<email>" email and "<password>" password
    When Create parentCategory with provided file
    When Create subCategory
    Then Status code should be 201
    Then Validate subcategory creation response JsonSchema
    Examples:
      | password | email      |
      | adminPsd | adminEmail |

  Scenario Outline: Create new category without admin role
    When Login by valid "<email>" email and "<password>" password
    When Fail parentCategory creatiion without admin role
    Then Status code should be 403
    Examples:
      | password | email     |
      | userPsd  | userEmail |
