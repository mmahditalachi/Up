package com.android.up;

import android.content.Intent;
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
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import com.android.up.model.PayObject;

import java.util.ArrayList;
import java.util.List;

public class Tiket extends Fragment  {
    private static List<String> destination;
    private int number;
    private int seekbarValue;
    private String item;
    private Spinner spinner;
    private TextView price;
    private Button pay,show_price;
    private SeekBar seekbar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.ticket_layout,container,false);
        destination = new ArrayList<>();
        spinner = view.findViewById(R.id.ticket_spinner);
        pay = view.findViewById(R.id.buy_btn_ticket);
        show_price = view.findViewById(R.id.show_price_btn_ticket);
        price = view.findViewById(R.id.price_txt_ticket);
        seekbar = view.findViewById(R.id.seekbar_ticket);
        CreateSpinner();
        Seekbar();
        show_price.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowPrice();
            }
        });
        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!price.getText().toString().equals("")){
                    PayObject po = new PayObject(10,number,"Ticket" + " " +spinner.getSelectedItem().toString());
                    HomePage.payList.add(po);
                    GoToPaymentpORTAL();
                }
            }
        });
        return view;
    }

    private void GoToPaymentpORTAL(){
        Intent intent = new Intent(this.getActivity(),PaymentPortal.class);
        startActivity(intent);
    }

    private void CreateSpinner(){
        destination.add("LA-Tehran");
        destination.add("LA-NY");
        destination.add("LA-Esfehan");
        destination.add("London-Shiraz");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(),R.layout.support_simple_spinner_dropdown_item,destination);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    private void ShowPrice(){
        System.out.println(2+2);
        if (seekbarValue!=0 && "LA-Tehran".equals(spinner.getSelectedItem().toString())){
            number = seekbarValue*2000;
            price.setText(String.valueOf(number));
        }
        else if(seekbarValue!=0 && spinner.getSelectedItem().toString().equals("LA-NY")){
            number = seekbarValue*400;
            price.setText(String.valueOf(number));
        }
        else if(seekbarValue!=0 && spinner.getSelectedItem().toString().equals("LA-Esfehan")){
            number = seekbarValue*1500;
            price.setText(String.valueOf(number));
        }
        else if(seekbarValue!=0 && spinner.getSelectedItem().toString().equals("London-Shiraz")){
            number = seekbarValue*1700;
            price.setText(String.valueOf(number));
        }
        else {
            Pay p = new Pay();
            p.Alert("tiket number must more than 1","Error",this.getActivity());
        }
    }

    private void Seekbar(){
        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                seekbarValue = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }



}
