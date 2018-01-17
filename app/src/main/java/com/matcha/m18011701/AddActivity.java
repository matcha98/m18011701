package com.matcha.m18011701;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.matcha.m18011701.data.Student;

public class AddActivity extends AppCompatActivity {

    EditText ed1,ed2,ed3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        ed1=findViewById(R.id.editText);
        ed2=findViewById(R.id.editText2);
        ed3=findViewById(R.id.editText3);
    }

    public void clickAdd(View v)
    {
        int id=Integer.valueOf(ed1.getText().toString());
        String name=ed2.getText().toString();
        int score=Integer.valueOf(ed3.getText().toString());
        MainActivity.dao.add(new Student(id,name,score));
        finish();
    }
}
