package com.udacity.gradle.builditbigger;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.os.AsyncTask;
import android.support.annotation.Nullable;

public class BaseViewModel extends ViewModel implements JokeTask.JokeListener {

    private final MutableLiveData<Result<Joke>> joke = new MutableLiveData<>();

    private AsyncTask jokeTask;

    public void loadJoke() {
        joke.setValue(Result.loading());
        if (jokeTask != null) {
            jokeTask.cancel(true);
        }
        jokeTask = new JokeTask(this).execute();
    }

    @Override
    public void jokeResult(Result<Joke> result) {
        joke.setValue(result);
    }

    public LiveData<Result<Joke>> getJoke() {
        return joke;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        if (jokeTask != null) {
            jokeTask.cancel(true);
            jokeTask = null;
        }
    }

    @Nullable
    public Result<Joke> getResult() {
        return joke.getValue();
    }
}
