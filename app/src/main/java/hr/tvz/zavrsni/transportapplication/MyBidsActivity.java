package hr.tvz.zavrsni.transportapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import hr.tvz.zavrsni.adapter.BidAdapterUser;
import hr.tvz.zavrsni.domain.api.Bids;
import hr.tvz.zavrsni.json.TransportApiListener;


public class MyBidsActivity extends TransportActivity implements TransportApiListener<Bids> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_bids);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_my_bids, menu);
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

        app.getBidsByUser();
        Toast.makeText(getApplicationContext(), "Loading bids...", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onApiResponse(Bids response) {
        if (!checkUserAuthenticationResponseAndReset(response)) return;

        ListView listView = (ListView) findViewById(R.id.bidList);
        BidAdapterUser adapter = new BidAdapterUser(getApplicationContext(), new ArrayList<>(response.getBidsList()));
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull AdapterView<?> parent, @NonNull View view, int position, long id) {
                TextView idJobTextView = (TextView) view.findViewById(R.id.jobId);
                Intent i = new Intent(MyBidsActivity.this, JobActivity.class);

                i.putExtra("job_id", idJobTextView.getText().toString());

                startActivity(i);
            }
        });
    }

    @Override
    public void onApiFailure(String message) {
        super.alert(TextUtils.isEmpty(message) ? getString(R.string.api_alert_dialog_body) : message);
    }
}
