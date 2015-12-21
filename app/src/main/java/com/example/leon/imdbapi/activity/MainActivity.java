package com.example.leon.imdbapi.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.leon.imdbapi.BuildConfig;
import com.example.leon.imdbapi.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String MOVIE_TITLE = BuildConfig.APPLICATION_ID + ".MOVIE_TITLE";

    private EditText mEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_main);
        mEditText = (EditText) findViewById(R.id.edit_text);
        Button mButton = (Button) findViewById(R.id.btn);
        mButton.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, MovieCardActivity.class);
        intent.putExtra(MOVIE_TITLE, mEditText.getText().toString());
        mEditText.setText("");
        startActivity(intent);
    }
}
