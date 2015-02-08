package hr.tvz.zavrsni.domain.api;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

import hr.tvz.zavrsni.util.Const;

/**
 * Created by Kristian on 8.2.2015..
 */
public class Job {
    @SerializedName(Const.JOB_ID) private String id;
    @SerializedName(Const.CATEGORY_ID) private String categoryId;
    @SerializedName(Const.USER_ID) private String userId;
    @SerializedName(Const.JOB_NAME) private String name;
    @SerializedName(Const.JOB_DESCRIPTION) private String description;
    @SerializedName(Const.JOB_CREATION_DATE) private Date creationDate;
    @SerializedName(Const.JOB_EXPIRATION_DATE) private Date expirationDate;

    @SerializedName(Const.SUCCESS) private int success;

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

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Job)) return false;

        Job job = (Job) o;

        if (success != job.success) return false;
        if (!categoryId.equals(job.categoryId)) return false;
        if (!creationDate.equals(job.creationDate)) return false;
        if (!description.equals(job.description)) return false;
        if (!expirationDate.equals(job.expirationDate)) return false;
        if (!id.equals(job.id)) return false;
        if (!name.equals(job.name)) return false;
        if (!userId.equals(job.userId)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + categoryId.hashCode();
        result = 31 * result + userId.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + description.hashCode();
        result = 31 * result + creationDate.hashCode();
        result = 31 * result + expirationDate.hashCode();
        result = 31 * result + success;
        return result;
    }

    @Override
    public String toString() {
        return "Job{" +
                "id='" + id + '\'' +
                ", categoryId='" + categoryId + '\'' +
                ", userId='" + userId + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", creationDate=" + creationDate +
                ", expirationDate=" + expirationDate +
                ", success=" + success +
                '}';
    }
}
