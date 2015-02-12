package hr.tvz.zavrsni.transportapplication;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import hr.tvz.zavrsni.domain.api.User;
import hr.tvz.zavrsni.json.TransportApiListener;


public class ContactActivity extends TransportActivity implements TransportApiListener<User> {
    private String mUsername = new String();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        if(getIntent().hasExtra("username")){
            mUsername = getIntent().getStringExtra("username");
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_contact, menu);
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

    @Override
    protected void onResume() {
        super.onResume();
        App app = (App) getApplication();
        app.setTransportApiListener(this);

        app.getUserByUsername(mUsername);
        Toast.makeText(getApplicationContext(), "Loading contact...", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        App app = (App) getApplication();
        app.removeTransportApiListener();
    }

    @Override
    public void onApiResponse(User response) {
        TextView textUsername = (TextView)findViewById(R.id.contactUsername);
        TextView textCompany = (TextView)findViewById(R.id.contactCompany);
        TextView textTel = (TextView)findViewById(R.id.contactNumber);
        TextView textEmail = (TextView)findViewById(R.id.contactEmail);
        TextView textName = (TextView)findViewById(R.id.contactName);
        TextView textSurname = (TextView)findViewById(R.id.contactSurname);

        textUsername.setText(response.getUsername());
        textCompany.setText(response.getCompanyName());
        textTel.setText(response.getContact());
        textEmail.setText(response.getEmail());
        textName.setText(response.getName());
        textSurname.setText(response.getSurname());
    }

    @Override
    public void onApiFailure(String message) {
        super.alert(TextUtils.isEmpty(message) ? getString(R.string.api_alert_dialog_body) : message);
    }
}
