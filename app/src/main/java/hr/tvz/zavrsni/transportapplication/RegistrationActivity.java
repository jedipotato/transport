package hr.tvz.zavrsni.transportapplication;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import hr.tvz.zavrsni.domain.api.BasicModel;
import hr.tvz.zavrsni.json.TransportApiListener;
import hr.tvz.zavrsni.util.TransportPreferences;


public class RegistrationActivity extends TransportActivity implements TransportApiListener<BasicModel> {

    private String mInputName;
    private String mInputSurname;
    private String mInputUsername;
    private String mInputEmail;
    private String mInputPassword;

    private EditText mEditTextName;
    private EditText mEditTextSurname;
    private EditText mEditTextUsername;
    private EditText mEditTextEmail;
    private EditText mEditTextPassword;

    private App mApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        mApp = (App) getApplication();

        mEditTextName = (EditText) findViewById(R.id.registerInputName);
        mEditTextSurname = (EditText) findViewById(R.id.registerInputSurname);
        mEditTextUsername = (EditText) findViewById(R.id.registerInputUsername);
        mEditTextEmail = (EditText) findViewById(R.id.registerInputEmail);
        mEditTextPassword = (EditText) findViewById(R.id.registerInputPassword);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mApp = null;
    }

    @Override
    protected void onResume() {
        super.onResume();

        mApp.setTransportApiListener(this);
    }

    @Override
    protected void onPause() {
        super.onPause();

        mApp.removeTransportApiListener();
    }

    @Override
    protected void onDebugBuildOnly() {
        super.onDebugBuildOnly();

//        mEditTextName.setText("Pero");
//        mEditTextSurname.setText("Kvrzica");
//        mEditTextUsername.setText("ppero");
//        mEditTextEmail.setText("email@gmail.com");
//        mEditTextPassword.setText("123456");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_registration, menu);
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

    public void onClickSubmit(View view) {
        mEditTextName.setError(null);
        mEditTextSurname.setError(null);
        mEditTextUsername.setError(null);
        mEditTextEmail.setError(null);
        mEditTextPassword.setError(null);

        mInputName = mEditTextName.getText().toString();
        mInputSurname = mEditTextSurname.getText().toString();
        mInputUsername = mEditTextUsername.getText().toString();
        mInputEmail = mEditTextEmail.getText().toString();
        mInputPassword = mEditTextPassword.getText().toString();

        boolean isOk = true;
        if(mInputName.length()<1 || mInputName.length()>30) {
            mEditTextName.setError("Name input error!");
            isOk = false;
        }
        if(mInputSurname.length()<1 || mInputSurname.length()>30) {
            mEditTextSurname.setError("Last name input error!");
            isOk = false;
        }
        if(mInputUsername.length()<1 || mInputUsername.length()>12) {
            mEditTextUsername.setError("Username input error!");
            isOk = false;
        }
        if(mInputEmail.length()<2 || mInputEmail.length()>40) {
            mEditTextEmail.setError("E-mail input error!");
            isOk = false;
        }
        if(mInputPassword.length()<4 || mInputPassword.length()>30) {
            mEditTextPassword.setError("Password minimum is 4 characters!");
            isOk = false;
        }

        if (!isOk) return;

        mApp.createUser(mInputName,mInputSurname,mInputUsername,mInputPassword,mInputEmail);
    }

    @Override
    public void onApiResponse(BasicModel response) {
        if (!response.isSuccess()) {
            alert(response.getMessage());
        } else {
            TransportPreferences prefs = new TransportPreferences(this);
            prefs.saveUsername(mInputUsername);
            prefs.savePassword(mInputPassword);
            Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    @Override
    public void onApiFailure() {
        super.alert(R.string.api_alert_dialog_body);
    }

}
