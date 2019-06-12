package com.android.up.house;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.up.HomePage;
import com.android.up.Login;
import com.android.up.PaymentPortal;
import com.android.up.ProductHomePage;
import com.android.up.R;
import com.android.up.model.PayObject;
import com.bumptech.glide.Glide;

import de.hdodenhof.circleimageview.CircleImageView;

public class HouseAdaptor extends RecyclerView.Adapter<HouseAdaptor.ViewHolder> {

    Context context;
    public static int id;

    public HouseAdaptor(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.house_recycleview, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder,final int i) {
        viewHolder.imagetext.setText(HouseMain.house.get(i).getTitle());
        viewHolder.price.setText(String.valueOf(HouseMain.house.get(i).getPrice()));
        Glide.with(context).asBitmap().load(HouseMain.house.get(i).getImage()).into(viewHolder.image);

        int price =  HouseMain.house.get(i).getPrice();
        int id = HouseMain.house.get(i).getId();
        String title = HouseMain.house.get(i).getTitle();

        PayObject payObject = new PayObject(id,price,title);
        viewHolder.parent_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HomePage.payList.add(payObject);
                GoToProdectPage();
            }
        });
    }

    @Override
    public int getItemCount() {
        return HouseMain.house.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView image;
        TextView imagetext,price;
        RelativeLayout parent_layout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            imagetext = itemView.findViewById(R.id.image_name);
            parent_layout = itemView.findViewById(R.id.parent_layout);
            price= itemView.findViewById(R.id.product_price);
        }
    }

    private void GoToProdectPage()
    {
        Intent intent = new Intent(this.context, ProductHomePage.class);
        context.startActivity(intent);

    }
}
