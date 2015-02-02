package hr.tvz.zavrsni.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import hr.tvz.zavrsni.domain.Job;
import hr.tvz.zavrsni.transportapplication.R;

public class JobAdapter extends ArrayAdapter<Job> {
    public JobAdapter(Context context, ArrayList<Job> jobList) {
        super(context, 0, jobList);
    }

    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        Job job = getItem(position);

        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_jobs, parent, false);
        }

        TextView idView = (TextView)convertView.findViewById(R.id.jobId);
        TextView nameView = (TextView)convertView.findViewById(R.id.jobName);

        idView.setText(job.getId());
        nameView.setText(job.getName());

        return convertView;
    }
}
