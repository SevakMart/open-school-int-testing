package steps.categories;

import utils.RequestsUtils;
import utils.ResponseUtils;
import utils.SharedTestData;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CategoriesSteps {


    @When("Get all categories and relevant subcategories")
    public void getAllCategoriesAndRelevantSubcategories() {
        RequestsUtils.get("categories", SharedTestData.getToken());


    }

//
//    @Then("Validate categories' response {string} values")
//    public void validateCategoriesResponseTitleValues(String expectedSubCategoryTitle) {
//        List  <Object> list = (List<Object>) ResponseUtils.getListFromResponse("$.parentAndSubCategoriesMap..title");
//        list.forEach(System.out::println);
//
//        Assertions.assertThat(actualSubCategoryTitle).isEqualTo(expectedSubCategoryTitle);
//}

    @Then("Set Admin Token")
    public void setAdminToken() {
        SharedTestData.setToken(ResponseUtils.getAuthTokenFromResponseHeader());
    }

    @When("Create SubCategory")
    public void createSubCategory() {

    }

    @When("Create Category where File path is {string} and image {string}")
    public void createCategoryWhereFilePathIsAndImage(String filePath, String title) {
        System.out.println(filePath);
        SharedTestData.setToken(ResponseUtils.getAuthTokenFromResponseHeader());
        RequestsUtils.multipartPost("categories", SharedTestData.getToken(), filePath, title);
    }

    @When("Create new category with parent {}, path {}, title {}")
    public void createNewCategoryWithParentIdPathFilePathTitleTitle(String id, String filePath, String title) {
        System.out.println(id);
        System.out.println("___________________");
        System.out.println(title);
        SharedTestData.setToken(ResponseUtils.getAuthTokenFromResponseHeader());
        System.out.println("This is token " + SharedTestData.getToken());
        RequestsUtils.multipartPost("categories", SharedTestData.getToken(), id, title, filePath);
    }

    @When("Get subCategory by {string}")
    public void getSubCategoryBy(String title) {
        SharedTestData.setToken(ResponseUtils.getAuthTokenFromResponseHeader());
        RequestsUtils.getCategoryBySubCategoryTitle("categories/subcategories", SharedTestData.getToken(), title);


    }

    @When("Create new category with {string}")
    public void createNewCategoryWith(String title) {
        SharedTestData.setToken(ResponseUtils.getAuthTokenFromResponseHeader());
        RequestsUtils.multipartPostWithoutImage("categories", SharedTestData.getToken(), title);
    }

    @When("Get all categories and relevant subcategories without login")
    public void getAllCategoriesAndRelevantSubcategoriesWithoutLogin() {
        RequestsUtils.get("categories");
    }
}
