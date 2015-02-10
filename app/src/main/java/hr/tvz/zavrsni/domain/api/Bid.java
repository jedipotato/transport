package hr.tvz.zavrsni.domain.api;

import com.google.gson.annotations.SerializedName;

import hr.tvz.zavrsni.util.Const;

public class Bid extends BasicModel {
    @SerializedName(Const.BID_ID) private String id;
    @SerializedName(Const.JOB_ID) private String jobId;
    @SerializedName(Const.USER_ID) private String userId;
    @SerializedName(Const.BID_BID) private String bid;
    @SerializedName(Const.BID_DESCRIPTION) private String description;
    @SerializedName(Const.BID_CREATION_DATE) private String creationDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getBid() {
        return bid;
    }

    public void setBid(String bid) {
        this.bid = bid;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Bid)) return false;
        if (!super.equals(o)) return false;

        Bid bid1 = (Bid) o;

        if (bid != null ? !bid.equals(bid1.bid) : bid1.bid != null) return false;
        if (creationDate != null ? !creationDate.equals(bid1.creationDate) : bid1.creationDate != null)
            return false;
        if (description != null ? !description.equals(bid1.description) : bid1.description != null)
            return false;
        if (id != null ? !id.equals(bid1.id) : bid1.id != null) return false;
        if (jobId != null ? !jobId.equals(bid1.jobId) : bid1.jobId != null) return false;
        if (userId != null ? !userId.equals(bid1.userId) : bid1.userId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (id != null ? id.hashCode() : 0);
        result = 31 * result + (jobId != null ? jobId.hashCode() : 0);
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (bid != null ? bid.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (creationDate != null ? creationDate.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Bid{" +
                "id='" + id + '\'' +
                ", jobId='" + jobId + '\'' +
                ", userId='" + userId + '\'' +
                ", bid='" + bid + '\'' +
                ", description='" + description + '\'' +
                ", creationDate='" + creationDate + '\'' +
                '}';
    }
}
