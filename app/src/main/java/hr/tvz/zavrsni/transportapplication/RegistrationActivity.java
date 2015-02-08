package hr.tvz.zavrsni.transportapplication;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


public class RegistrationActivity extends ActionBarActivity {

    private String mInputName;
    private String mInputSurname;
    private String mInputUsername;
    private String mInputEmail;
    private String mInputPassword;

    private TextView mWarning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        mWarning = (TextView)findViewById(R.id.registerWarning);
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
        App app = (App) getApplication();
        app.createUser(mInputName,mInputSurname,mInputUsername,mInputPassword,mInputEmail);
    }
}
