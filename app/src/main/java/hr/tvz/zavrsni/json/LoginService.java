package hr.tvz.zavrsni.json;

import hr.tvz.zavrsni.domain.api.BasicModel;
import hr.tvz.zavrsni.domain.api.User;
import hr.tvz.zavrsni.util.Const;
import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.PUT;

/**
 * Used for login only
 */
public interface LoginService {

    @PUT(Const.API_USER)
    void putUser(@Body User jsonUser, Callback<BasicModel> callback);

    @GET(Const.API_LOGIN)
    void login(@Header("username") String username, @Header("password") String password, Callback<BasicModel> callback);

}
