package hr.tvz.zavrsni.transportapplication;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import hr.tvz.zavrsni.domain.api.Job;
import hr.tvz.zavrsni.json.TransportApiListener;


public class JobActivity extends TransportActivity implements TransportApiListener<Job> {
    private String mJobId = new String();
    private String mCategoryId = new String();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job);

        if(getIntent().hasExtra("category_id") && getIntent().hasExtra("job_id")) {
            mJobId = getIntent().getStringExtra("job_id");
            mCategoryId = getIntent().getStringExtra("category_id");
        }
        else {
            Log.e("JobActivity::category_id | job_id", mCategoryId + " | " + mJobId);
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_item, menu);
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
        /*} else if (id == R.id.action_home) {
            Toast.makeText(this, "Doma", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, MainActivity.class);
//            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);*/
        }


        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        App app = (App) getApplication();
        app.setTransportApiListener(this);

        app.getJob(mJobId,mCategoryId);
        Toast.makeText(getApplicationContext(),"Loading job...", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        App app = (App) getApplication();
        app.removeTransportApiListener();
    }

    @Override
    public void onApiResponse(Job response) {
        TextView textName = (TextView)findViewById(R.id.textJobName);
        TextView textDescription = (TextView)findViewById(R.id.textJobDescription);

        textName.setText(response.getName());
        textDescription.setText(response.getDescription());
    }

    @Override
    public void onApiFailure(String message) {
        super.alert(TextUtils.isEmpty(message) ? getString(R.string.api_alert_dialog_body) : message);
    }
}
