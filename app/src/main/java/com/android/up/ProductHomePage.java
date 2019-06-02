package com.android.up;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.up.comment.MainComment;
import com.android.up.house.HouseAdaptor;
import com.android.up.house.HouseMain;
import com.android.up.model.House;
import com.bumptech.glide.Glide;

public class ProductHomePage extends AppCompatActivity {
    private static final String TAG = "ProductHomePage";
    private Button addtocart,send_comment,Show_Comments;
    private ImageView image;
    public static int price_;
    private EditText comment,quantity;
    private TextView title,detail,price;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_page_layout);

        title = findViewById(R.id.product_name_product_page);
        image = findViewById(R.id.product_image_product_page);
        detail = findViewById(R.id.information_product_page);
        price = findViewById(R.id.price_product_page);
        send_comment = findViewById(R.id.send_comment);
        comment = findViewById(R.id.comment_ed);
        addtocart = findViewById(R.id.addtobag_btn_product_page);
        Show_Comments = findViewById(R.id.comment_btn_product_page);

        SelectData();
        AddToCart_btn();
        Send_Comment();
        ShowComments();

    }

    public void ShowComments()
    {
        Show_Comments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GoToComments();
            }
        });
    }

    public void GoToComments()
    {
        Intent intent = new Intent(this,MainComment.class);
        startActivity(intent);
    }

    public void Send_Comment()
    {
        send_comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(comment.getText().toString().equals(""))
                    Alert();
                else {
                    Comment();
                    Successful();
//                    GoToCart();
                }
            }
        });
    }
    public void Successful()
    {
        AlertDialog alertDialog1 = new AlertDialog.Builder(
                ProductHomePage.this).create();

        alertDialog1.setTitle("Successful");
        alertDialog1.setMessage("your massage is send +\n+ thank you");
        alertDialog1.setButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
//                            Toast.makeText(getApplicationContext(),
//                                    "You clicked on OK", Toast.LENGTH_SHORT).show();
            }
        });
        alertDialog1.show();
    }

    public void Alert()
    {
        AlertDialog alertDialog1 = new AlertDialog.Builder(
                ProductHomePage.this).create();

        alertDialog1.setTitle("Error");
        alertDialog1.setMessage("write something !!");
        alertDialog1.setButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
//                            Toast.makeText(getApplicationContext(),
//                                    "You clicked on OK", Toast.LENGTH_SHORT).show();
            }
        });
        alertDialog1.show();
    }

    public void Comment()
    {
        DatabaseAccess db = new DatabaseAccess(this);
        //correct id of our product
        int product_id = HouseAdaptor.id;
        String comment_ = comment.getText().toString();
        String sql = "Insert into comments(comment,username,id) values('"+comment_+"','"+Login.u_info.get(Login.list_number).getUsername()+"','"+product_id+"')";
        db.getDb().execSQL(sql);
    }

    private void AddToCart_btn()
    {
        addtocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    GoToPayment();
                    GoToHomePage();
            }
        });
    }

    private void GoToPayment()
    {
        Intent intent = new Intent(this,PaymentPortal.class);
        startActivity(intent);
    }

    private void GoToHomePage()
    {
        Intent intent = new Intent(this,PaymentPortal.class);
        startActivity(intent);
    }


    public void SelectData()
    {
        Log.d(TAG,"receive data from product list");
        for (int i = 0; i < HouseMain.house.size(); i++) {
            if(HouseAdaptor.id == HouseMain.house.get(i).getId())
            {
                title.setText(HouseMain.house.get(i).getTitle());
                Bitmap bmp;
                Glide.with(this)
                        .asBitmap()
                        .load(HouseMain.house.get(i).getImage())
                        .into(image);

                price.setText(Integer.toString(HouseMain.house.get(i).getPrice()));
                price_ = HouseMain.house.get(i).getPrice();
                detail.setText(HouseMain.house.get(i).getDetails());
            }
        }
    }


}

