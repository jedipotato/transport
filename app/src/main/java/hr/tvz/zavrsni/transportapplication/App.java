package hr.tvz.zavrsni.transportapplication;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import hr.tvz.zavrsni.domain.api.BasicModel;
import hr.tvz.zavrsni.domain.api.Categories;
import hr.tvz.zavrsni.domain.api.Job;
import hr.tvz.zavrsni.domain.api.Jobs;
import hr.tvz.zavrsni.domain.api.User;
import hr.tvz.zavrsni.json.ApiCallables;
import hr.tvz.zavrsni.json.ApiServices;
import hr.tvz.zavrsni.json.TransportApiListener;
import hr.tvz.zavrsni.util.Const;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Main Application singleton
 */
public class App extends Application implements ApiCallables {

    private ApiServices mApiServices;
    private TransportApiListener mTransportApiListener;

    @Override
    public void onCreate() {
        super.onCreate();

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(Const.URL_BASE)
                .setLogLevel(BuildConfig.DEBUG ? RestAdapter.LogLevel.FULL : RestAdapter.LogLevel.NONE)
                .build();
        mApiServices = restAdapter.create(ApiServices.class);
    }

    public void setTransportApiListener(TransportApiListener l) {
        this.mTransportApiListener = l;
    }

    public void removeTransportApiListener() {
        this.mTransportApiListener = null;
    }

    @Override
     public void getCategories() {
        mApiServices.getAllCategories(new Callback<Categories>() {
            @Override
            public void success(Categories categories, Response response) {
                if (mTransportApiListener != null) {
                    mTransportApiListener.onApiResponse(categories);
                }
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e("App.getAllCategories failed", error.toString());
                if (mTransportApiListener != null) {
                    mTransportApiListener.onApiFailure();
                }
            }
        });
    }

    @Override
    public void getJobs(String categoryId) {
        mApiServices.getJobsByCategory(categoryId, new Callback<Jobs>() {
            @Override
            public void success(Jobs jobs, Response response) {
                if (mTransportApiListener != null) {
                    mTransportApiListener.onApiResponse(jobs);
                }
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e("App.getJobs failed", error.toString());
                if (mTransportApiListener != null) {
                    mTransportApiListener.onApiFailure();
                }
            }
        });

    }

    @Override
    public void getJob(String jobId, String categoryId) {
        mApiServices.getJobById(jobId, categoryId, new Callback<Job>() {
            @Override
            public void success(Job job, Response response) {
                if (mTransportApiListener != null) {
                    mTransportApiListener.onApiResponse(job);
                }
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e("App.getJobById failed", error.toString());
                if (mTransportApiListener != null) {
                    mTransportApiListener.onApiFailure();
                }
            }
        });
    }

    @Override
    public void createUser(String name, String surname, String username, String password, String email) {
        User user = new User(name, surname, username, password, email);
//        Gson gson = new Gson();
//        String jsonUser = gson.toJson(user); //TODO ovo radi i lijepo pretvori u json
        mApiServices.putUser(user, new Callback<BasicModel>() {
            @Override
            public void success(BasicModel basicModel, Response response) {
                Toast.makeText(getApplicationContext(), "Success PUT", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void failure(RetrofitError error) {
                if (mTransportApiListener != null) {
                    mTransportApiListener.onApiFailure();
                }
            }
        });
    }


}
