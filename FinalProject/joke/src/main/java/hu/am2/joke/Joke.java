package hu.am2.joke;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Random;


public class Joke {

    private final List<JokeModel> jokes;

    public Joke() {
        InputStream stream = Thread.currentThread().getContextClassLoader().getResourceAsStream("jokes.json");
        Reader reader = new InputStreamReader(stream);
        Type jokeList = new TypeToken<List<JokeModel>>() {
        }.getType();

        jokes = new Gson().fromJson(reader, jokeList);
    }

    public JokeModel getJoke() {
        if (jokes != null && jokes.size() > 0) {
            Random random = new Random();

            return jokes.get(random.nextInt(jokes.size()));
        }
        return new JokeModel("I'm fresh out of jokes!", "Sorry");
    }
}
