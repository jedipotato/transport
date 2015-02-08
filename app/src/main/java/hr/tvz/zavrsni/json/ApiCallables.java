package hr.tvz.zavrsni.json;

/**
 * Defines all api methods
 */
public interface ApiCallables {
    void getCategories();
    void getJobs(String id);
    void getJob(String jobId, String categoryId);

    void createUser(String name, String surname, String username, String password, String email);
}
