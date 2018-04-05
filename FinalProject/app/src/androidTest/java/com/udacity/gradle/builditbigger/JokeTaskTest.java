package com.udacity.gradle.builditbigger;

import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.CountDownLatch;

import static junit.framework.Assert.assertNotNull;

//based on answer: https://stackoverflow.com/questions/2321829/android-asynctask-testing-with-android-test-framework

@RunWith(AndroidJUnit4.class)
public class JokeTaskTest {

    @Test
    public void jokeTest() throws InterruptedException {

        final CountDownLatch countDownLatch = new CountDownLatch(1);

        new JokeTask(result -> {
            assertNotNull(result);
            //these two assertions need the server to run
            //assertNotNull(result.data);
            //assertNotNull(result.data.getQuestion());
            countDownLatch.countDown();
        }).execute();

        countDownLatch.await();
    }
}