package com.android.up;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.android.up.model.PayObject;

public class Charity extends Fragment {

    private Button pay;
    private EditText amount;
    private Spinner spiner;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.nikokari_layout,container,false);
        amount = view.findViewById(R.id.amount_txt_nikokari);
        pay = view.findViewById(R.id.submit_btn_nikokari);
        spiner = view.findViewById(R.id.)
        int zero = 0;
        Context context = this.getActivity();
        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!amount.getText().toString().equals("")) {
                    PayObject po = new PayObject(zero, Integer.parseInt(amount.getText().toString()), "Charity");
                    HomePage.payList.add(po);
                    GoToPaymentPortal();
                }else {
                    Pay p = new Pay();
                    p.Alert("Write amount","Error",context);
                }
            }
        });
        return view;
    }
    private void CreateSpinner(){
        destination.add("Gas bill");
        destination.add("Electric bill");
        destination.add("Phone bill");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(),R.layout.support_simple_spinner_dropdown_item,destination);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    private void GoToPaymentPortal(){
        Intent intent = new Intent(this.getActivity(),PaymentPortal.class);
        startActivity(intent);
    }
}
