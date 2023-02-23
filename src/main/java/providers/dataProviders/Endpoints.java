package providers.dataProviders;

public enum Endpoints {

    ADD_QUESTIONS("courses/peers-questions"),
    ADD_ANSWER_FOR_THE_PEERS_Question("/answer"),
    ADD_QUESTIONS_TO_THE_MENTOR("/courses/mentor-questions"),
    ENROLL_COURSE("users/{userId}/courses/{courseId}");

    public final String url;

    Endpoints(String endpoint) {
        this.url = endpoint;
    }
}
