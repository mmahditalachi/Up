package com.android.up;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.up.model.MobileBill;
import com.android.up.model.PayObject;

public class MobileCharge extends Fragment {

    private EditText mobile_number;
    private TextView price;
    private Button show,pay;
    private MobileBill mb;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.charge_mobile_layout,container,false);
        mobile_number = view.findViewById(R.id.mobile_number_txt_mobile_charge);
        price  = view.findViewById(R.id.price_txt_mobile_charge);
        show = view.findViewById(R.id.count_btn_mobile_charge);
        pay = view.findViewById(R.id.pay_btn_mobile_charge);

        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GetDataFromDatabase();
            }
        });


        return view;
    }

    private void GoToPayment(){
        Intent intent = new Intent(this.getContext(),PaymentPortal.class);
        getActivity().startActivity(intent);
    }

    private void GetDataFromDatabase(){
        DatabaseAccess db = new DatabaseAccess(this.getActivity());
        Cursor c = db.getDb().rawQuery("Select * from mobilebill",null);
        c.moveToFirst();
        while (!c.isAfterLast()){
            String number = c.getString(0);
            int price = c.getInt(2);
            int id = c.getInt(1);
            if(number.equals(mobile_number.getText().toString())){
                mb = new MobileBill(number,id,price);
                break;
            }
            c.moveToNext();
        }
        if (mb == null){
            Alert("your number is not in our database","Not found");
        }
        else {
            PayObject payObject = new PayObject(mb.getId(),mb.getPrice(),"MobileCharge");
            price.setText(String.valueOf(mb.getPrice()));
            pay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    HomePage.payList.add(payObject);
                    UpdateDatabase();
                    GoToPayment();
                }
            });
        }
    }
    public void Alert(String Message,String Title)
    {
        AlertDialog alertDialog1 = new AlertDialog.Builder(
                this.getActivity()).create();

        alertDialog1.setTitle(Title);
        alertDialog1.setMessage(Message);
        alertDialog1.setButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
//                            Toast.makeText(getApplicationContext(),
//                                    "You clicked on OK", Toast.LENGTH_SHORT).show();
            }
        });
        alertDialog1.show();
    }

    private void UpdateDatabase(){
        DatabaseAccess db = new DatabaseAccess(this.getActivity());
        int zero =0;
        String sql ="Update mobilebill set price = '"+zero+"' where number ='"+mobile_number.getText().toString()+"'";
        db.getDb().execSQL(sql);
    }
}
