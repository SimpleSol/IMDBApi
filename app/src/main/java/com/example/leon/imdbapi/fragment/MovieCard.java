package com.example.leon.imdbapi.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.leon.imdbapi.R;
import com.example.leon.imdbapi.api.IMDBApi;
import com.example.leon.imdbapi.content.Movie;
import com.squareup.picasso.Picasso;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by Leon on 16.12.2015.
 */
public class MovieCard extends Fragment {

    private static final String API = "http://www.omdbapi.com";

    private TextView mTitle;
    private TextView mYear;
    private TextView mDirector;
    private TextView mActors;
    private TextView mPlot;
    private TextView mImdbRating;

    private ImageView mPoster;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fmt_movie_card, container, false);
        mTitle = (TextView) view.findViewById(R.id.title);
        mYear = (TextView) view.findViewById(R.id.year);
        mDirector = (TextView) view.findViewById(R.id.director);
        mActors = (TextView) view.findViewById(R.id.actors);
        mImdbRating = (TextView) view.findViewById(R.id.imdb_rating);
        mPlot = (TextView) view.findViewById(R.id.plot);
        mPoster = (ImageView) view.findViewById(R.id.poster);
        return view;

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        retrofitStaff(getArguments());
    }

    private void retrofitStaff(Bundle bundle) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        IMDBApi imdbApi = retrofit.create(IMDBApi.class);
        Call<Movie> call = imdbApi.getMovie(String.valueOf(bundle.getString("title")), "full");
        call.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Response<Movie> response, Retrofit retrofit) {
                Movie movie = response.body();
                Picasso.with(getActivity().getApplicationContext()).load(movie.getPoster()).into(mPoster);
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
















