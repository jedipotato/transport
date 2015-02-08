package hr.tvz.zavrsni.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import hr.tvz.zavrsni.domain.Category;
import hr.tvz.zavrsni.transportapplication.R;

public class CategoryAdapter extends ArrayAdapter<Category> {

    public CategoryAdapter(Context context, ArrayList<Category> categoryList){
        super(context, 0, categoryList);
    }

    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_categories, parent, false);
            holder=new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Category category = getItem(position);

        holder.categoryId.setText(category.getId());
        holder.categoryName.setText(category.getName());

        return convertView;
    }

    static class ViewHolder {
        TextView categoryId;
        TextView categoryName;

        ViewHolder(View convertView){
            categoryId = (TextView)convertView.findViewById(R.id.categoryId);
            categoryName = (TextView)convertView.findViewById(R.id.categoryName);
        }
    }
}
