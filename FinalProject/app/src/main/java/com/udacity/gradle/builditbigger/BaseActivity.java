package com.udacity.gradle.builditbigger;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.udacity.gradle.builditbigger.databinding.ActivityMainBinding;

import hu.am2.jokedisplay.JokeDisplayActivity;


public class BaseActivity extends AppCompatActivity {

    private static final String TAG = "BaseActivity";

    ActivityMainBinding binding;

    BaseViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        viewModel = ViewModelProviders.of(this).get(BaseViewModel.class);

        binding.jokeButton.setOnClickListener(v -> loadJoke());

        viewModel.getJoke().observe(this, this::jokeResult);

    }

    private void loadJoke() {
        viewModel.loadJoke();
    }

    private void jokeResult(Result<Joke> result) {

        switch (result.status) {
            case LOADING: {
                binding.jokeButton.setVisibility(View.GONE);
                binding.progressBar.setVisibility(View.VISIBLE);
                break;
            }
            case SUCCESS: {
                onSuccess(result);
                break;
            }
            case ERROR: {
                binding.progressBar.setVisibility(View.GONE);
                Snackbar.make(binding.jokeButton, R.string.error, Snackbar.LENGTH_SHORT).show();
                Log.d(TAG, "jokeResult: " + result.errorMessage);
                binding.jokeButton.setVisibility(View.VISIBLE);
                break;
            }
        }
    }

    void onSuccess(Result<Joke> result) {
        binding.progressBar.setVisibility(View.GONE);
        binding.jokeButton.setVisibility(View.VISIBLE);
    }

    void launchJokeDisplay(Result<Joke> result) {
        if (result.data != null) {
            JokeDisplayActivity.launchJokeDisplay(this, result.data.getQuestion(), result.data.getAnswer());
        } else {
            Snackbar.make(binding.jokeButton, R.string.error, Snackbar.LENGTH_SHORT).show();
        }

    }
}
