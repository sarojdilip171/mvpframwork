package com.sarojdilip171.mvpframwork;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.sarojdilip171.mvpframwork.contract.MovieListContract;
import com.sarojdilip171.mvpframwork.model.Movie;
import com.sarojdilip171.mvpframwork.presenter.MoviePresenter;
import com.sarojdilip171.mvpframwork.view.MovieListAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MovieListContract.View {

    private MoviePresenter moviePresenter;
    private RecyclerView rvMovieList;
    private List<Movie> movieList;
    private MovieListAdapter movieListAdapter;
    private ProgressBar pbLoading;
    private int pageNo = 1;

    private LinearLayoutManager layoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvMovieList = findViewById(R.id.rvMovieList);
        pbLoading = findViewById(R.id.pbLoading);

        movieList = new ArrayList<>();
        layoutManager = new LinearLayoutManager(this);
        rvMovieList.setLayoutManager(layoutManager);
        rvMovieList.setHasFixedSize(true);

        moviePresenter = new MoviePresenter(this);
        moviePresenter.requestDataFromServer();

    }

    @Override
    public void showProgress() {
        pbLoading.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        pbLoading.setVisibility(View.GONE);
    }

    @Override
    public void setDataToRecyclerview(List<Movie> movieListArray) {

        movieList.addAll(movieListArray);
        movieListAdapter = new MovieListAdapter(movieList, MainActivity.this);
        rvMovieList.setAdapter(movieListAdapter);

    }

    @Override
    public void onResponseFailure(Throwable throwable) {

        Log.e("ERROR:", throwable.getMessage());
        Toast.makeText(MainActivity.this, "Error in getting data", Toast.LENGTH_LONG).show();

    }
}
