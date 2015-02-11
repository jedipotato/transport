package hr.tvz.zavrsni.transportapplication;

import android.app.ProgressDialog;
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
import hr.tvz.zavrsni.domain.api.Job;
import hr.tvz.zavrsni.domain.api.Jobs;
import hr.tvz.zavrsni.json.TransportApiListener;


public class JobListActivity extends TransportActivity implements TransportApiListener<Jobs> {
    private ProgressDialog pDialog;
    private ArrayList<Job> jobList = new ArrayList<>();
    private String mCategoryId = new String();
    private String mJobId = new String();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_list);

        if(getIntent().hasExtra("category_id")) {
            mCategoryId = getIntent().getStringExtra("category_id");
        }
        else {
            Log.e("JobListActivity::category_id",mCategoryId);
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_item_list, menu);
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

        app.getJobs(mCategoryId);
        Toast.makeText(getApplicationContext(), "Loading jobs...", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        App app = (App) getApplication();
        app.removeTransportApiListener();
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
                TextView idTextView = (TextView) view.findViewById(R.id.jobId);
                Intent i = new Intent(JobListActivity.this, JobActivity.class);

                i.putExtra("job_id", idTextView.getText().toString());
                i.putExtra("category_id", mCategoryId);
                Log.e("JobListActivity::job_id",idTextView.getText().toString());
                Log.e("JobListActivity::category_id",mCategoryId);
                startActivity(i);
            }
        });
    }

    @Override
    public void onApiFailure(String message) {
        super.alert(TextUtils.isEmpty(message) ? getString(R.string.api_alert_dialog_body) : message);
    }

    /*class LoadAllJobs extends AsyncTask<String, Void, ArrayList<Job>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(getApplicationContext(),"Loading jobs...",Toast.LENGTH_SHORT).show();
        }

        @Override
        protected ArrayList<Job> doInBackground(@NonNull String... params) {
            try {
                JSONParser jParser = new JSONParser();
                String url = "http://10.0.2.2/transportGetJobsByCategory.php?category_id=" + params[0];
                Log.e("url",url);
                JSONObject jObject = jParser.makeHttpRequest(url);

                Integer success = jObject.getInt("success");

                if (success == 1) {
                    jobList = Job.getListFromJSON(jObject);
                }
                else {
                    Toast.makeText(getApplicationContext(), "No jobs available!", Toast.LENGTH_SHORT).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return jobList;
        }

        @Override
        protected void onPostExecute(@NonNull ArrayList<Job> jobList) {
            ListView listView = (ListView)findViewById(R.id.jobList);
            JobAdapter adapter = new JobAdapter(getApplicationContext(),jobList);
            listView.setAdapter(adapter);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(@NonNull AdapterView<?> parent, @NonNull View view, int position, long id) {
                    TextView idTextView = (TextView)view.findViewById(R.id.jobId);
                    Intent i = new Intent(JobListActivity.this, JobActivity.class);
                    i.putExtra("job_id",idTextView.getText().toString());
                    startActivity(i);
                }
            });
        }
    }*/
}
