package providers.dataProviders;

import java.util.List;

public final class SharedTestData {

    private static String token = "";

    private static String resetPasswordToken;

    private static int userId;

    private static int categoryId;

    private static int subCategoryId;

    private static List<?> usersSavedMentorsList;

    private static int courseId;

    public static int getCourseId() {
        return courseId;
    }

    public static void setCourseId(int courseId) {
        SharedTestData.courseId = courseId;
    }

    public static int getSubCategoryId() {
        return subCategoryId;
    }

    public static List<?> getUsersSavedMentorsList() {
        return usersSavedMentorsList;
    }

    public static void setUsersSavedMentorsList(List<?> usersSavedMentorsList) {
        SharedTestData.usersSavedMentorsList = usersSavedMentorsList;
    }

    public static void setSubCategoryId(int subCategoryId) {
        SharedTestData.subCategoryId = subCategoryId;
    }

    public static void setCategoryId(int categoryId) {
        SharedTestData.categoryId = categoryId;
    }

    public static int getCategoryId() {
        return categoryId;
    }

    public static String getToken() {
        return token;
    }

    public static void setToken(String token) {
        SharedTestData.token = token;
    }

    public static String getResetPasswordToken() {
        return resetPasswordToken;
    }

    public static void setResetPasswordToken(String resetPasswordToken) {
        SharedTestData.resetPasswordToken = resetPasswordToken;
    }

    public static int getUserId() {
        return userId;
    }

    public static void setUserId(int userId) {
        SharedTestData.userId = userId;
    }
}
