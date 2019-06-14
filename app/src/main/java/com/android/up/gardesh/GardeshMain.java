package com.android.up.gardesh;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.android.up.DatabaseAccess;
import com.android.up.Login;
import com.android.up.R;
import com.android.up.model.Order;

import java.util.ArrayList;
import java.util.List;

public class GardeshMain extends Fragment {

    public static List<Order> order;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.gardesh_main_layout,container,false);
        order = new ArrayList<>();
        SelectFromDatabase();
        ListView l = view.findViewById(R.id.list_gardesh);
        GardeshAdaptor ga = new GardeshAdaptor(this.getActivity());
        l.setAdapter(ga);
        return view;
    }

    private void SelectFromDatabase() {
        DatabaseAccess db = new DatabaseAccess(this.getActivity());
        Cursor c = db.getDb().rawQuery("Select * from orders where username = '"+ Login.u_info.get(Login.list_number).getUsername() +"'",null);
        c.moveToFirst();
        while (!c.isAfterLast()){
             int price = c.getInt(0);
             int id =c.getInt(2);
             String username = c.getString(3);
             String firstname =c.getString(4);
             String lastname = c.getString(5);
             String title = c.getString(1);
             Order o = new Order(price,id,username,firstname,lastname,title);
            order.add(o);
            c.moveToNext();
        }
    }
}
