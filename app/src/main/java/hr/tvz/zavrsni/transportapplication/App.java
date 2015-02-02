package hr.tvz.zavrsni.transportapplication;

import android.app.Application;
import android.support.annotation.IntDef;
import android.util.Log;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import hr.tvz.zavrsni.domain.api.Categories;
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

    public static final int GET_CATEGORIES = -1;

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({GET_CATEGORIES})
    public @interface ApiMethod {}

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

    public void invokeApi(@ApiMethod int apiMethod) {
        switch (apiMethod) {
            case GET_CATEGORIES:
                getCategories();
                break;

            default:
                Log.e("App", "Wrong method definition");
        }
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

            }
        });
    }
}
