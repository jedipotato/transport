package hr.tvz.zavrsni.json;

/**
 * Defines all api methods
 */
public interface ApiCallables {
    void getCategories();
    void getJobs(String id);
    void getJob(String jobId, String categoryId);
    void getJobById(String jobId);
    void getJobsByUser();
    void getUser();
    void updateUser(String name, String surname, String username, String email, String password);
    void getBidsByUser();
    void getBidsByJob(String jobId, String categoryId);
    void postBid(String jobId, String categoryId, String bid, String userId);
    void postJob(String categoryId, String jobName, String jobDescription, String duration);

    void createUser(String name, String surname, String username, String password, String email);
    void login(String username, String password);
}
