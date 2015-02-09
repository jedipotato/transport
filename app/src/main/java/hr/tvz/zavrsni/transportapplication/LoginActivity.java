package hr.tvz.zavrsni.transportapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import hr.tvz.zavrsni.domain.api.BasicModel;
import hr.tvz.zavrsni.json.TransportApiListener;
import hr.tvz.zavrsni.util.TransportPreferences;


public class LoginActivity extends TransportActivity implements TransportApiListener<BasicModel> {

    private EditText mEditTextUsername;
    private EditText mEditTextPassword;

    private App mApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mEditTextUsername = (EditText) findViewById(R.id.inputUsername);
        mEditTextPassword = (EditText) findViewById(R.id.inputPassword);

        mApp = (App) getApplication();

        findViewById(R.id.buttonLogin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(@NonNull View v) {
                onClickLogin(v);
            }
        });

        TransportPreferences prefs = new TransportPreferences(this);
        if (prefs.getAutoLogin()) {
            loginSuccess();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        mApp.setTransportApiListener(this);

        TransportPreferences prefs = new TransportPreferences(this);
        mEditTextUsername.setText(prefs.getUsername());
        mEditTextPassword.setText(prefs.getPassword());
    }

    @Override
    protected void onPause() {
        super.onPause();
        mApp.removeTransportApiListener();
    }

    @Override
    protected void onDebugBuildOnly() {
        super.onDebugBuildOnly();

        mEditTextUsername.setText("ppero");
        mEditTextPassword.setText("123456");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onClickRegistration(View view) {
        Intent i = new Intent(LoginActivity.this, RegistrationActivity.class);
        startActivity(i);
    }

    public void onClickLogin(View view) {
        mEditTextUsername.setError(null);
        mEditTextPassword.setError(null);

        String username = mEditTextUsername.getText().toString();
        String password = mEditTextPassword.getText().toString();

        boolean isOk = true;
        if (username.length() < 1 || username.length() > 12) {
            mEditTextUsername.setError("Username input error");
            isOk = false;
        }
        if (password.length() < 4 || password.length() > 30) {
            mEditTextPassword.setError("Password minimum is 4 characters!");
            isOk = false;
        }

        if (!isOk) return;

        mApp.login(username, password);
    }

    private void loginSuccess() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    @Override
    public void onApiResponse(BasicModel response) {
        if (response.isSuccess()) {
            TransportPreferences prefs = new TransportPreferences(this);
            if (!prefs.getUsername().equals(mEditTextUsername.getText().toString())) {
                prefs.saveUsername(mEditTextUsername.getText().toString());
            }
            if (!prefs.getUsername().equals(mEditTextPassword.getText().toString())) {
                prefs.savePassword(mEditTextPassword.getText().toString());
            }
            prefs.saveAutoLogin(true);
            loginSuccess();
        } else {
            super.alert(response.getMessage());
        }
    }

    @Override
    public void onApiFailure(String message) {
        super.alert(TextUtils.isEmpty(message) ? getString(R.string.api_alert_dialog_body) : message);
    }
}
