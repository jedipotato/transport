package hr.tvz.zavrsni.domain.api;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import hr.tvz.zavrsni.domain.Job;
import hr.tvz.zavrsni.util.Const;

/**
 * Created by Kristian on 7.2.2015..
 */
public class Jobs {

    @SerializedName(Const.JOBS) private List<Job> jobsList;
    @SerializedName(Const.SUCCESS) private int success;

    public List<Job> getJobsList() {
        return jobsList;
    }

    public void setJobsList(List<Job> jobsList) {
        this.jobsList = jobsList;
    }

    public boolean isSuccess() {
        return success == 1;

    }

    public void setSuccess(int success) {
        this.success = success;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Jobs that = (Jobs) o;

        if (success != that.success) return false;
        if (jobsList != null ? !jobsList.equals(that.jobsList) : that.jobsList != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = jobsList != null ? jobsList.hashCode() : 0;
        result = 31 * result + success;
        return result;
    }

    @Override
    public String toString() {
        return "Jobs{" +
                "jobsList=" + jobsList +
                ", success=" + success +
                '}';
    }
}
