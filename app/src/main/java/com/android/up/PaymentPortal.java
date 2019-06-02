package com.android.up;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.up.house.HouseAdaptor;
import com.android.up.house.HouseMain;

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

        price.setText(String.valueOf(ProductHomePage.price_));

        Payment_btn();
    }

    private void Payment_btn()
    {
            payment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String cardNum = card_num1.getText().toString() + card_num2.getText().toString() + card_num3.getText().toString() + card_num4.getText().toString();
                    if (cardNum.equals(Login.u_info.get(Login.list_number).getCardnum()) && password.getText().toString().equals(Login.u_info.get(Login.list_number).getPass())) {
                        Log.d("payment","checker");
                        System.out.println(cardNum);
                        System.out.println(password);
                        ReduceFromInventory();
                        InsertToDatabase();
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

    private void ReduceFromInventory()
    {
        int inventory = Login.u_info.get(Login.list_number).getCardInventory() - Integer.parseInt(price.getText().toString());
        if(inventory<0){
            Alert("Error","Not enough money in your card");
            GoToHomePage();
        }
        else{
        DatabaseAccess db = new DatabaseAccess(this);
        String sql = "update users set cardinventory = '"+inventory+"' where username='"+Login.u_info.get(Login.list_number).getUsername()+"'";
        db.getDb().execSQL(sql);
        }
    }

    private void GoToHomePage()
    {
        Intent intent = new Intent(this,HomePage.class);
        startActivity(intent);
    }

    private void InsertToDatabase()
    {
        DatabaseAccess db = new DatabaseAccess(this);
        int price = HouseMain.house.get(HouseAdaptor.id-1).getPrice();
        int id = HouseMain.house.get(HouseAdaptor.id-1).getId();
        String title = HouseMain.house.get(HouseAdaptor.id-1).getTitle();
        String username = Login.u_info.get(Login.list_number).getUsername();
        String firstname = Login.u_info.get(Login.list_number).getFirst_name();
        String lastname = Login.u_info.get(Login.list_number).getLast_name();

        String sql = "Insert into orders(price,title,id,username,firstname,lastname) Values('"+price+"','"+title+"','"+id+"','"+username+"','"+firstname+"','"+lastname+"')";
        db.getDb().execSQL(sql);
    }
}
