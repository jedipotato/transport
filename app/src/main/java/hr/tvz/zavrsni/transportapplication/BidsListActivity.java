package hr.tvz.zavrsni.transportapplication;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import hr.tvz.zavrsni.adapter.BidAdapter;
import hr.tvz.zavrsni.domain.api.Bids;
import hr.tvz.zavrsni.json.TransportApiListener;


public class BidsListActivity extends TransportActivity implements TransportApiListener<Bids> {
    private String mJobId = new String();
    private String mUserId = new String();
    private String mCategoryId = new String();
    private boolean mIsUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bids_list);

        if(getIntent().hasExtra("job_id")){
            mJobId = getIntent().getStringExtra("job_id");
        }
        if(getIntent().hasExtra("category_id")){
            mCategoryId = getIntent().getStringExtra("category_id");
        }
        if(getIntent().hasExtra("is_user")){
            mIsUser = getIntent().getBooleanExtra("is_user", false);
        }
        if(getIntent().hasExtra("user_id")){
            mUserId = getIntent().getStringExtra("user_id");
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_bids_list, menu);
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

        app.getBidsByJob(mJobId, mCategoryId);
    }

    @Override
    protected void onPause() {
        super.onPause();
        App app = (App) getApplication();
        app.removeTransportApiListener();
    }

    @Override
    public void onApiResponse(Bids response) {
        if (!checkUserAuthenticationResponseAndReset(response)) return;

        if(response.getSuccess() == 1) {
            Toast.makeText(getApplicationContext(), response.getMessage(), Toast.LENGTH_SHORT).show();
            ListView listView = (ListView) findViewById(R.id.bidList);
            BidAdapter adapter = new BidAdapter(getApplicationContext(), new ArrayList<>(response.getBidsList()));
            listView.setAdapter(adapter);
        }
        if(response.getSuccess() == 0) {
            Toast.makeText(getApplicationContext(), response.getMessage(), Toast.LENGTH_SHORT).show();
            ListView listView = (ListView) findViewById(R.id.bidList);
            BidAdapter adapter = new BidAdapter(getApplicationContext(), new ArrayList<>(response.getBidsList()));
            listView.setAdapter(adapter);
        }
        if(response.getSuccess() == -2){
            Toast.makeText(getApplicationContext(), response.getMessage(), Toast.LENGTH_SHORT).show();
        }

        EditText editBid = (EditText) findViewById(R.id.editJobBid);
        Button buttonPlaceBid = (Button) findViewById(R.id.buttonJobPlaceBid);

        if(mIsUser){
            editBid.setVisibility(View.GONE);
            buttonPlaceBid.setVisibility(View.GONE);
        }
    }

    @Override
    public void onApiFailure(String message) {
        super.alert(TextUtils.isEmpty(message) ? getString(R.string.api_alert_dialog_body) : message);
    }

    public void onClickPlaceBid(View view) {
        App app = (App) getApplication();
        app.setTransportApiListener(this);
        EditText editBid = (EditText) findViewById(R.id.editJobBid);
        app.postBid(mJobId, mCategoryId, editBid.getText().toString(),mUserId);
    }
}
