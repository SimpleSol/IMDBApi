package com.example.leon.imdbapi.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.leon.imdbapi.R;
import com.example.leon.imdbapi.api.ImdbApi;
import com.example.leon.imdbapi.content.Movie;
import com.squareup.picasso.Picasso;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by Leon on 21.12.2015.
 */
public class MovieCardActivity extends AppCompatActivity {

    private static final String API = "http://www.omdbapi.com";

    private TextView mTitle;
    private TextView mYear;
    private TextView mDirector;
    private TextView mActors;
    private TextView mPlot;
    private TextView mImdbRating;

    private ImageView mPoster;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_movie_card);

        mTitle = (TextView) findViewById(R.id.title);
        mYear = (TextView) findViewById(R.id.year);
        mDirector = (TextView) findViewById(R.id.director);
        mActors = (TextView) findViewById(R.id.actors);
        mImdbRating = (TextView) findViewById(R.id.imdb_rating);
        mPlot = (TextView) findViewById(R.id.plot);
        mPoster = (ImageView) findViewById(R.id.poster);

        String title = getIntent().getStringExtra(MainActivity.MOVIE_TITLE);

        retrofitStaff(title);
    }

    private void retrofitStaff(String title) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ImdbApi imdbApi = retrofit.create(ImdbApi.class);
        Call<Movie> call = imdbApi.getMovie(String.valueOf(title), "full");
        call.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Response<Movie> response, Retrofit retrofit) {
                Movie movie = response.body();
                Picasso.with(getApplicationContext()).load(movie.getPoster()).into(mPoster);
                mTitle.append(" " + movie.getTitle());
                mYear.append(" " + movie.getYear());
                mDirector.append(" " + movie.getDirector());
                mActors.append(" " + movie.getActors());
                mImdbRating.append(" " + movie.getImdbRating());
                mPlot.append(" " + movie.getPlot());
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }
}
