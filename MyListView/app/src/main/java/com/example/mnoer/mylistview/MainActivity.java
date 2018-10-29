package com.example.mnoer.mylistview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Button btnList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnList=(Button)findViewById(R.id.btnList);
        btnList.setOnClickListener(this);
    }
    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.btnList:
                Intent moveIntent = new Intent(MainActivity.this, myListActivity.class);
                startActivity(moveIntent);
                break;
        }
    }
}
