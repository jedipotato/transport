package hr.tvz.zavrsni.transportapplication;

import android.app.Application;
import android.util.Log;

import hr.tvz.zavrsni.domain.api.BasicModel;
import hr.tvz.zavrsni.domain.api.Bid;
import hr.tvz.zavrsni.domain.api.Bids;
import hr.tvz.zavrsni.domain.api.Categories;
import hr.tvz.zavrsni.domain.api.Job;
import hr.tvz.zavrsni.domain.api.Jobs;
import hr.tvz.zavrsni.domain.api.User;
import hr.tvz.zavrsni.json.ApiCallables;
import hr.tvz.zavrsni.json.ApiServices;
import hr.tvz.zavrsni.json.LoginService;
import hr.tvz.zavrsni.json.TransportApiListener;
import hr.tvz.zavrsni.util.Const;
import hr.tvz.zavrsni.util.Crypt;
import hr.tvz.zavrsni.util.TransportPreferences;
import retrofit.Callback;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Main Application singleton
 */
public class App extends Application implements ApiCallables {

    private LoginService mLoginService;
    private TransportApiListener<BasicModel> mTransportApiListener;

    private ApiServices apiServiceAdapter() {
        RequestInterceptor requestInterceptor = new RequestInterceptor() {
            @Override
            public void intercept(RequestFacade request) {
                TransportPreferences prefs = new TransportPreferences(getApplicationContext());
                request.addHeader(Const.USERNAME, prefs.getUsername());
                request.addHeader(Const.PASSWORD,
                        /* password has to be encrypted */ Crypt.md5(prefs.getPassword()));
            }
        };
        return new RestAdapter.Builder()
                .setEndpoint(Const.URL_BASE)
                .setLogLevel(BuildConfig.DEBUG ? RestAdapter.LogLevel.FULL : RestAdapter.LogLevel.NONE)
                .setRequestInterceptor(requestInterceptor)
                .build().create(ApiServices.class);
    }

    @Override
    public void onCreate() {
        super.onCreate();

        mLoginService = new RestAdapter.Builder()
                .setEndpoint(Const.URL_BASE)
                .setLogLevel(BuildConfig.DEBUG ? RestAdapter.LogLevel.FULL : RestAdapter.LogLevel.NONE)
                .build().create(LoginService.class);
    }

    public void setTransportApiListener(TransportApiListener l) {
        this.mTransportApiListener = l;
    }

    public void removeTransportApiListener() {
        this.mTransportApiListener = null;
    }

    @Override
     public void getCategories() {
        apiServiceAdapter().getAllCategories(new Callback<Categories>() {
            @Override
            public void success(Categories categories, Response response) {
                if (mTransportApiListener != null) {
                    mTransportApiListener.onApiResponse(categories);
                }
            }

            @Override
            public void failure(RetrofitError error) {
                if (mTransportApiListener != null) {
                    mTransportApiListener.onApiFailure(null);
                }
            }
        });
    }

