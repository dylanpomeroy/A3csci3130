package com.acme.a3csci3130;

import android.support.test.espresso.IdlingResource;

/**
 * Created by elber on 7/6/2017.
 */

public class ElapsedTimeIdlingResource implements IdlingResource {
    private final long startTime;
    private final long waitTime;
    private ResourceCallback resourceCallback;

    /**
     * Initializes a new instance of the ElapsedTimeIdlingResource class.
     * @param waitingTime The amount of time to idle.
     */
    public ElapsedTimeIdlingResource(long waitingTime) {
        this.startTime = System.currentTimeMillis();
        this.waitTime = waitingTime;
    }

    /**
     * Gets the name and wait time of the Idling Resource.
     * @return The name and wait time fo the Idling Resource.
     */
    @Override
    public String getName() {
        return ElapsedTimeIdlingResource.class.getName() + ":" + waitTime;
    }

    /**
     * Returns whether or not the time elapsed is longer than the required wait time.
     * @return A boolean determining whether or not the appropriate amount of time has elapsed.
     */
    @Override
    public boolean isIdleNow() {
        long elapsed = System.currentTimeMillis() - startTime;
        boolean idle = (elapsed >= waitTime);
        if (idle) {
            resourceCallback.onTransitionToIdle();
        }
        return idle;
    }

    /**
     * Assigns the resource callback.
     * @param resourceCallback The resource callback to assign.
     */
    @Override
    public void registerIdleTransitionCallback(
            ResourceCallback resourceCallback) {
        this.resourceCallback = resourceCallback;
    }
}
