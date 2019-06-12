package com.android.up;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.up.model.PayObject;

import java.util.ArrayList;
import java.util.List;


public class PaymentPortal extends Activity {

    private Button payment;
    private EditText card_num1,card_num2,card_num3,card_num4,password;
    private TextView price;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment_portal_layout);
        payment = findViewById(R.id.payment_pay);
        card_num1 = findViewById(R.id.num_1);
        card_num2 = findViewById(R.id.num_2);
        card_num3 = findViewById(R.id.num_3);
        card_num4 = findViewById(R.id.num_4);
        price = findViewById(R.id.payment_price);
        password = findViewById(R.id.payment_password);
        price.setText(String.valueOf(HomePage.payList.get(HomePage.payList.size()-1).getPrice()));

        Payment_btn();
    }


    private void Payment_btn()
    {
        int price = HomePage.payList.get(HomePage.payList.size()-1).getPrice();
        int id = HomePage.payList.get(HomePage.payList.size()-1).getId();
        String title = HomePage.payList.get(HomePage.payList.size()-1).getTitle();
            payment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String cardNum = card_num1.getText().toString() + card_num2.getText().toString() + card_num3.getText().toString() + card_num4.getText().toString();
                    if (cardNum.equals(Login.u_info.get(Login.list_number).getCardnum()) && password.getText().toString().equals(Login.u_info.get(Login.list_number).getPass())) {
                        Pay pay = new Pay();
                        pay.Pay(PaymentPortal.this,price,title,id);
                        Login login = new Login();
                        login.SelectUsersData(PaymentPortal.this);
                        GoToHomePage();
                    }
                    else{
                        Alert("Error","incorrect information");
                    }
                }
            });
    }
    public void Alert(String title,String Message)
    {
        AlertDialog alertDialog1 = new AlertDialog.Builder(
                PaymentPortal.this).create();

        alertDialog1.setTitle(title);
        alertDialog1.setMessage(Message);
        alertDialog1.setButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
//                            Toast.makeText(getApplicationContext(),
//                                    "You clicked on OK", Toast.LENGTH_SHORT).show();
            }
        });
        alertDialog1.show();
    }

    private void GoToHomePage()
    {
        Intent intent = new Intent(this,HomePage.class);
        startActivity(intent);
    }
}
