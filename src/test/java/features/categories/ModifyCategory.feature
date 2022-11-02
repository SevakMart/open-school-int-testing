Feature: API test for Open School: category-controller
  Description: The purpose of these tests is to cover all flows connected with categories' modification
  Open School Swagger URL : http://open-school-dev.eu-central-1.elasticbeanstalk.com/swagger-ui/index.html

  Background:
    Given Setup Rest Assured

  Scenario Outline: Modify data of categories or subcategories by title
    When Login by valid "<email>" email and "<password>" password
    When Create parentCategory with provided file
    Then Modify category or subcategory by title
    Then Status code should be 200
    Then Validate response body by title
    Examples:
      | password | email      |
      | adminPsd | adminEmail |

  Scenario Outline: Modify data of categories or subcategories by image
    When Login by valid "<email>" email and "<password>" password
    When Create parentCategory with provided file
    Then Modify category or subcategory by image
    Then Status code should be 200
    Then Validate response body by image
    Examples:
      | password | email      |
      | adminPsd | adminEmail |

  Scenario Outline: Modify data of categories or subcategories by invalid category id
    When Login by valid "<email>" email and "<password>" password
    Then Modify category or subcategory by invalid category id
    Then Status code should be 400
    Then Validate error message about invalid category
    Examples:
      | password | email      |
      | adminPsd | adminEmail |

  Scenario Outline: Modify data of categories or subcategories by invalid parentCategoryId
    When Login by valid "<email>" email and "<password>" password
    Then Get category from DB
    Then Modify category or subcategory by invalid parentCategoryId
    Then Status code should be 400
    Then Validate error message about invalid category
    Examples:
      | password | email      |
      | adminPsd | adminEmail |

  Scenario Outline: Modify data of categories or subcategories by invalid title
    When Login by valid "<email>" email and "<password>" password
    Then Get category from DB
    Then Modify category or subcategory by invalid title
    Then Status code should be 400
    Then Validate error message about invalid category
    Examples:
      | password | email      |
      | adminPsd | adminEmail |
