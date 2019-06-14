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
import com.android.up.Login;
import com.android.up.R;
import com.android.up.model.News;
import com.android.up.model.Rating;
import com.android.up.model.Sort;
import java.util.ArrayList;
import java.util.List;
import Jama.Matrix;
import Jama.SingularValueDecomposition;

public class SVDMain extends Fragment {

    private List<Rating> user;
    public static List<News> news;
    public static List<Rating> rating;
    public static List<News> new_news;
    RecyclerView recyclerView;
    SVDAdaptor adaptor;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.svd_main_layout,container,false);
        SelectFromDatabase();
        SelectRatingFromDatabase();
        boolean check = UserExits();

        if(check){
        InsertToMatrix();}

        recyclerView = view.findViewById(R.id.recycle_view_news);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        adaptor = new SVDAdaptor(this.getActivity());
        recyclerView.setAdapter(adaptor);
        return view;
    }

    private boolean UserExits(){
        boolean check = false;
        for (int i = 0; i < rating.size(); i++) {
            if (Login.u_info.get(Login.list_number).getUsername().equals(rating.get(i).getUsername())){
                check = true;
            }
        }
        return check;
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
    private void SelectRatingFromDatabase(){
        rating = new ArrayList<>();
        DatabaseAccess db = new DatabaseAccess(this.getActivity());
        Cursor c = db.getDb().rawQuery("Select * from rating order by username ASC",null);
        c.moveToFirst();
        while (!c.isAfterLast())
        {
            String username = c.getString(0);
            int id = c.getInt(1);
            float rating_ = c.getFloat(2);

            Rating r = new Rating(username,id,rating_);
            rating.add(r);
            c.moveToNext();
        }
    }

    private int MaxNumberLIst(){
        int max =0;
        for (int i = 0; i < news.size(); i++) {
            if(news.get(i).getId()>max){
                max=news.get(i).getId();
            }
        }
        return max;
    }
    private List Sort(List<Rating> rating){
        for (int i = 0; i < rating.size(); i++) {
            for (int j = 0; j < MaxNumberLIst(); j++) {
                for (int k = 0; k < rating.size(); k++){
                    if (rating.get(j).getRating() > rating.get(j+1).getId()){
                        Rating temp = rating.get(j);
                        rating.set(j,rating.get(j+1));
                        rating.set(j+1,temp);
                    }
                }
            }
        }
        return rating;
    }


    private void InsertToMatrix(){
        List<Rating> username = new ArrayList<>();
        List<Rating> sort = new ArrayList<>();
        sort = Sort(rating);
        int counter=0;
        double[][] rating_ = new double[Login.u_info.size()][news.size()];
        for (int i = 0; i < Login.u_info.size(); i++) {
            for (int j = 0; j < MaxNumberLIst(); j++) {
                rating_[i][j] = sort.get(counter).getRating();
                counter++;
            }
        }

        double[] matrixRowSum =  SearchingCloseRang(rating_,news.size(),Login.u_info.size(),sort);
        List<Integer> distance = new ArrayList<>();
        for (int i = 0; i < matrixRowSum.length; i++) {
        if(matrixRowSum[i]<Math.sqrt(30) && matrixRowSum[i]!=0){
                distance.add(i);
            }
        }
        if (distance.size()==0){
            return;
        }else {

            double[][] finaluser = new double[distance.size()][news.size()];
            for (int i = 0; i < distance.size(); i++) {
                for (int j = 0; j < news.size(); j++) {
                    finaluser[i][j] = rating_[distance.get(i)][j];
                }
            }

            double[][] user_i = new double[news.size()][1];
            for (int i = 0; i < news.size(); i++) {
                for (int j = 0; j < 1; j++) {
                    user_i[i][j] = user.get(i).getRating();
                }
            }
            System.out.println();
            Matrix User = new Matrix(user_i);
            Matrix A = new Matrix(finaluser);
            SingularValueDecomposition svd = A.svd();
            Matrix U = svd.getV();
            Matrix UT = U.transpose();
            Matrix Mul = UT.times(U);
            System.out.println(Mul);
            Matrix result = Mul.times(User);
            Sorting(result.transpose());
        }
    }
    private void Sorting(Matrix result){
        double[][] matrix =result.getArrayCopy();
        List<Sort> sorting = new ArrayList<>();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                double temp = matrix[i][j];
                Sort sort = new Sort(j+1,temp);
                sorting.add(sort);
            }
        }
        for (int i = 0; i < sorting.size(); i++) {
            for (int j = 0; j < sorting.size()-i-1; j++) {
                if(sorting.get(j).getValue()<sorting.get(j+1).getValue()){
                    Sort temp = sorting.get(j);
                    sorting.set(j,sorting.get(j+1));
                    sorting.set(j+1,temp);
                }
            }
        }
        //Hakim MMD
        List<News> news__ = new ArrayList<>();
        for (int i = 0; i <news.size() ; i++) {
            news__.add(news.get(i));
        }

        for (int i = 0; i < sorting.size(); i++) {
            for (int j = 0; j < news.size(); j++) {
                if(sorting.get(i).getId() == news__.get(j).getId()){
                    news.set(i,news__.get(j));
                }
            }
        }
    }



    private void FindUserI(List<Rating> list,String username){
        user = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if(list.get(i).getUsername().equals(username)){
                user.add(list.get(i));
            }
        }
    }


    private double[] SearchingCloseRang(double a[][],int newsSize,int loginSize,List<Rating> sort)
    {
        double[][] b =new double[loginSize][newsSize];
        double[] matrixRowSum = new double[loginSize];

        String username_ = Login.u_info.get(Login.list_number).getUsername();
        FindUserI(sort,username_);
        for (int i = 0; i < loginSize; i++) {
            for (int j = 0; j < newsSize; j++) {
                b[i][j] = Math.pow(user.get(j).getRating()-a[i][j],2);
            }
            for (int j = 0; j <newsSize ; j++) {
                matrixRowSum[i] = matrixRowSum[i]+ b[i][j];
            }
            matrixRowSum[i] = Math.sqrt(matrixRowSum[i]);
        }
        return matrixRowSum;
    }
}
