package com.android.up.comment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.android.up.R;

public class CommentAdaptor extends BaseAdapter {

    Context context;

    public CommentAdaptor(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return MainComment.comment.size();
    }

    @Override
    public Object getItem(int position) {
        return MainComment.comment.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null)
        {
            convertView = LayoutInflater.from(context).inflate(R.layout.comment_list_layout,parent,false);
            TextView comment = convertView.findViewById(R.id.text_comment_list);
            TextView username = convertView.findViewById(R.id.username_comment_list);

            comment.setText(MainComment.comment.get(position).getText());
            username.setText(MainComment.comment.get(position).getUsername());

        }
        return convertView;
    }
}
