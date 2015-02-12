package hr.tvz.zavrsni.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import hr.tvz.zavrsni.domain.api.Bid;
import hr.tvz.zavrsni.transportapplication.R;

/**
 * Created by Kristian on 11.2.2015..
 */
public class BidAdapterUser extends ArrayAdapter<Bid> {
    public BidAdapterUser(Context context, List<Bid> bidList) {
        super(context, 0, bidList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_user_bids, parent, false);
            holder=new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Bid bid = getItem(position);

        holder.jobId.setText(bid.getJobId());
        holder.bidValue.setText(bid.getBid());
        holder.jobName.setText(bid.getJobName());

        return convertView;
    }

    static class ViewHolder {
        TextView jobId;
        TextView bidValue;
        TextView jobName;

        ViewHolder(View convertView){
            jobId = (TextView)convertView.findViewById(R.id.jobId);
            bidValue = (TextView)convertView.findViewById(R.id.bidValue);
            jobName = (TextView)convertView.findViewById(R.id.jobName);
        }
    }
}
