package com.android.up;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.up.model.Manager;
import com.android.up.model.Users;
import java.util.ArrayList;
import java.util.List;

public class Login extends Activity {

    public static int list_number;
    private TextView signUp;
    private final static String TAG = "LoginPage";
    static String username_database,pass_database;
    public static List<Users> u_info;
    public static List<Manager> m_info;
    public static boolean u_username_correct=false;
    public static boolean u_pass_correct=false;
    static boolean m_username_correct=false;
    static boolean m_pass_correct=false;
    private Button login;
    private EditText username,pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        SelectManagerData();
        LoginBtn();
        SignUp();
    }

    public void SelectManagerData()
    {
        Log.d(TAG,"Reading from database");
        DatabaseAccess dbAccess = new DatabaseAccess(this);
        m_info = new ArrayList<>();

        Cursor c = dbAccess.getDb().rawQuery("SELECT * FROM managers", null);
        c.moveToFirst();
        while (!c.isAfterLast()) {

            String username = c.getString(0);
            String pass = c.getString(1);
            String firstname = c.getString(2);
            String lastname = c.getString(3);

            Manager m = new Manager(username,firstname,lastname,pass);
            m_info.add(m);
            c.moveToNext();
        }
    }
    public void SelectUsersData(Context context)
    {

        u_info = new ArrayList<>();
        DatabaseAccess dbAccess = new DatabaseAccess(context);

        Cursor c = dbAccess.getDb().rawQuery("SELECT * FROM users", null);
        c.moveToFirst();
        while (!c.isAfterLast()) {

            int cardInventory = c.getInt(12);
            double address_latitude = c.getDouble(11);
            double address_Longitude = c.getDouble(10);
            String username = c.getString(9);
            String pass = c.getString(8);
            int id = c.getInt(7);
            String cardnum = c.getString(6);
            String address = c.getString(5);
            String phonenum = c.getString(4);
            String number = c.getString(3);
            String email = c.getString(2);
            String firstname = c.getString(0);
            String lastname = c.getString(1);

            Users u = new Users(username,id,firstname,lastname,email,address,pass,number,phonenum,cardnum,address_Longitude,address_latitude,cardInventory);
            u_info.add(u);
            c.moveToNext();
        }
    }
    public void LoginBtn()
    {
        Button login = findViewById(R.id.login_btn);
        username = findViewById(R.id.email_login);
        pass = findViewById(R.id.password_login);


        login.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                SelectUsersData(Login.this);
                ManagerSearching();
                UsersSearching();

                if(u_username_correct && u_pass_correct) {
                    GoToHomePage();
                    u_pass_correct =false;
                    u_username_correct =false;
                }


                else if(m_username_correct && m_pass_correct) {
//                    GoToManagerPage();
                    m_pass_correct=false;
                    m_username_correct = false;
                }

                else
                    Alert();
            }
        });
    }
    public void ManagerSearching()
    {
        for (int i = 0; i < m_info.size(); i++) {
            if(username.getText().toString().equals(m_info.get(i).getUsername())){
                username_database = username.getText().toString();
                m_username_correct =true;
                list_number = i;
                break;
            }
            else
                m_username_correct =false;

        }
        for (int i = 0; i < m_info.size(); i++) {
            if(pass.getText().toString().equals(m_info.get(i).getPass()))
            {
                pass_database = pass.getText().toString();
                m_pass_correct =true;
                break;
            }
            else
                m_pass_correct =false;
        }
    }
    public void UsersSearching()
    {
        for (int i = 0; i < u_info.size(); i++) {
            if(username.getText().toString().equals(u_info.get(i).getUsername())){
                username_database = username.getText().toString();
                list_number = i;
                u_username_correct =true;
                break;
            }
        }
        for (int i = 0; i < u_info.size(); i++) {
            if(pass.getText().toString().equals(u_info.get(i).getPass()))
            {
                pass_database = pass.getText().toString();
                u_pass_correct =true;
                break;
            }
        }
    }
    public void Alert()
    {
        AlertDialog alertDialog1 = new AlertDialog.Builder(
                Login.this).create();

        alertDialog1.setTitle("Incorrect information");
        alertDialog1.setMessage("Sorry, your password or your username was incorrect.");
        alertDialog1.setButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
//                            Toast.makeText(getApplicationContext(),
//                                    "You clicked on OK", Toast.LENGTH_SHORT).show();
            }
        });
        alertDialog1.show();
    }
    public void GoToHomePage()
    {
        Intent intent = new Intent(this,HomePage.class);
        startActivity(intent);
    }

    public void SignUp()
    {
        signUp = findViewById(R.id.singup_tv);
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GoToSignUp();
            }
        });
    }
    public void GoToSignUp()
    {
        Intent intent = new Intent(this,SignUp.class);
        startActivity(intent);
    }

//    public void GoToManagerPage()
//    {
//        Intent intent = new Intent(this,AdminMainPage.class);
//        startActivity(intent);
//    }

}
