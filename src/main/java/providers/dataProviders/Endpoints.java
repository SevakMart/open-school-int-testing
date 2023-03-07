package providers.dataProviders;

public enum Endpoints {

    ADD_QUESTIONS("courses/peers-questions"),
    ADD_ANSWER_FOR_THE_PEERS_Question("/answer"),
    ADD_QUESTIONS_TO_THE_MENTOR("/courses/mentor-questions"),
    ENROLL_COURSE("users/{userId}/courses/{courseId}"),
    SIGN_UP("auth/register"),
    SEND_TO_EMAIL_FOR_VERIFICATION("auth/account/verification"),
    RESEND_TO_EMAIL_VERIFICATION("auth/account/verification/resend"),
    VERIFY_EMAIL("auth/account"),
    CREATE_CATEGORY("categories"),
    CREATE_COURSE("courses"),
    GET_ALL_PARENT_CATEGORIES("categories/parentCategories"),
    DELETE_COURSE("courses/{id}");

    public final String url;

    Endpoints(String endpoint) {
        this.url = endpoint;
    }
}