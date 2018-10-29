package com.example.mnoer.cardview;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.load.engine.Resource;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements ExampleAdapter.OnItemClickListener{
    public static final String EXTRA_URL = "imageUrl";
    public static final String EXTRA_Title = "title";
    public static final String EXTRA_Overview = "overview";

    private RecyclerView mRecyclerView;
    private ExampleAdapter mExampleAdapter;
    private ArrayList<ExampleItem> mExampleList;
    private RequestQueue mRequestQueue;

    String Language;
    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mExampleList = new ArrayList<>();

        mRequestQueue = Volley.newRequestQueue(this);
        parseJSON();
    }

    private void parseJSON() {

        Language = Locale.getDefault().getDisplayLanguage().toString();
        Toast.makeText(this,Language, Toast.LENGTH_SHORT).show();
        url = "https://api.themoviedb.org/3/movie/now_playing?api_key=609e7b6ae6f36e4074d83e9efbfdd760";

        if (Language == "English") {
//            url = "https://api.themoviedb.org/3/movie/now_playing?api_key=609e7b6ae6f36e4074d83e9efbfdd760";
            Toast.makeText(this,"Emgos", Toast.LENGTH_SHORT).show();
        } else if (Language == "Indonesia") {
            url = "https://api.themoviedb.org/3/movie/now_playing?api_key=609e7b6ae6f36e4074d83e9efbfdd760&language=id";
        }


        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("results");

                    for (int i= 0; i < jsonArray.length(); i++){
                        JSONObject hit = jsonArray.getJSONObject(i);

                        String id = hit.getString("id");
                        String title = hit.getString("title");
                        String overview = hit.getString("overview");
                        String imagePath = hit.getString("poster_path");
                        String imageUrl = "http://image.tmdb.org/t/p/w185" + imagePath;

                        mExampleList.add(new ExampleItem(imageUrl, title, overview ));
                    }

                    mExampleAdapter = new ExampleAdapter(MainActivity.this, mExampleList);
                    mRecyclerView.setAdapter(mExampleAdapter);
                    mExampleAdapter.setOnItemClickListener(MainActivity.this);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        mRequestQueue.add(request);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Context context;
        Resources resources;
        if (item.getItemId() == R.id.language) {
            Toast.makeText(this,"English", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(Settings.ACTION_LOCALE_SETTINGS);

            startActivity(intent);
        } else {
            return super.onOptionsItemSelected(item);
        }

        return true;
    }


    @Override
    public void OnIntemClick(int position) {
        Intent detailIntent = new Intent(this, DetailActivity.class);
        ExampleItem clickedItem = mExampleList.get(position);

        detailIntent.putExtra(EXTRA_URL, clickedItem.getmImageUrl());
        detailIntent.putExtra(EXTRA_Title, clickedItem.getmTitle());
        detailIntent.putExtra(EXTRA_Overview, clickedItem.getmOverview());

        startActivity(detailIntent);
    }
}
