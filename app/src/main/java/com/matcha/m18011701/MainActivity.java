package com.matcha.m18011701;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.matcha.m18011701.data.DBType;
import com.matcha.m18011701.data.Student;
import com.matcha.m18011701.data.StudentDAO;
import com.matcha.m18011701.data.StudentDAOFactory;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    static StudentDAO dao;
    DBType dbType;
    ListView lv;
    ArrayList<String> studentName;
    ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv=findViewById(R.id.listView);
        dbType=DBType.CLOULD;
        dao= StudentDAOFactory.getInstance(MainActivity.this,dbType);
        studentName=new ArrayList<String>();
        adapter=new ArrayAdapter<String>(
                MainActivity.this,android.R.layout.simple_list_item_1,studentName);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent it=new Intent(MainActivity.this,ShowActivity.class);
                it.putExtra("id",dao.getList().get(i).id);
                startActivity(it);
            }
        });
        lv.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        refreshData();
    }

    public void refreshData()
    {
        studentName.clear();
        for(Student s:dao.getList())
        {
            studentName.add(s.name);
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
       getMenuInflater().inflate(R.menu.mymenu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.menu_add)
        {
            Intent it=new Intent(MainActivity.this,AddActivity.class);
            startActivity(it);
        }
        return super.onOptionsItemSelected(item);
    }
}
