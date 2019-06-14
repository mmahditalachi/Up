package com.android.up;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.android.up.model.Users;

public class CartToCart extends Fragment {

    boolean number1_check =false;
    boolean number2_check =false;
    private EditText number1,number2,amount;
    private Button pay;
    private Users u1,u2;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.cart_to_cart_layout,container,false);
        number1 = view.findViewById(R.id.src_number_cart_to_cart);
        number2 = view.findViewById(R.id.destination_cart_number_cart_to_cart);
        amount = view.findViewById(R.id.amount_number_cart_to_cart);
        pay = view.findViewById(R.id.submit_btn_cart_to_cart);
        PayBtn();
        return view;
    }

    private void Searching(){
        for (int i = 0; i < Login.u_info.size(); i++) {
            if(number1.getText().toString().equals(Login.u_info.get(i).getCardnum())){
                number1_check = true;
                u1 = Login.u_info.get(i);
                break;
            }
        }
        for (int i = 0; i < Login.u_info.size(); i++) {
            if(number2.getText().toString().equals(Login.u_info.get(i).getCardnum())){
                number2_check=true;
                u2 = Login.u_info.get(i);
                break;
            }
        }
    }

    private void PayBtn(){
        Context context = this.getActivity();
        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Searching();
                if(number1_check && number2_check && !amount.equals("")){
                    Alert();
                }else {
                    Pay p = new Pay();
                    p.Alert("origin card or destination card is not available","Incorrect Message",context);
                }
            }
        });
    }
    private void Alert(){

        String Message ="Origin : " + u1.getUsername() + "   " + u1.getCardnum() + "\n" + "Destination : "+ u2.getUsername() + "   " + u2.getCardnum();
        AlertDialog alertDialog1 = new AlertDialog.Builder(
                this.getActivity()).create();

        alertDialog1.setTitle("Are you sure?");
        alertDialog1.setMessage(Message);
        alertDialog1.setButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        alertDialog1.setButton(DialogInterface.BUTTON_POSITIVE, "Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                UpdateInventory();
            }
        });
        alertDialog1.setButton(DialogInterface.BUTTON_NEGATIVE, "No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        alertDialog1.show();
    }

    private void UpdateInventory(){
        DatabaseAccess db = new DatabaseAccess(this.getActivity());
        int[] inventory = GetInventory();
        String sql = "Update Users set cardinventory = '"+inventory[0]+"' where cardnum ='"+u1.getCardnum()+"' ";
        db.getDb().execSQL(sql);
        String sql2 = "Update Users set cardinventory = '"+inventory[1]+"' where cardnum ='"+u2.getCardnum()+"' ";
        db.getDb().execSQL(sql2);
    }

    private int[] GetInventory(){
        int[] inventory = new int[2];
        int update_1 = u1.getCardInventory() - Integer.parseInt(amount.getText().toString());
        int update_2 = u2.getCardInventory() + update_1;
        inventory[0] = update_1;
        inventory[1] = update_2;
        return inventory;
    }
}
