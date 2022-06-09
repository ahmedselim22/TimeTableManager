package com.example.timetablemanager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.timetablemanager.Databases.DB_user;
import com.example.timetablemanager.Databases.User;

import java.util.ArrayList;

public class login extends AppCompatActivity {

    DB_user obj = new DB_user(this);
    EditText Usre12,Pass12;
    Button loginbtn,signupbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginbtn=findViewById(R.id.LogIN);
        signupbtn=findViewById(R.id.Sign_up);
        Usre12= findViewById(R.id.usernameLogin);
        Pass12= findViewById(R.id.passwordLogin);

//        Toast.makeText(login.this, obj.DeleteAll(), Toast.LENGTH_SHORT).show();
        ArrayList<User> arr = obj.GetData();
//        for(User x : arr){
//            Log.i("tag", x.getId()+ " "+x.getName() + " "+x.getUsername() + " "+x.getPassword() + " "+x.getEmail());
//        }

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Check1 = Usre12.getText().toString();
                String Check2 = Pass12.getText().toString();
                if(Check1.equals("") || Check2.equals("")){
                    Toast.makeText(login.this, "Please Fill all fields", Toast.LENGTH_SHORT).show();
                }else {

                    String ret = obj.Search(Usre12.getText().toString(), Pass12.getText().toString());
                    if (ret == "Found") {
                        Intent II = new Intent(login.this, MainActivity.class);
                        startActivity(II);
                    } else {
                        Toast.makeText(login.this, ret, Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(login.this , SignUp.class);
                startActivityForResult(in,200);
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode ==200 && resultCode==RESULT_OK){
            String h1 = data.getExtras().getString("name");
            String h2 = data.getExtras().getString("username");
            String h3 = data.getExtras().getString("email");
            String h4 = data.getExtras().getString("password");

            String msg = obj.insertData(h1,h2,h3,h4);
            Toast.makeText(login.this,msg,Toast.LENGTH_SHORT).show();
            ArrayList<User> li = obj.GetData();

            Usre12.setText(h2);
            Pass12.setText(h4);
        }
    }
}