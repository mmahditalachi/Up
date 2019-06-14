package com.android.up.concert;

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
import com.android.up.house.HouseAdaptor;
import com.android.up.model.ConcertObject;

import java.util.ArrayList;
import java.util.List;

public class ConcertMain extends Fragment {
    public static List<ConcertObject> concert;
    RecyclerView recyclerView;
    ConcertAdaptor adapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.concert_layout,container,false);
        concert = new ArrayList<>();
        SelectDataFromDatabase();
        System.out.println(0);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycle_view_concert);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        adapter = new ConcertAdaptor(this.getActivity());
        recyclerView.setAdapter(adapter);
        return view;
    }

    private void SelectDataFromDatabase() {
        DatabaseAccess db = new DatabaseAccess(this.getActivity());
        Cursor c = db.getDb().rawQuery("Select * from concert",null);
        c.moveToFirst();
        while (!c.isAfterLast()){
             String title = c.getString(0);
             String details = c.getString(1);
             String image = c.getString(2);
             int price =c.getInt(3);
             int id = c.getInt(4);
             ConcertObject co = new ConcertObject(title,details,image,price,id);
             concert.add(co);
            c.moveToNext();
        }
    }
}
