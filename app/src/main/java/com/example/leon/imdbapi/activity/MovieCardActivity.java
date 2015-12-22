package com.example.leon.imdbapi.activity;

import android.app.LoaderManager;
import android.content.Loader;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.leon.imdbapi.R;
import com.example.leon.imdbapi.api.ImdbApi;
import com.example.leon.imdbapi.content.Movie;
import com.example.leon.imdbapi.loader.ImdbLoader;
import com.squareup.picasso.Picasso;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by Leon on 21.12.2015.
 */
public class MovieCardActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Movie> {


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
        Bundle bundle = new Bundle();
        bundle.putString(MainActivity.MOVIE_TITLE, getIntent().getStringExtra(MainActivity.MOVIE_TITLE));

        getLoaderManager().initLoader(R.id.imdb_loader, bundle, this).forceLoad();

    }

    @Override
    public Loader<Movie> onCreateLoader(int id, Bundle args) {
        if (R.id.imdb_loader == id) {
            return new ImdbLoader(getApplicationContext(), args.getString(MainActivity.MOVIE_TITLE));
        }
        return null;
    }

    @Override
    public void onLoadFinished(Loader<Movie> loader, Movie movie) {
        Picasso.with(getApplicationContext()).load(movie.getPoster()).into(mPoster);
        mTitle.append(" " + movie.getTitle());
        mYear.append(" " + movie.getYear());
        mDirector.append(" " + movie.getDirector());
        mActors.append(" " + movie.getActors());
        mImdbRating.append(" " + movie.getImdbRating());
        mPlot.append(" " + movie.getPlot());
    }

    @Override
    public void onLoaderReset(Loader<Movie> loader) {

    }
}
