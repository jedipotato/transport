package hr.tvz.zavrsni.domain;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Kristian on 20.1.2015..
 */
public class Category {
    private String id;
    private String name;

    public Category() {
    }

    public Category(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static ArrayList<Category> getListFromJSON(JSONObject jsonObject) {
        ArrayList<Category> categoryList = new ArrayList<Category>();
        try{

            JSONArray categories = jsonObject.getJSONArray("categories");

            for (int i = 0; i < categories.length(); i++) {
                JSONObject tempObj = categories.getJSONObject(i);

                String id = tempObj.getString("category_id");
                String name = tempObj.getString("name");
                Log.e("id: ", id);
                Log.e("name",name);

                Category objCategory = new Category(id, name);
                categoryList.add(objCategory);
            }
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
        return categoryList;
    }
}
