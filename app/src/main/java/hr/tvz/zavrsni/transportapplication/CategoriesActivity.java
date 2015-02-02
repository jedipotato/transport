package hr.tvz.zavrsni.transportapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import hr.tvz.zavrsni.adapter.CategoryAdapter;
import hr.tvz.zavrsni.domain.Category;
import hr.tvz.zavrsni.json.JSONParser;


public class CategoriesActivity extends ActionBarActivity {

    public ProgressDialog pDialog;
    public ArrayList<Category> categoryList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

        new LoadAllCategories().execute();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_categories, menu);
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

    class LoadAllCategories extends AsyncTask<String, Void, ArrayList<Category>>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(getApplicationContext(),"Loading categories...",Toast.LENGTH_SHORT).show();
            /*pDialog = new ProgressDialog(CategoriesActivity.this);
            pDialog.setMessage("");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();*/
        }

        /**
        * Pozadinsko učitavanje kategorija
        **/
        @Override
        protected ArrayList<Category> doInBackground(@NonNull String... params) {
            try {
                JSONParser jParser = new JSONParser();
                String url = "http://10.0.2.2/transportGetCategories.php";
                JSONObject jObject = jParser.makeHttpRequest(url);

                Integer success = jObject.getInt("success");

                if (success == 1) {
                    categoryList = Category.getListFromJSON(jObject);
                }
                else {
                    Toast.makeText(getApplicationContext(), "No categories available!", Toast.LENGTH_SHORT).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return categoryList;
        }

        @Override
        protected void onPostExecute(@NonNull ArrayList<Category> categoryList) {
            ListView listView = (ListView)findViewById(R.id.categoryList);
            CategoryAdapter adapter = new CategoryAdapter(getApplicationContext(),categoryList);
            listView.setAdapter(adapter);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(@NonNull AdapterView<?> parent, @NonNull View view, int position, long id) {
                    TextView idTextView = (TextView)view.findViewById(R.id.categoryId);
                    Intent i = new Intent(CategoriesActivity.this, JobListActivity.class);
                    i.putExtra("category_id",idTextView.getText().toString());

                    /*CategoryAdapter adapter = (CategoryAdapter) parent.getAdapter();
                    adapter.getItem(position).getId();*/
                    startActivity(i);
                }
            });

        }
    }
}
