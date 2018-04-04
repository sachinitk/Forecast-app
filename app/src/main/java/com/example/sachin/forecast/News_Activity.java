package com.example.sachin.forecast;

import android.app.ProgressDialog;
import android.content.ClipData;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class News_Activity extends AppCompatActivity implements NewsAdapter.ClickListener {
    List<News> newsList;
    RecyclerView recyclerView;
    NewsAdapter adapter;
    private Toolbar toolbar;
    private static final String URL_NEWS = "https://newsapi.org/v2/top-headlines?country=IN&apiKey=bad39db9bba64abcae6dde54decd27ee";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle_view);
        toolbar = (Toolbar)findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        newsList = new ArrayList<>();


        loadNews();
    }


    private void loadNews() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading....poor internet ");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                URL_NEWS, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();

                try {

                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray array = jsonObject.getJSONArray("articles");

                    for (int i = 0; i < array.length(); i++) {
                        JSONObject news = array.getJSONObject(i);
                        //Toast.makeText(News_Activity.this, news.getString("title"), Toast.LENGTH_LONG).show();
                        newsList.add(new News(
                                news.getString("title"),
                                news.getString("description"),
                                news.getString("urlToImage"),
                                news.getString("url")
                        ));


                    }
                    adapter  = new NewsAdapter(News_Activity.this,newsList);
                    recyclerView.setAdapter(adapter);
                } catch (org.json.JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(News_Activity.this,e.toString(),Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                    Toast.makeText(News_Activity.this,"ERRROR:"+error.toString(),Toast.LENGTH_LONG).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_new, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.weatherbutton){
            startActivity(new Intent(News_Activity.this,WeatherActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    @Override
    public void itemClicked(View view, int position) {
        if(position ==1)
        {
            Toast.makeText(getApplicationContext(),"avjv",Toast.LENGTH_SHORT).show();
        }

    }

    }