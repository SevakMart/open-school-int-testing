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

    private static int faqsId;

    private static String faqsQuestion;

    private static String faqsAnswer;

    private static String categorytitle;

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

    public static int getFaqsId() {
        return faqsId;
    }

    public static void setFaqsId(int faqsId) {
        SharedTestData.faqsId = faqsId;
    }

    public static void setFaqsQuestion(String faqsQuestion) {
        SharedTestData.faqsQuestion = faqsQuestion;
    }

    public static String getFaqsQuestion() {
        return faqsQuestion;
    }

    public static String getFaqsAnswer() {
        return faqsAnswer;
    }

    public static void setFaqsAnswer(String faqsAnswer) {
        SharedTestData.faqsAnswer = faqsAnswer;
    }

    public static int getCourseId() {
        return courseId;
    }

    public static void setCourseId(int courseId) {
        SharedTestData.courseId = courseId;
    }

    public static String getCategorytitle() {
        return categorytitle;
    }

    public static void setCategorytitle(String categorytitle) {
        SharedTestData.categorytitle = categorytitle;
    }
}
