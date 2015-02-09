package hr.tvz.zavrsni.json;

import hr.tvz.zavrsni.domain.api.BasicModel;
import hr.tvz.zavrsni.util.Const;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Header;

/**
 * Used for login only
 */
public interface LoginService {

    @GET(Const.API_LOGIN)
    void login(@Header("username") String username, @Header("password") String password, Callback<BasicModel> callback);

}
