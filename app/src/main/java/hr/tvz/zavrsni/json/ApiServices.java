package hr.tvz.zavrsni.json;

import hr.tvz.zavrsni.domain.api.BasicModel;
import hr.tvz.zavrsni.domain.api.Categories;
import hr.tvz.zavrsni.domain.api.Job;
import hr.tvz.zavrsni.domain.api.Jobs;
import hr.tvz.zavrsni.domain.api.User;
import hr.tvz.zavrsni.util.Const;
import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.PUT;
import retrofit.http.Path;

/**
 * Defines API services which are to be used with Retrofit.
 * Do not confuse with {@link hr.tvz.zavrsni.json.ApiCallables}
 */
public interface ApiServices {

    @GET(Const.API_CATEGORY)
    void getAllCategories(Callback<Categories> result);

    @GET(Const.API_JOBS)
    void getJobsByCategory(@Path("category_id") String id, Callback<Jobs> result);

    @GET(Const.API_JOB)
    void getJobById(@Path("job_id") String jobId, @Path("category_id") String categoryId, Callback<Job> result);

    @PUT(Const.API_USER + "peder")
    void putUser(@Body User jsonUser, Callback<BasicModel> callback);
}
