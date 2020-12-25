package com.sarojdilip171.mvpframwork.presenter;

import com.sarojdilip171.mvpframwork.contract.MovieListContract;
import com.sarojdilip171.mvpframwork.model.Movie;
import com.sarojdilip171.mvpframwork.service.MovieListModel;

import java.util.List;

public class MoviePresenter  implements MovieListContract.Presenter, MovieListContract.Model.OnFinishedListener {

    private MovieListContract.View movieListView;
    private MovieListContract.Model movieListModel;

    public MoviePresenter(MovieListContract.View movieListView) {
        this.movieListView = movieListView;
        movieListModel = new MovieListModel();
    }

    @Override
    public void onDestroy() {
        this.movieListView = null;
    }

    @Override
    public void getMoreData(int pageNo) {

        if(movieListView != null) {
            movieListView.showProgress();
        }

        movieListModel.getMovieList(this, pageNo);

    }

    @Override
    public void requestDataFromServer() {

        if(movieListView != null) {
            movieListView.showProgress();
        }

        movieListModel.getMovieList(this, 1);

    }

    @Override
    public void onFinished(List<Movie> movieArrayList) {

        movieListView.setDataToRecyclerview(movieArrayList);

        if(movieListView != null) {
            movieListView.hideProgress();
        }

    }

    @Override
    public void onFailure(Throwable t) {

        movieListView.onResponseFailure(t);

        if(movieListView != null) {
            movieListView.hideProgress();
        }

    }
}
