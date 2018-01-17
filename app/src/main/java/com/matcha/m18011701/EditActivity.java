package com.matcha.m18011701;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.matcha.m18011701.data.Student;

public class EditActivity extends AppCompatActivity {

    int id;
    TextView tv7;
    EditText ed4,ed5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        Intent it=getIntent();
        id=it.getIntExtra("id",0);
        tv7=findViewById(R.id.textView7);
        ed4=findViewById(R.id.editText4);
        ed5=findViewById(R.id.editText5);
        Student s=MainActivity.dao.getStudent(id);
        tv7.setText(String.valueOf(s.id));
        ed4.setText(s.name);
        ed5.setText(String.valueOf(s.score));
    }

    public void clickBack(View v)
    {
        finish();
    }
    public void clickOk(View v)
    {
            String name=ed4.getText().toString();
            int score=Integer.valueOf(ed5.getText().toString());
            MainActivity.dao.update(new Student(id,name,score));
            Intent it=new Intent(EditActivity.this,MainActivity.class);
            startActivity(it);
    }
}
