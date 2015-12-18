package com.example.leon.imdbapi.activity;

import android.app.Activity;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.leon.imdbapi.R;
import com.example.leon.imdbapi.fragment.MainFragment;

public class MainActivity extends Activity implements FragmentManager.OnBackStackChangedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_main);

        if (savedInstanceState == null) {
            getFragmentManager()
                    .beginTransaction()
                    .add(R.id.frame, new MainFragment())
                    .commit();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (android.R.id.home == item.getItemId()) {
            getFragmentManager().popBackStackImmediate();
            return true;
        }
        return super.onOptionsItemSelected(item);

    }

    @Override
    protected void onStart() {
        super.onStart();
        getFragmentManager().addOnBackStackChangedListener(this);
    }

    @Override
    public void onBackStackChanged() {
        if (getActionBar() != null) {
            if (getFragmentManager().getBackStackEntryCount() > 0) {
                getActionBar().setDisplayHomeAsUpEnabled(true);
            } else {
                getActionBar().setDisplayHomeAsUpEnabled(false);
            }
        }
    }
}
