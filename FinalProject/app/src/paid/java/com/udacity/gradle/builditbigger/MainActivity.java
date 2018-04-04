package com.udacity.gradle.builditbigger;

public class MainActivity extends BaseActivity {

    @Override
    void onSuccess(Result<Joke> result) {
        super.onSuccess(result);
        launchJokeDisplay(result);
    }
}
