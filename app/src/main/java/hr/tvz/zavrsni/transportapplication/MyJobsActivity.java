package hr.tvz.zavrsni.transportapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import hr.tvz.zavrsni.adapter.JobAdapter;
import hr.tvz.zavrsni.domain.api.Jobs;
import hr.tvz.zavrsni.json.TransportApiListener;


public class MyJobsActivity extends TransportActivity implements TransportApiListener<Jobs> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_jobs);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_my_jobs, menu);
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
    protected void onPause() {
        super.onPause();
        App app = (App) getApplication();
        app.removeTransportApiListener();
    }

    @Override
    protected void onResume() {
        super.onResume();
        App app = (App) getApplication();
        app.setTransportApiListener(this);

        app.getJobsByUser();
        Toast.makeText(getApplicationContext(), "Loading jobs...", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onApiResponse(Jobs response) {
        if (!checkUserAuthenticationResponseAndReset(response)) return;

        ListView listView = (ListView) findViewById(R.id.jobList);
        JobAdapter adapter = new JobAdapter(getApplicationContext(), new ArrayList<>(response.getJobsList()));
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull AdapterView<?> parent, @NonNull View view, int position, long id) {
                TextView idJobTextView = (TextView) view.findViewById(R.id.jobId);
                TextView idCategoryTextView = (TextView) view.findViewById(R.id.categoryId);
                Intent i = new Intent(MyJobsActivity.this, JobActivity.class);

                i.putExtra("job_id", idJobTextView.getText().toString());
                i.putExtra("category_id", idCategoryTextView.getText().toString());
                Log.e("JobListActivity::job_id", idJobTextView.getText().toString());
                Log.e("JobListActivity::category_id",idCategoryTextView.getText().toString());
                startActivity(i);
            }
        });
    }

    @Override
    public void onApiFailure(String message) {
        super.alert(TextUtils.isEmpty(message) ? getString(R.string.api_alert_dialog_body) : message);
    }
}
