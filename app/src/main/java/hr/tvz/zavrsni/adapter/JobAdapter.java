package hr.tvz.zavrsni.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import hr.tvz.zavrsni.domain.api.Job;
import hr.tvz.zavrsni.transportapplication.R;

public class JobAdapter extends ArrayAdapter<Job> {

    public JobAdapter(Context context, ArrayList<Job> jobList) {
        super(context, 0, jobList);
    }

    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_jobs, parent, false);
            holder=new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Job job = getItem(position);

        Log.d("JOB_ID", "jobId = " + job.getId());
        holder.jobId.setText(job.getId());
        holder.categoryId.setText(job.getCategoryId());
        holder.jobName.setText(job.getName());

        return convertView;
    }

    static class ViewHolder {
        TextView jobId;
        TextView categoryId;
        TextView jobName;

        ViewHolder(View convertView){
            jobId = (TextView)convertView.findViewById(R.id.jobId);
            categoryId = (TextView)convertView.findViewById(R.id.categoryId);
            jobName = (TextView)convertView.findViewById(R.id.jobName);
        }
    }
}
