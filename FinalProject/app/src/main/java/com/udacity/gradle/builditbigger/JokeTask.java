package com.udacity.gradle.builditbigger;

import android.os.AsyncTask;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;
import com.udacity.gradle.builditbigger.backend.myApi.model.MyBean;

import java.io.IOException;
import java.lang.ref.WeakReference;

//based of off: https://github.com/GoogleCloudPlatform/gradle-appengine-templates/tree/77e9910911d5412e5efede5fa681ec105a0f02ad/HelloEndpoints#2
// -connecting-your-android-app-to-the-backend

class JokeTask extends AsyncTask<Void, Void, Result<Joke>> {

    private static MyApi myApi = null;

    private final WeakReference<JokeListener> jokeListenerWeakReference;

    public JokeTask(JokeListener jokeListener) {
        jokeListenerWeakReference = new WeakReference<>(jokeListener);
    }

    @Override
    protected Result<Joke> doInBackground(Void... voids) {

        if (myApi == null) {
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
                .setRootUrl("http://10.0.3.2:8080/_ah/api/")
                .setGoogleClientRequestInitializer(request -> request.setDisableGZipContent(true));

            myApi = builder.build();
        }

        try {
            final MyBean myBean = myApi.getJoke().execute();
            return Result.success(new Joke(myBean.getQuestion(), myBean.getAnswer()));
        } catch (IOException e) {
            e.printStackTrace();
            return Result.error(e.getMessage());
        }
    }

    @Override
    protected void onPostExecute(Result<Joke> j) {
        if (jokeListenerWeakReference.get() != null) {
            jokeListenerWeakReference.get().jokeResult(j);
        }
    }

    public interface JokeListener {
        void jokeResult(Result<Joke> result);
    }
}
