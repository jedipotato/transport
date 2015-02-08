package hr.tvz.zavrsni.domain;

import android.util.Log;

import com.google.gson.annotations.SerializedName;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;

import hr.tvz.zavrsni.util.Const;

public class Job {
    @SerializedName(Const.JOB_ID) private String id;
    @SerializedName(Const.CATEGORY_ID) private String categoryId;
    @SerializedName(Const.USER_ID ) private String userId;
    @SerializedName(Const.JOB_NAME) private String name;
    @SerializedName(Const.JOB_DESCRIPTION) private String description;
    @SerializedName(Const.JOB_CREATION_DATE) private Date creationDate;
    @SerializedName(Const.JOB_EXPIRATION_DATE) private Date expirationDate;

    public Job(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public static ArrayList<Job> getListFromJSON(JSONObject jsonObject) {
        ArrayList<Job> jobList = new ArrayList<Job>();
        try{

            JSONArray categories = jsonObject.getJSONArray("jobs");

            for (int i = 0; i < categories.length(); i++) {
                JSONObject tempObj = categories.getJSONObject(i);

                String id = tempObj.getString("job_id");
                String name = tempObj.getString("name");
                Log.e("id: ", id);
                Log.e("name",name);

                Job objJob = new Job(id, name);
                jobList.add(objJob);
            }
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
        return jobList;
    }
}
