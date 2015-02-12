package hr.tvz.zavrsni.domain.api;

import com.google.gson.annotations.SerializedName;

import hr.tvz.zavrsni.util.Const;

public class Job extends BasicModel {
    @SerializedName(Const.JOB_ID) private String id;
    @SerializedName(Const.CATEGORY_ID) private String categoryId;
    @SerializedName(Const.USER_ID) private String userId;
    @SerializedName(Const.JOB_NAME) private String name;
    @SerializedName(Const.JOB_DESCRIPTION) private String description;
    @SerializedName(Const.JOB_CREATION_DATE) private String creationDate;
    @SerializedName(Const.JOB_EXPIRATION_DATE) private String expirationDate;
    @SerializedName(Const.JOB_LOWEST_BID) private String lowestBid;
    @SerializedName(Const.JOB_LOWEST_USER) private String lowestUsers;
    @SerializedName(Const.JOB_DURATION) private String duration;

    @SerializedName(Const.IS_USER) private boolean isUser;

    public Job(String categoryId, String name, String description, String duration) {
        this.categoryId = categoryId;
        this.name = name;
        this.description = description;
        this.duration = duration;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
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

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public boolean isUser() {
        return isUser;
    }

    public String getLowestBid() {
        return lowestBid;
    }

    public void setLowestBid(String lowestBid) {
        this.lowestBid = lowestBid;
    }

    public String getLowestUsers() {
        return lowestUsers;
    }

    public void setLowestUsers(String lowestUsers) {
        this.lowestUsers = lowestUsers;
    }

    public void setUser(boolean isUser) {
        this.isUser = isUser;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Job)) return false;

        Job job = (Job) o;

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
                ", creationDate='" + creationDate + '\'' +
                ", expirationDate='" + expirationDate + '\'' +
                '}';
    }
}
