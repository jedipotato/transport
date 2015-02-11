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


public class BidAdapter extends ArrayAdapter<Bid> {

    public BidAdapter(Context context, int resource, List<Bid> bidList) {
        super(context, 0, bidList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_bids, parent, false);
            holder=new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Bid bid = getItem(position);

        holder.bidId.setText(bid.getId());
        holder.bidValue.setText(bid.getBid());

        return convertView;
    }

    static class ViewHolder {
        TextView bidId;
        TextView bidValue;

        ViewHolder(View convertView){
            bidId = (TextView)convertView.findViewById(R.id.bidId);
            bidValue = (TextView)convertView.findViewById(R.id.bidValue);
        }
    }
}