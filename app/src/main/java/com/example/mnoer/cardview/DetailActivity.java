package com.example.mnoer.cardview;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import static android.content.Intent.EXTRA_TITLE;
import static com.example.mnoer.cardview.MainActivity.EXTRA_Overview;
import static com.example.mnoer.cardview.MainActivity.EXTRA_Title;
import static com.example.mnoer.cardview.MainActivity.EXTRA_URL;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        String imageUrl = intent.getStringExtra(EXTRA_URL);
        String title = intent.getStringExtra(EXTRA_Title);
        String overview = intent.getStringExtra(EXTRA_Overview);

        ImageView imageView = findViewById(R.id.image_view_detail);
        TextView textViewTitle = findViewById(R.id.tv_title_detail);
        TextView textViewOverview = findViewById(R.id.tv_overview_detail);

        Picasso.with(this).load(imageUrl).fit().centerCrop().into(imageView);
        textViewTitle.setText(title);
        textViewOverview.setText(overview);

    }
}