    @Override
    public void getJobs(String categoryId) {
        apiServiceAdapter().getJobsByCategory(categoryId, new Callback<Jobs>() {
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
                    mTransportApiListener.onApiFailure(null);
                }
            }
        });

    }

    @Override
    public void getJob(String jobId, String categoryId) {
        apiServiceAdapter().getJobById(jobId, categoryId, new Callback<Job>() {
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
                    mTransportApiListener.onApiFailure(null);
                }
            }
        });
    }

    @Override
    public void getJobById(String jobId) {
        apiServiceAdapter().getJobByJobId(jobId, new Callback<Job>() {
            @Override
            public void success(Job job, Response response) {
                if (mTransportApiListener != null) {
                    mTransportApiListener.onApiResponse(job);
                }
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e("App.getJobByJobId", error.toString());
                if (mTransportApiListener != null) {
                    mTransportApiListener.onApiFailure(null);
                }
            }
        });
    }

    @Override
    public void getJobsByUser() {
        apiServiceAdapter().getJobsByUser(new Callback<Jobs>() {
            @Override
            public void success(Jobs jobs, Response response) {
                if (mTransportApiListener != null) {
                    mTransportApiListener.onApiResponse(jobs);
                }
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e("App.getJobsByUser", error.toString());
                if (mTransportApiListener != null) {
                    mTransportApiListener.onApiFailure(null);
                }
            }
        });
    }

    @Override
    public void getUser() {
        apiServiceAdapter().getUser(new Callback<User>() {
            @Override
            public void success(User user, Response response) {
                if (mTransportApiListener != null) {
                    mTransportApiListener.onApiResponse(user);
                }
            }

            @Override
            public void failure(RetrofitError error) {
                if (mTransportApiListener != null) {
                    mTransportApiListener.onApiFailure(null);
                }
            }
        });
    }

    @Override
    public void getUserByUsername(String username) {
        apiServiceAdapter().getUserByUsername(username, new Callback<User>() {
            @Override
            public void success(User user, Response response) {
                if (mTransportApiListener != null) {
                    mTransportApiListener.onApiResponse(user);
                }
            }

            @Override
            public void failure(RetrofitError error) {
                if (mTransportApiListener != null) {
                    mTransportApiListener.onApiResponse(null);
                }
            }
        });
    }

    @Override
    public void updateUser(String name, String surname, String username, String email, String password, String company, String contact) {
        User user = new User(name, surname, username, password != null ? Crypt.md5(password) : null, email, company, contact);
        apiServiceAdapter().updateUser(user, new Callback<BasicModel>() {
            @Override
            public void success(BasicModel basicModel, Response response) {
                if (mTransportApiListener != null) {
                    mTransportApiListener.onApiResponse(basicModel);
                }
            }

            @Override
            public void failure(RetrofitError error) {
                if (mTransportApiListener != null) {
                    mTransportApiListener.onApiFailure(null);
                }
            }
        });
    }

    @Override
    public void getBidsByUser() {
        apiServiceAdapter().getBidsByUser(new Callback<Bids>() {
            @Override
            public void success(Bids bids, Response response) {
                if(mTransportApiListener != null){
                    mTransportApiListener.onApiResponse(bids);
                }
            }

            @Override
            public void failure(RetrofitError error) {
                if (mTransportApiListener != null) {
                    mTransportApiListener.onApiFailure(null);
                }
            }
        });
    }

    @Override
    public void getBidsByJob(String jobId, String categoryId) {
        apiServiceAdapter().getBidsByJob(jobId, categoryId, new Callback<Bids>() {
            @Override
            public void success(Bids bids, Response response) {
                if (mTransportApiListener != null) {
                    mTransportApiListener.onApiResponse(bids);
                }
            }

            @Override
            public void failure(RetrofitError error) {
                if (mTransportApiListener != null) {
                    mTransportApiListener.onApiFailure(null);
                }
            }
        });
    }

    @Override
    public void postBid(String jobId, String categoryId, String bid, String userId) {
        Bid newBid = new Bid(jobId, bid, userId);
        apiServiceAdapter().postBid(newBid, jobId, categoryId, new Callback<Bids>() {
            @Override
            public void success(Bids bids, Response response) {
                if(mTransportApiListener != null){
                    mTransportApiListener.onApiResponse(bids);
                }
            }

            @Override
            public void failure(RetrofitError error) {
                if (mTransportApiListener != null) {
                    mTransportApiListener.onApiFailure(null);
                }
            }
        });
    }

    @Override
    public void postJob(String categoryId, String jobName, String jobDescription, String duration) {
        Job newJob = new Job(categoryId,jobName,jobDescription,duration);
        apiServiceAdapter().createJob(newJob,categoryId, new Callback<BasicModel>() {
            @Override
            public void success(BasicModel basicModel, Response response) {
                if(mTransportApiListener != null){
                    mTransportApiListener.onApiResponse(basicModel);
                }
            }

            @Override
            public void failure(RetrofitError error) {
                if (mTransportApiListener != null) {
                    mTransportApiListener.onApiFailure(null);
                }
            }
        });
    }


    @Override
    public void createUser(String name, String surname, String username, String password, String email) {
        User user = new User(name, surname, username, Crypt.md5(password), email, null, null);
        mLoginService.putUser(user, new Callback<BasicModel>() {
            @Override
            public void success(BasicModel basicModel, Response response) {
                if (mTransportApiListener != null) {
                    mTransportApiListener.onApiResponse(basicModel);
                }
            }

            @Override
            public void failure(RetrofitError error) {
                if (mTransportApiListener != null) {
                    mTransportApiListener.onApiFailure(null);
                }
            }
        });
    }

    @Override
    public void login(String username, String password) {
        mLoginService.login(username, Crypt.md5(password), new Callback<BasicModel>() {
            @Override
            public void success(BasicModel basicModel, Response response) {
                if (mTransportApiListener != null) {
                    mTransportApiListener.onApiResponse(basicModel);
                }
            }

            @Override
            public void failure(RetrofitError error) {
                if (mTransportApiListener != null) {
                    mTransportApiListener.onApiFailure(null);
                }
            }
        });
    }


}
