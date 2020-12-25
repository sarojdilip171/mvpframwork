package com.sarojdilip171.mvpframwork.service;

import android.util.Log;

import com.sarojdilip171.mvpframwork.contract.MovieListContract;
import com.sarojdilip171.mvpframwork.model.Movie;
import com.sarojdilip171.mvpframwork.model.MovieListResponse;
import com.sarojdilip171.mvpframwork.network.ApiClient;
import com.sarojdilip171.mvpframwork.network.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieListModel implements MovieListContract.Model {

    private final String TAG = "MovieListModel";
    private int pageNo = 1;

    @Override
    public void getMovieList(final OnFinishedListener onFinishedListener, int pageNo) {

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        Call<MovieListResponse> call = apiService.getPopularMovies(ApiClient.API_KEY, pageNo);

        call.enqueue(new Callback<MovieListResponse>() {
            @Override
            public void onResponse(Call<MovieListResponse> call, Response<MovieListResponse> response) {
                List<Movie> movies = response.body().getResults();
                Log.e(TAG, "Number of movies received: "+ movies.size());

                onFinishedListener.onFinished(movies);
            }

            @Override
            public void onFailure(Call<MovieListResponse> call, Throwable t) {

                Log.e(TAG, t.toString());
                onFinishedListener.onFailure(t);

            }
        });

    }
}
