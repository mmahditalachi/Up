package com.android.up;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class Information extends Fragment {

    private TextView first_name,last_name,email,number_phone,home_number,address,username,card_num,inventory;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.information_layout,container,false);

        first_name = view.findViewById(R.id.first_name_information);
        last_name = view.findViewById(R.id.last_name_information);
        email = view.findViewById(R.id.email_information);
        number_phone = view.findViewById(R.id.phone_information);
        home_number = view.findViewById(R.id.home_phone_information);
        address = view.findViewById(R.id.address_information);
        username =view.findViewById(R.id.username_information);
        card_num = view.findViewById(R.id.card_number_information);
        inventory = view.findViewById(R.id.inventory_information);
        FillingTheBlack();

        return view;
    }

    private void FillingTheBlack()
    {
        first_name.setText( Login.u_info.get(Login.list_number).getFirst_name());
        last_name.setText( Login.u_info.get(Login.list_number).getLast_name());
        email.setText( Login.u_info.get(Login.list_number).getEmail());
        number_phone.setText( Login.u_info.get(Login.list_number).getPhonenum());
        home_number.setText(Login.u_info.get(Login.list_number).getNumber());
        address.setText( Login.u_info.get(Login.list_number).getAddress());
        username.setText( Login.u_info.get(Login.list_number).getUsername());
        card_num.setText(Login.u_info.get(Login.list_number).getCardnum());
        inventory.setText(String.valueOf(Login.u_info.get(Login.list_number).getCardInventory()));
    }
}
