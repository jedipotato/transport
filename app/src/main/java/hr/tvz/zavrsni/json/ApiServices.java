package hr.tvz.zavrsni.json;

import hr.tvz.zavrsni.domain.api.Categories;
import hr.tvz.zavrsni.domain.api.Jobs;
import hr.tvz.zavrsni.util.Const;
import retrofit.Callback;
import retrofit.http.GET;

/**
 * Defines API services which are to be used with Retrofit.
 * Do not confuse with {@link hr.tvz.zavrsni.json.ApiCallables}
 */
public interface ApiServices {

    @GET(Const.API_CATEGORY)
    void getAllCategories(Callback<Categories> result);

    @GET(Const.API_JOBS)
    void getJobsByCategory(Callback<Jobs> result);

}
