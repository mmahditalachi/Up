package com.android.up.house;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.up.DatabaseAccess;
import com.android.up.R;
import com.android.up.model.House;

import java.util.ArrayList;
import java.util.List;

public class HouseMain extends Fragment {

    RecyclerView recyclerView;
    public static List<House> house;
    HouseAdaptor adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.house_layout,container,false);
        SelectFromDatabase();
        recyclerView = (RecyclerView) view.findViewById(R.id.recycle_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        adapter = new HouseAdaptor(this.getActivity());
        recyclerView.setAdapter(adapter);
        return view;
    }
    private void SelectFromDatabase()
    {
        house = new ArrayList<>();
        DatabaseAccess db = new DatabaseAccess(this.getActivity());
        Cursor c = db.getDb().rawQuery("Select * from house",null);
        c.moveToFirst();
        while (!c.isAfterLast())
        {
            String title = c.getString(0);
            String details = c.getString(1);
            String image = c.getString(4);
            int price = c.getInt(2);
            int id = c.getInt(3);

            House h = new House(title,details,price,id,image);
            house.add(h);
            c.moveToNext();
        }
    }
}
