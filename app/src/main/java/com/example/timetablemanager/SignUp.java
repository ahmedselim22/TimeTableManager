package com.example.timetablemanager;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class SignUp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        EditText E1 , E2, E3, E4;
        E1 = findViewById(R.id.name);
        E2 = findViewById(R.id.username);
        E3 = findViewById(R.id.password);
        E4 = findViewById(R.id.email);

        findViewById(R.id.Create).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent up  = new Intent();
                up.putExtra("name" , E1.getText().toString());
                up.putExtra("username" , E2.getText().toString());
                up.putExtra("password" , E3.getText().toString());
                up.putExtra("email" , E4.getText().toString());
                setResult(Activity.RESULT_OK,up);
                finish();

            }
        });
    }
}

