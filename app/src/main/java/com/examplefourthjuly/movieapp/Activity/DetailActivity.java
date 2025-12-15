package com.examplefourthjuly.movieapp.Activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.examplefourthjuly.movieapp.Adapter.ImageListAdapter;
import com.examplefourthjuly.movieapp.Domain.Image;
import com.examplefourthjuly.movieapp.Domain.Show;
import com.examplefourthjuly.movieapp.R;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class DetailActivity extends AppCompatActivity {
    private RequestQueue mRequestQueue;
    private StringRequest mStringRequest;
    private ProgressBar progressBar;
    private TextView titleTxt,movieTypeTxt,movieTimeTxt,movieDateTxt,movieSummaryInfo,movieActorInfo;
    private NestedScrollView scrollView;
    private int idFilm;
    private ShapeableImageView pic1;
    private ImageView pic2,backImg;
    private RecyclerView.Adapter adapterImgList;
    private RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main),(v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);

            return insets;
        });
        idFilm=getIntent().getIntExtra("id",0);
        initView();
        mRequestQueue = Volley.newRequestQueue(this);
        sendRequest();
        fetchImageList();
    }

    private void fetchImageList() {
        String url = "https://api.tvmaze.com/shows/" + idFilm + "/images";
        StringRequest imageRequest = new StringRequest(Request.Method.GET, url,
                response -> {
                    Gson gson = new Gson();
                    Type listType = new TypeToken<List<ShowImage>>() {}.getType();
                    List<ShowImage> images = gson.fromJson(response, listType);

                    List<String> urls = new ArrayList<>();
                    for (ShowImage img : images) {
                        if (img.resolutions != null && img.resolutions.original != null) {
                            urls.add(img.resolutions.original.url);
                        }
                    }
                    ImageListAdapter adapterImgList = new ImageListAdapter(urls);
                    recyclerView.setAdapter(adapterImgList);
                },
                error -> Log.e("fetchImageList", "Error fetching images: " + error.toString())
        );
        mRequestQueue.add(imageRequest);
    }


    private void sendRequest() {
        mRequestQueue= Volley.newRequestQueue(this);
        progressBar.setVisibility(View.VISIBLE);
        scrollView.setVisibility(View.GONE);

        if (idFilm <= 0) {
            Log.e("DetailActivity", "Invalid film ID: " + idFilm);
            return;
        }

        mStringRequest=new StringRequest(Request.Method.GET,
                "https://api.tvmaze.com/shows/" + idFilm ,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Gson gson = new Gson();
                        progressBar.setVisibility(View.GONE);
                        scrollView.setVisibility(View.VISIBLE);

                        Show item = gson.fromJson(response, Show.class);

                        if (item.getImage() != null && item.getImage().getMedium() != null) {
                                Glide.with(DetailActivity.this).load(item.getImage().getOriginal()).into(pic1);
                                Glide.with(DetailActivity.this).load(item.getImage().getOriginal()).into(pic2);
                            }

                            if (item.getName() != null) {
                                titleTxt.setText(item.getName());
                            }
                            movieTypeTxt.setText(item.getType());
                            movieTimeTxt.setText(item.getRuntime());
                            movieDateTxt.setText(item.getPremiered());
                            movieSummaryInfo.setText(android.text.Html.fromHtml(item.getSummary()));
                            movieActorInfo.setText(String.join(", ", item.getGenres()));
                    }
                },
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressBar.setVisibility(View.GONE);
                        Log.i("uilover","onErrorResponse:"+error.toString());
                    }
                });
        mRequestQueue.add(mStringRequest);
    }

    private void initView() {
        titleTxt=findViewById(R.id.movieNameTxt);
        progressBar=findViewById(R.id.detailLoading);
        scrollView=findViewById(R.id.scrollView3);
        pic1 = findViewById(R.id.posterNormalImg);
        pic2=findViewById(R.id.posterBigImg);
        movieTypeTxt=findViewById(R.id.movieRateTxt);
        movieTimeTxt=findViewById(R.id.movieTimeTxt);
        movieDateTxt=findViewById(R.id.movieDateTxt);
        movieSummaryInfo=findViewById(R.id.movieSummaryInfo);
        movieActorInfo=findViewById(R.id.movieActorInfo);
        backImg=findViewById(R.id.backImg);
        recyclerView=findViewById(R.id.imageRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        backImg.setOnClickListener(view ->finish());
    }
}