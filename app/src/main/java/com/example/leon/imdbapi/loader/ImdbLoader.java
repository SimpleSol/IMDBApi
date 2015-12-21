package com.example.leon.imdbapi.loader;

import android.content.AsyncTaskLoader;
import android.content.Context;

import com.example.leon.imdbapi.api.ImdbApi;
import com.example.leon.imdbapi.content.Movie;

import retrofit.Call;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

/**
 * Created by Leon on 21.12.2015.
 */
public class ImdbLoader extends AsyncTaskLoader<Call<Movie>> {

    private static final String API = "http://www.omdbapi.com";

    private String mTitle;

    public ImdbLoader(Context context, String title) {
        super(context);
        mTitle = title;
    }

    @Override
    public Call<Movie> loadInBackground() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ImdbApi imdbApi = retrofit.create(ImdbApi.class);
        return imdbApi.getMovie(mTitle, "full");
    }

    @Override
    public void deliverResult(Call<Movie> data) {
        super.deliverResult(data);
    }
}
