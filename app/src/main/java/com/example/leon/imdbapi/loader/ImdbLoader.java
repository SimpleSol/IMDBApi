package com.example.leon.imdbapi.loader;

import android.content.AsyncTaskLoader;
import android.content.Context;

import com.example.leon.imdbapi.api.ImdbApi;
import com.example.leon.imdbapi.content.Movie;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by Leon on 21.12.2015.
 */
public class ImdbLoader extends AsyncTaskLoader<Movie> {

    private static final String API = "http://www.omdbapi.com";

    private String mTitle;

    private Movie mResult;

    public ImdbLoader(Context context, String title) {
        super(context);
        mTitle = title;
    }

    @Override
    public void deliverResult(Movie data) {
        if (isReset()) {
            return;
        }
        mResult = data;
        if (isStarted()) {
            super.deliverResult(data);
        }
    }

    @Override
    protected void onStartLoading() {
        if (mResult != null) {
            deliverResult(mResult);
        }
        if (takeContentChanged() || mResult == null) {
            forceLoad();
        }
    }

    @Override
    protected void onStopLoading() {
        cancelLoad();
    }

    @Override
    protected void onReset() {
        super.onReset();
        onStopLoading();
        mResult = null;
    }

    @Override
    public Movie loadInBackground() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ImdbApi imdbApi = retrofit.create(ImdbApi.class);
        Call<Movie> call = imdbApi.getMovie(mTitle, "full");
        call.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Response<Movie> response, Retrofit retrofit) {
                mResult = response.body();
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
        return mResult;
    }
}
