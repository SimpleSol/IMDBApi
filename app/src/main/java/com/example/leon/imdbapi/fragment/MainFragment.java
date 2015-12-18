package com.example.leon.imdbapi.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.leon.imdbapi.R;

/**
 * Created by Leon on 16.12.2015.
 */
public class MainFragment extends Fragment implements View.OnClickListener {


    private EditText mEditText;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fmt_main, container, false);
        Button mButton = (Button) view.findViewById(R.id.btn_1);
        mButton.setOnClickListener(this);
        mEditText = (EditText) view.findViewById(R.id.edit_text);
        return view;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_1) {
            MovieCard movieCard = new MovieCard();
            Bundle bundle = new Bundle();
            bundle.putString("title", String.valueOf(mEditText.getText()));
            movieCard.setArguments(bundle);
            getFragmentManager()
                    .beginTransaction()
                    .replace(R.id.frame, movieCard)
                    .addToBackStack(MovieCard.class.getName())
                    .commit();
        }
    }

}
