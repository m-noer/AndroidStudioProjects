package com.example.mnoer.mylistview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class myListActivity extends AppCompatActivity {
    private ListView lvItem;
    private String[] bahasapemrograman = new String[]{
            "ASP.NET","C++","C#" ,"BASIC", "SQL",
            "PHP", "Phyton","Javascript","Java",
            "Visual Basic"};
    private String[] level = new String[]{
            "1","2","3" ,"4", "5",
            "6", "7","8","9",
            "10"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_list);

        getSupportActionBar().setTitle("MyListView");

        lvItem = (ListView) findViewById(R.id.list_view);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(myListActivity.this, android.R.layout.simple_list_item_1, android.R.id.text1, bahasapemrograman);

        lvItem.setAdapter(adapter);

        lvItem.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(myListActivity.this, "Memilih : "+bahasapemrograman[position], Toast.LENGTH_LONG).show();

            }
        });
    }
}
