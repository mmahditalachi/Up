package com.android.up.comment;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.android.up.DatabaseAccess;
import com.android.up.R;
import com.android.up.house.HouseAdaptor;
import com.android.up.house.HouseMain;
import com.android.up.model.Comments;

import java.util.ArrayList;
import java.util.List;

public class MainComment extends Activity {

    public static List<Comments> comment;
    private static final String TAG =  "MainComment";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.comment_layout);
        comment = new ArrayList<>();
        SelectCommentsFromDatabase();
        ListView l = findViewById(R.id.comment_list);
        CommentAdaptor c = new CommentAdaptor(this);
        l.setAdapter(c);
    }

    public void SelectCommentsFromDatabase()
    {
        Log.d(TAG,"user uses comment function to send comment");
        DatabaseAccess db = new DatabaseAccess(this);
        int id = HouseAdaptor.id;
        Cursor c = db.getDb().rawQuery("Select * from comments where id ='"+id+"' ",null);
        c.moveToFirst();
        while(!c.isAfterLast())
        {
            String text = c.getString(0);
            int product_id = c.getInt(1);
            String username = c.getString(2);
            int like = c.getInt(3);
            int dislike = c.getInt(4);

            Comments comments = new Comments(username,text,product_id, like,dislike);
            comment.add(comments);
            c.moveToNext();
        }
    }
}
