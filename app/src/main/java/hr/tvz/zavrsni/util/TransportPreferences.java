package hr.tvz.zavrsni.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class TransportPreferences {

    private static final String PREFERENCE_USERNAME = "hr.tvz.zavrsni.transport.username";
    private static final String PREFERENCE_PASSWORD = "hr.tvz.zavrsni.transport.password";
    private static final String PREFERENCE_AUTO_LOGIN = "hr.tvz.zavrsni.transport.autoLogin";

    private SharedPreferences mSharedPreferences;

    public TransportPreferences(Context context) {
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public void clearLoginInfo() {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.remove(PREFERENCE_USERNAME);
        editor.remove(PREFERENCE_PASSWORD);
        editor.remove(PREFERENCE_AUTO_LOGIN);
        editor.apply();
    }

    public void saveUsername(String username) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(PREFERENCE_USERNAME, username);
        editor.apply();
    }

    public String getUsername() {
        return mSharedPreferences.getString(PREFERENCE_USERNAME, "");
    }

    public void savePassword(String password) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(PREFERENCE_PASSWORD, password);
        editor.apply();
    }

    public String getPassword() {
        return mSharedPreferences.getString(PREFERENCE_PASSWORD, "");
    }

    public void saveAutoLogin(boolean autoLogin) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putBoolean(PREFERENCE_AUTO_LOGIN, autoLogin);
        editor.apply();
    }

    public boolean getAutoLogin() {
        return mSharedPreferences.getBoolean(PREFERENCE_AUTO_LOGIN, false);
    }

}
