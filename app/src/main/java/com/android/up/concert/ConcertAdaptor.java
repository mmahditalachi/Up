package com.android.up.concert;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.up.HomePage;
import com.android.up.PaymentPortal;
import com.android.up.ProductHomePage;
import com.android.up.R;
import com.android.up.house.HouseAdaptor;
import com.android.up.house.HouseMain;
import com.android.up.model.PayObject;
import com.bumptech.glide.Glide;

public class ConcertAdaptor extends RecyclerView.Adapter<ConcertAdaptor.ViewHolder> {

    Context context;

    public ConcertAdaptor(Context context) {
        this.context = context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.concert_recycle_layout, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.imagetext.setText(ConcertMain.concert.get(i).getDetails());
        String c = String.valueOf(ConcertMain.concert.get(i).getPrice());
        viewHolder.price.setText("$ "+ c);
        viewHolder.title.setText(ConcertMain.concert.get(i).getTitle());
        Glide.with(context).asBitmap().load(ConcertMain.concert.get(i).getImage()).into(viewHolder.image);

        int price =  ConcertMain.concert.get(i).getPrice();
        int id =ConcertMain.concert.get(i).getId();
        String title = ConcertMain.concert.get(i).getTitle();

        PayObject payObject = new PayObject(id,price,title);
        viewHolder.parent_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HomePage.payList.add(payObject);
                GoToPayment();
            }
        });
    }

    @Override
    public int getItemCount() {
        return ConcertMain.concert.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView image;
        TextView imagetext,price,title;
        RelativeLayout parent_layout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.concert_title);
            image = itemView.findViewById(R.id.concert_image);
            imagetext = itemView.findViewById(R.id.details_concert);
            parent_layout = itemView.findViewById(R.id.layout_2);
            price= itemView.findViewById(R.id.prce_concert);
        }
    }

    private void GoToPayment()
    {
        Intent intent = new Intent(this.context, PaymentPortal.class);
        context.startActivity(intent);
    }
}
