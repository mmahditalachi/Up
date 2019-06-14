package com.android.up;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.android.up.SVD.SVDMain;
import com.android.up.gardesh.GardeshMain;
import com.android.up.house.HouseMain;

public class Home extends Fragment implements View.OnClickListener {
    private Button news,mobilecharge,payBill,CardToCard,Wallet,housedeal,charity,gardesh;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_layout,container,false);
        news = view.findViewById(R.id.news_btn_main);
        gardesh = view.findViewById(R.id.account_activities_btn_main);
        mobilecharge = view.findViewById(R.id.chargemobile_btn_main);
        payBill = view.findViewById(R.id.paybill_btn_main);
        CardToCard = view.findViewById(R.id.carttocart_btn_main);
        Wallet = view.findViewById(R.id.credit_btn_main);
        housedeal =view.findViewById(R.id.buy_property_btn_main);
        charity = view.findViewById(R.id.charitable_btn_main);
        charity.setOnClickListener(this);
        news.setOnClickListener(this);
        gardesh.setOnClickListener(this);
        housedeal.setOnClickListener(this);
        mobilecharge.setOnClickListener(this);
        payBill.setOnClickListener(this);
        CardToCard.setOnClickListener(this);
        Wallet.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.news_btn_main:
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new SVDMain()).commit();
                break;
            case R.id.chargemobile_btn_main:
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new MobileCharge()).commit();
                break;
            case R.id.paybill_btn_main:
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new Bill()).commit();
                break;
            case R.id.carttocart_btn_main:
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new CartToCart()).commit();
                break;
            case R.id.credit_btn_main:
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new Inventory()).commit();
                break;
            case R.id.buy_property_btn_main:
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new HouseMain()).commit();
                break;
            case R.id.charitable_btn_main:
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new Charity()).commit();
                break;
            case R.id.account_activities_btn_main:
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new GardeshMain()).commit();
                break;
        }
    }
}
