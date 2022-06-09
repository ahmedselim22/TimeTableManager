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

public class Update_Task extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    EditText title,description,day;
    Button updateTask;
    String id;
    Spinner spinner;
    String Value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_task);

        title=findViewById(R.id.title);
        description=findViewById(R.id.description);
        updateTask=findViewById(R.id.updateTask);
        spinner=findViewById(R.id.spinUPdate);


        Intent in = getIntent();
        title.setText(in.getExtras().getString("title"));
        description.setText(in.getExtras().getString("description"));
        Value = in.getExtras().getString("day");

        id = in.getExtras().getString("id");


        spinner.setOnItemSelectedListener(this);
        String[] Days ={"SUN","MON","TUE","WED","THU","FRI","SAT"};
        ArrayAdapter adapter2 = new ArrayAdapter(this,R.layout.spinner_item,Days);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter2);

        Toast.makeText(Update_Task.this,Value, Toast.LENGTH_SHORT).show();
        if(Value.equals(Days[0]))
            spinner.setSelection(0);
        else if(Value.equals(Days[1]))
            spinner.setSelection(1);
        else if(Value.equals(Days[2]))
            spinner.setSelection(2);
        else if(Value.equals(Days[3]))
            spinner.setSelection(3);
        else if(Value.equals(Days[4]))
            spinner.setSelection(4);
        else if(Value.equals(Days[5]))
            spinner.setSelection(5);
        else if(Value.equals(Days[6]))
            spinner.setSelection(6);

        updateTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!TextUtils.isEmpty(title.getText().toString()) && !TextUtils.isEmpty(description.getText().toString()))
                {

                    DatabaseClass db = new DatabaseClass(Update_Task.this);
                    db.updateTask(title.getText().toString(),description.getText().toString(),id,Value);


                    Intent i=new Intent(Update_Task.this,MainActivity.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(i);
                    finish();


                }
                else
                {
                    Toast.makeText(Update_Task.this, "Both Fields Required", Toast.LENGTH_SHORT).show();
                }


            }
        });

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        if(adapterView.getId()== R.id.spinUPdate){
            Value = adapterView.getItemAtPosition(i).toString();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}