package hr.tvz.zavrsni.transportapplication;

import android.content.Intent;
import android.os.Bundle;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mEditTextUsername = (EditText) findViewById(R.id.inputUsername);
        mEditTextPassword = (EditText) findViewById(R.id.inputPassword);
    }

    @Override
    protected void onResume() {
        super.onResume();

        TransportPreferences prefs = new TransportPreferences(this);
        mEditTextUsername.setText(prefs.getUsername());
        mEditTextPassword.setText(prefs.getPassword());
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

    @Override
    public void onApiResponse(BasicModel response) {

    }

    @Override
    public void onApiFailure() {

    }
}
