package hr.tvz.zavrsni.transportapplication;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
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

    private TextView mWarning;

    private App mApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        mApp = (App) getApplication();

        mWarning = (TextView)findViewById(R.id.registerWarning);
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

        ((TextView) findViewById(R.id.registerInputName)).setText("Pero");
        ((TextView) findViewById(R.id.registerInputSurname)).setText("Kvrzica");
        ((TextView) findViewById(R.id.registerInputUsername)).setText("ppero");
        ((TextView) findViewById(R.id.registerInputEmail)).setText("email@gmail.com");
        ((TextView) findViewById(R.id.registerInputPassword)).setText("123456");
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
        mInputName = ((TextView)findViewById(R.id.registerInputName)).getText().toString();
        mInputSurname = ((TextView)findViewById(R.id.registerInputSurname)).getText().toString();
        mInputUsername = ((TextView)findViewById(R.id.registerInputUsername)).getText().toString();
        mInputEmail = ((TextView)findViewById(R.id.registerInputEmail)).getText().toString();
        mInputPassword = ((TextView)findViewById(R.id.registerInputPassword)).getText().toString();

        if(mInputName.length()<1 || mInputName.length()>30) {
            mWarning.setText("Name input error!");
            return;
        }
        if(mInputSurname.length()<1 || mInputSurname.length()>30) {
            mWarning.setText("Last name input error!");
            return;
        }
        if(mInputUsername.length()<1 || mInputUsername.length()>12) {
            mWarning.setText("Username input error!");
            return;
        }
        if(mInputEmail.length()<2 || mInputEmail.length()>40) {
            mWarning.setText("E-mail input error!");
            return;
        }
        if(mInputPassword.length()<4 || mInputPassword.length()>30) {
            mWarning.setText("Password minimum is 4 characters!");
            return;
        }
        //TODO
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
