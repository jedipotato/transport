package hr.tvz.zavrsni.transportapplication;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import hr.tvz.zavrsni.domain.api.BasicModel;
import hr.tvz.zavrsni.json.TransportApiListener;


public class NewJobActivity extends TransportActivity implements TransportApiListener<BasicModel> {
    private String mCategoryId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_job);

        if(getIntent().hasExtra("category_id")){
            mCategoryId = getIntent().getStringExtra("category_id");
        }

        Spinner dropdown = (Spinner)findViewById(R.id.newJobSpinnerDays);
        String[] items = new String[50];
        for(int i=0; i<30; i++){
            items[i] = Integer.toString(i+1);
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, items);
        dropdown.setAdapter(adapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_new_job, menu);
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
    public void onApiResponse(BasicModel response) {
        if (!checkUserAuthenticationResponseAndReset(response)) return;

        if(response.getSuccess() == 1){
            Toast.makeText(getApplicationContext(), response.getMessage(), Toast.LENGTH_SHORT).show();
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
        }
        if(response.getSuccess() == 0){
            Toast.makeText(getApplicationContext(), response.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onApiFailure(String message) {
        super.alert(TextUtils.isEmpty(message) ? getString(R.string.api_alert_dialog_body) : message);
    }

    public void onClickCreateJob(View view) {
        App app = (App) getApplication();
        app.setTransportApiListener(this);

        EditText inputName = (EditText)findViewById(R.id.newJobNameInput);
        EditText inputDesc = (EditText)findViewById(R.id.newJobDescriptionInput);
        Spinner inputSpinner = (Spinner)findViewById(R.id.newJobSpinnerDays);

        app.postJob(mCategoryId,inputName.getText().toString(),inputDesc.getText().toString(),inputSpinner.getSelectedItem().toString());
    }
}
