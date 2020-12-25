package com.sarojdilip171.mvpframwork.contract;

import com.sarojdilip171.mvpframwork.model.Movie;

import java.util.List;

public interface MovieListContract {

    interface Model{

        interface OnFinishedListener {
            void onFinished(List<Movie> movieArrayList);
            void onFailure(Throwable t);
        }

        void getMovieList(OnFinishedListener onFinishedListener, int pageNo);

    }

    interface View{

        void showProgress();
        void hideProgress();
        void setDataToRecyclerview(List<Movie> movieListArray);
        void onResponseFailure(Throwable throwable);

    }

    interface Presenter {

        void onDestroy();
        void getMoreData(int pageNo);
        void requestDataFromServer();
    }

}
