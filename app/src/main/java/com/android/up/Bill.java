package com.android.up;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.up.model.BillObject;
import com.android.up.model.PayObject;

import java.util.ArrayList;
import java.util.List;

public class Bill extends Fragment implements AdapterView.OnItemSelectedListener {
    private Spinner spinner;
    private EditText numberET;
    private List<String> destination;
    private String item;
    private Button pay,show;
    private TextView priceTV;
    private BillObject bo;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.pay_bill_layout,container,false);
        destination = new ArrayList<>();
        spinner = view.findViewById(R.id.bill_combobox_paybill);
        numberET = view .findViewById(R.id.bill_number_txt_paybill);
        pay = view.findViewById(R.id.pay_btn_paybill);
        priceTV = view.findViewById(R.id.bill_price_txt_paybill);
        show = view.findViewById(R)
        CreateSpinner();
        if(!item.equals("") && !numberET.equals("") &&  !priceTV.equals("")) {
            pay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    PayObject p = new PayObject(bo.getId(), bo.getPrice(), item);
                    HomePage.payList.add(p);
                    GoToPaymentPortal();
                }
            });
        }


        return view;
    }
    private void GoToPaymentPortal(){
        Intent intent = new Intent(this.getActivity(),PaymentPortal.class);
        startActivity(intent);
    }

    private void CreateSpinner(){
        destination.add("Gas bill");
        destination.add("Electric bill");
        destination.add("Phone bill");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(),R.layout.support_simple_spinner_dropdown_item,destination);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        item = parent.getItemAtPosition(position).toString();
    }
    private void SelectDataFromDatabase(){
        DatabaseAccess db = new DatabaseAccess(this.getActivity());
        String sql = "Select * from Bills";
        Cursor c = db.getDb().rawQuery(sql,null);
        c.moveToFirst();
        if(!item.equals("")) {
            while (!c.isAfterLast()) {
                String title = c.getString(3);
                int id = c.getInt(2);
                int price = c.getInt(0);
                String number = c.getString(1);
                if (numberET.getText().toString().equals(number) && item.equals(title)){
                     bo = new BillObject(price,number,id,title);
                }
                c.moveToNext();
            }
        }else{
            Pay p = new Pay();
            p.Alert("select categories","Error",this.getActivity());
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
