package com.udacity.gradle.builditbigger.backend;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;

import hu.am2.joke.Joke;
import hu.am2.joke.JokeModel;

/** An endpoint class we are exposing */
@Api(
        name = "myApi",
        version = "v1",
        namespace = @ApiNamespace(
                ownerDomain = "backend.builditbigger.gradle.udacity.com",
                ownerName = "backend.builditbigger.gradle.udacity.com",
                packagePath = ""
        )
)
public class MyEndpoint {

    private final Joke joke;

    public MyEndpoint() {
        joke = new Joke();
    }

    /**
     * A simple endpoint method that tells a joke
     */
    @ApiMethod(name = "getJoke")
    public MyBean getJoke() {
        MyBean response = new MyBean();
        JokeModel j = joke.getJoke();
        response.setQuestion(j.getQ());
        response.setAnswer(j.getA());

        return response;
    }

}
