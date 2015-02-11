package hr.tvz.zavrsni.transportapplication;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import hr.tvz.zavrsni.adapter.BidAdapter;
import hr.tvz.zavrsni.domain.api.Bids;
import hr.tvz.zavrsni.json.TransportApiListener;


public class BidsListActivity extends TransportActivity implements TransportApiListener<Bids> {
    private String mJobId = new String();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bids_list);

        if(getIntent().hasExtra("job_id")){
            mJobId = getIntent().getStringExtra("job_id");
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

        app.getBidsByJob(mJobId);
        Toast.makeText(getApplicationContext(), "Loading bids...", Toast.LENGTH_SHORT).show();
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

        ListView listView = (ListView) findViewById(R.id.bidList);
        BidAdapter adapter = new BidAdapter(getApplicationContext(), new ArrayList<>(response.getBidsList()));
        listView.setAdapter(adapter);

    }

    @Override
    public void onApiFailure(String message) {
        super.alert(TextUtils.isEmpty(message) ? getString(R.string.api_alert_dialog_body) : message);
    }
}
