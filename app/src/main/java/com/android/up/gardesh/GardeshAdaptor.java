package com.android.up.gardesh;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.up.Login;
import com.android.up.R;
import com.android.up.comment.MainComment;

import java.util.ConcurrentModificationException;

public class GardeshAdaptor extends BaseAdapter {

    Context context;

    public GardeshAdaptor(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return GardeshMain.order.size();
    }

    @Override
    public Object getItem(int position) {
        return GardeshMain.order.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null)
        {
            convertView = LayoutInflater.from(context).inflate(R.layout.gardesh_layout,parent,false);
            TextView title = convertView.findViewById(R.id.title_txt_gardesh);
            TextView to = convertView.findViewById(R.id.to_txt_gardesh);
            TextView from = convertView.findViewById(R.id.from_txt_gardesh);
            TextView price = convertView.findViewById(R.id.amount_txt_gardesh);

            String dollar = "$ ";
            String price_ = String.valueOf(GardeshMain.order.get(position).getPrice());
            title.setText(GardeshMain.order.get(position).getTitle());
            to.setText(GardeshMain.order.get(position).getTitle());
            from.setText(Login.u_info.get(Login.list_number).getLast_name());
            price.setText( dollar + price_);

        }
        return convertView;
    }
}
