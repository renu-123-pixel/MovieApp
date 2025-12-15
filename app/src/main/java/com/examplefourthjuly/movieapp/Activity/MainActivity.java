package com.examplefourthjuly.movieapp.Activity;


import android.view.View;
import android.os.Bundle;
import android.widget.ProgressBar;

import com.examplefourthjuly.movieapp.Domain.DetailFilm;
import com.examplefourthjuly.movieapp.R;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.examplefourthjuly.movieapp.Adapter.FilmListAdapter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerViewNewMovies, recyclerViewUpComing;
    private FilmListAdapter adapterNewMovies, adapterUpComing;
    private ProgressBar loading1, loading2;
    private RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        requestQueue = Volley.newRequestQueue(this);
        initView();
        sendRequest1();
        sendRequest2();
    }

    private void initView() {
        recyclerViewNewMovies = findViewById(R.id.view1);
        recyclerViewUpComing = findViewById(R.id.view2);

        recyclerViewNewMovies.setLayoutManager(
                new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        );

        recyclerViewUpComing.setLayoutManager(
                new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        );

        loading1 = findViewById(R.id.loading1);
        loading2 = findViewById(R.id.loading2);
    }

    private void sendRequest1() {
        loading1.setVisibility(View.VISIBLE);

        StringRequest request = new StringRequest(
                Request.Method.GET,
                "https://api.tvmaze.com/search/shows?q=all",
                response -> {
                    loading1.setVisibility(View.GONE);

                    Type type = new TypeToken<List<DetailFilm>>() {}.getType();
                    List<DetailFilm> list = new Gson().fromJson(response, type);

                    adapterNewMovies = new FilmListAdapter(list);
                    recyclerViewNewMovies.setAdapter(adapterNewMovies);
                },
                error -> loading1.setVisibility(View.GONE)
        );

        requestQueue.add(request);
    }

    private void sendRequest2() {
        loading2.setVisibility(View.VISIBLE);

        StringRequest request = new StringRequest(
                Request.Method.GET,
                "https://api.tvmaze.com/search/shows?q=all",
                response -> {
                    loading2.setVisibility(View.GONE);

                    Type type = new TypeToken<List<DetailFilm>>() {}.getType();
                    List<DetailFilm> list = new Gson().fromJson(response, type);

                    adapterUpComing = new FilmListAdapter(list);
                    recyclerViewUpComing.setAdapter(adapterUpComing);
                },
                error -> loading2.setVisibility(View.GONE)
        );

        requestQueue.add(request);
    }
}
