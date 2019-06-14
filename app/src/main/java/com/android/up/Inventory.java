package com.android.up;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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

public class Inventory extends Fragment {

    private Button show;
    private int invemtory_;
    private EditText number,password;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.mojodi_layout,container,false);
        show = view.findViewById(R.id.count_btn_mojodi);
        number = view.findViewById(R.id.cart_number_txt_mojodi);
        password = view.findViewById(R.id.password_txt_mojodi);
        Context context = this.getActivity();
        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!password.getText().toString().equals("") && !number.getText().toString().equals("")) {
                    Searching();
                }else {
                    Pay p =new Pay();
                    p.Alert("filling the blanks","Incorrect information",context);
                }
            }
        });
        return view;
    }
    private void Searching(){
        DatabaseAccess db = new DatabaseAccess(this.getActivity());
        Cursor c = db.getDb().rawQuery("Select cardinventory,cardnum from users where cardnum = '"+number.getText().toString()+"'",null);
        c.moveToFirst();
        while (!c.isAfterLast()){
            String CardNumber = c.getString(c.getColumnIndex("cardnum"));
            int inventory = c.getInt(c.getColumnIndex("cardinventory"));
            if(CardNumber.equals(number.getText().toString()) && password.getText().toString().equals(Login.u_info.get(Login.list_number).getPass())){
                invemtory_ = inventory;
                break;
            }
            c.moveToNext();
        }
        if(invemtory_ == 0 ){
            Pay p = new Pay();
            p.Alert("yur cardnumber is not available in our database","Incorrect information",this.getActivity());
        }else{
            PopUp(invemtory_);
        }
    }
    private void PopUp(int number){
        String Message ="$ " + number;
        AlertDialog alertDialog1 = new AlertDialog.Builder(
                this.getActivity()).create();

        alertDialog1.setTitle("Wallet");
        alertDialog1.setMessage(Message);
        alertDialog1.setButton(DialogInterface.BUTTON_POSITIVE, "Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        alertDialog1.show();
    }

}
