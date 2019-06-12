package com.android.up;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

public class Pay {
    public void Pay(Context context,int price,String title,int id){
        DatabaseAccess db = new DatabaseAccess(context);
        String username = Login.u_info.get(Login.list_number).getUsername();
        String firstname = Login.u_info.get(Login.list_number).getFirst_name();
        String lastname = Login.u_info.get(Login.list_number).getLast_name();
        String sql = "Insert into orders(price,title,id,username,firstname,lastname) values('"+price+"','"+title+"','"+id+"','"+username+"','"+firstname+"','"+lastname+"')";
        db.getDb().execSQL(sql);
        ReduceFromInventory(context,price);
    }

    private void ReduceFromInventory(Context context,int price)
    {
        int inventory = Login.u_info.get(Login.list_number).getCardInventory() - price;
        if(inventory<0){
            Alert("Error","Not enough money in your card",context);
        }
        else{
            DatabaseAccess db = new DatabaseAccess(context);
            String sql = "update users set cardinventory = '"+inventory+"' where username='"+Login.u_info.get(Login.list_number).getUsername()+"'";
            db.getDb().execSQL(sql);
        }
    }

    public void Alert(String Message,String Title,Context context)
    {
        AlertDialog alertDialog1 = new AlertDialog.Builder(
                context).create();

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
}
