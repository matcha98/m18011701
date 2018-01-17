package com.matcha.m18011701;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.matcha.m18011701.data.Student;

public class ShowActivity extends AppCompatActivity {

    TextView tv1,tv2,tv3;
    int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        tv1=findViewById(R.id.textView);
        tv2=findViewById(R.id.textView2);
        tv3=findViewById(R.id.textView3);
        Intent it=getIntent();
        id=it.getIntExtra("id",0);
        Student s=MainActivity.dao.getStudent(id);
        tv1.setText(String.valueOf(s.id));
        tv2.setText(s.name);
        tv3.setText(String.valueOf(s.score));
    }

    public void clickBack(View v)
    {
        finish();
    }
    public void clickDel(View v)
    {
        MainActivity.dao.delete(id);
        finish();
    }
    public void clickEdit(View v)
    {
        Intent it=new Intent(ShowActivity.this,EditActivity.class);
        it.putExtra("id",id);
        startActivity(it);
    }
}
