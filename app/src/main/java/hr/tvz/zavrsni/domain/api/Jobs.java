package hr.tvz.zavrsni.domain.api;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import hr.tvz.zavrsni.util.Const;

/**
 * Created by Kristian on 7.2.2015..
 */
public class Jobs extends BasicModel {

    @SerializedName(Const.JOBS) private ArrayList<Job> jobsList;

    public ArrayList<Job> getJobsList() {
        return jobsList;
    }

    public void setJobsList(ArrayList<Job> jobsList) {
        this.jobsList = jobsList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Jobs jobs = (Jobs) o;

        if (jobsList != null ? !jobsList.equals(jobs.jobsList) : jobs.jobsList != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (jobsList != null ? jobsList.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Jobs{" +
                "jobsList=" + jobsList +
                '}';
    }
}
