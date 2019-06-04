package com.android.up.SVD;

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
import com.android.up.model.News;

import java.util.ArrayList;
import java.util.List;

import Jama.SingularValueDecomposition;

public class SVDMain extends Fragment {

    public static List<News> news;
    RecyclerView recyclerView;
    SVDAdaptor adaptor;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.svd_main_layout,container,false);
        SelectFromDatabase();
        recyclerView = view.findViewById(R.id.recycle_view_news);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        adaptor = new SVDAdaptor(this.getActivity());
        recyclerView.setAdapter(adaptor);
        return view;
    }


    public void SelectFromDatabase()
    {
        news = new ArrayList<>();
        DatabaseAccess db = new DatabaseAccess(this.getActivity());
        Cursor c = db.getDb().rawQuery("Select * from news",null);
        c.moveToFirst();
        while (!c.isAfterLast())
        {
            String title = c.getString(0);
            String image = c.getString(1);
            String details = c.getString(2);
            int id = c.getInt(3);

            News n = new News(details,title,image,id);
            news.add(n);
            c.moveToNext();
        }
    }
}
