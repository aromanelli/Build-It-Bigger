package com.udacity.gradle.builditbigger;

import android.support.test.runner.AndroidJUnit4;
import android.text.TextUtils;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

@RunWith(AndroidJUnit4.class)
public class EndpointsAsyncTaskTest {

    private final CountDownLatch latch = new CountDownLatch(1);

    class TestListener implements EndpointsAsyncTask.Listener {
        @Override
        public void fetchedJoke(String text) {
            try {
                // REVIEWER: With a production app, I'd make sure the JokesFetcher
                // would return error signaling so that tests can be more thorough.
                assertTrue(!TextUtils.isEmpty(text));
            } finally {
                latch.countDown();
            }
        }
    }

    @Test
    public void testTask() {

        new EndpointsAsyncTask().execute( new TestListener() );

        boolean flag = true;
        try {
            flag = latch.await(60, TimeUnit.SECONDS);
        } catch (InterruptedException ie) {
            fail("An error occurred while waiting for fetching to be done. " + ie.getLocalizedMessage());
        }
        if (!flag) {
            fail("Timeout reached while waiting for fetching to be done.");
        }

    }

}
