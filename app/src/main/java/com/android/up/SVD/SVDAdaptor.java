package com.android.up.SVD;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.up.DatabaseAccess;
import com.android.up.Login;
import com.android.up.R;
import com.android.up.model.Rating;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SVDAdaptor extends RecyclerView.Adapter<SVDAdaptor.ViewHolder> {

    Context context;
    public static List<Rating> rating;

    public SVDAdaptor(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater= LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.svd_adaptor_layout,null);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder,final int i) {

        viewHolder.title .setText(SVDMain.news.get(i).getTitle());;
        viewHolder.details.setText(SVDMain.news.get(i).getDetails());
        viewHolder.ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                if (fromUser){
                    viewHolder.ratingTV.setText(String.valueOf(rating));
                }
            }
        });

        viewHolder.vote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!viewHolder.ratingTV.getText().toString().equals(""))
                    InsertUserRating(Float.parseFloat(viewHolder.ratingTV.getText().toString()),i);
                    Alert(" ","Thanks");
            }
        });

        Glide.with(context).asBitmap().load(SVDMain.news.get(i).getImage()).into(viewHolder.image);

    }

    public void Alert(String message,String title)
    {
        AlertDialog alertDialog1 = new AlertDialog.Builder(
                this.context).create();

        alertDialog1.setTitle(title);
        alertDialog1.setMessage(message);
        alertDialog1.setButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
//                            Toast.makeText(getApplicationContext(),
//                                    "You clicked on OK", Toast.LENGTH_SHORT).show();
            }
        });
        alertDialog1.show();
    }

    private void InsertUserRating(float rating,int i)
    {
        Log.i("SVDAdaptor","InsertUserRating");
        DeleteFromDatabase(i);
        DatabaseAccess db = new DatabaseAccess(this.context);
        String sql = "Insert into rating(rating,id,username) values('"+rating+"','"+SVDMain.news.get(i).getId()+"','"+Login.u_info.get(Login.list_number).getUsername()+"')";
        db.getDb().execSQL(sql);
    }

    private void DeleteFromDatabase(int i)
    {
        String username = Login.u_info.get(Login.list_number).getUsername();
        DatabaseAccess db = new DatabaseAccess(this.context);
        String sql ="Delete from rating where username ='"+username+"' AND id ='"+SVDMain.news.get(i).getId()+"'";
        db.getDb().execSQL(sql);
    }


    @Override
    public int getItemCount() {
        return SVDMain.news.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView image;
        TextView details,title,ratingTV;
        RelativeLayout parent_layout;
        RatingBar ratingBar;
        Button vote;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title_news);
            image = itemView.findViewById(R.id.image_news);
            details = itemView.findViewById(R.id.image_detail_news);
            parent_layout = itemView.findViewById(R.id.relative_news);
            ratingBar = itemView.findViewById(R.id.rating);
            ratingTV = itemView.findViewById(R.id.rating_value);
            vote = itemView.findViewById(R.id.vote_news);
        }
    }
}
