package com.example.timetablemanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class Add_task extends AppCompatActivity implements AdapterView.OnItemSelectedListener {


    EditText title, description;
    Button addTask;
    Spinner spinner;
    String Value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        title = findViewById(R.id.title);
        description = findViewById(R.id.description);
        addTask = findViewById(R.id.addTask);
        spinner=findViewById(R.id.spin);

        spinner.setOnItemSelectedListener(this);
        String[] Days ={"SUN","MON","TUE","WED","THU","FRI","SAT"};
        ArrayAdapter adapter2 = new ArrayAdapter(this, R.layout.spinner_item,Days);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter2);



        addTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                    if (!TextUtils.isEmpty(title.getText().toString()) && !TextUtils.isEmpty(description.getText().toString())) {
                        DatabaseClass db = new DatabaseClass(Add_task.this);
                        db.addTasks(title.getText().toString(), description.getText().toString() ,Value);

                        Intent intent = new Intent(Add_task.this,MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        finish();

                    } else {
                        Toast.makeText(Add_task.this, "Both Fields Required", Toast.LENGTH_SHORT).show();
                    }
            }
        });

    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        if(adapterView.getId()== R.id.spin){
            Value = adapterView.getItemAtPosition(i).toString();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}