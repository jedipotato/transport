package hr.tvz.zavrsni.transportapplication;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import hr.tvz.zavrsni.domain.api.Job;
import hr.tvz.zavrsni.json.TransportApiListener;


public class JobActivity extends TransportActivity implements TransportApiListener<Job> {
    private String mJobId = new String();
    private String mExpirationDate = new String();
    private String mCategoryId = new String();
    private boolean mIsUser;
    private String mUserId = new String();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job);

        if(getIntent().hasExtra("category_id")) {
            mCategoryId = getIntent().getStringExtra("category_id");
        }
        else{
            mCategoryId = null;
        }
        if(getIntent().hasExtra("job_id")) {
            mJobId = getIntent().getStringExtra("job_id");
        }
        else{
            mJobId = null;
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

        if(mCategoryId != null){
            app.getJob(mJobId,mCategoryId);
        }
        else{
            app.getJobById(mJobId);
        }

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
        if (!checkUserAuthenticationResponseAndReset(response)) return;

        TextView jobLowestBid = (TextView)findViewById(R.id.textJobBidValue);
        TextView textName = (TextView)findViewById(R.id.textJobName);
        TextView textDescription = (TextView)findViewById(R.id.textJobDescription);
        TextView textExpirationDate = (TextView)findViewById(R.id.textJobExpirationDateValue);

        if(response.getLowestBid() == null){
            jobLowestBid.setText("No bids!");
        }
        else{
            jobLowestBid.setText(response.getLowestBid());
        }
        textName.setText(response.getName());
        textDescription.setText(response.getDescription());
        textExpirationDate.setText(response.getExpirationDate());

        if(mCategoryId == null){
            response.getCategoryId();
        }
        mIsUser = response.isUser();
        mUserId = response.getUserId();
        mExpirationDate = response.getExpirationDate();

    }

    @Override
    public void onApiFailure(String message) {
        super.alert(TextUtils.isEmpty(message) ? getString(R.string.api_alert_dialog_body) : message);
    }

    public void onClickCurrentBids(View view) {
        Intent i = new Intent(JobActivity.this, BidsListActivity.class);
        i.putExtra("is_user", mIsUser);
        i.putExtra("job_id", mJobId);
        i.putExtra("user_id", mUserId);
        i.putExtra("category_id", mCategoryId);
        i.putExtra("expiration_date",mExpirationDate);
        startActivity(i);
    }
}
