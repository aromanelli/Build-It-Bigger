package com.udacity.gradle.builditbigger;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);

//        // (https://developers.google.com/admob/android/banner)
//        AdView mAdView = root.findViewById(R.id.adView);
//        // Create an ad request. Check logcat output for the hashed
//        // device ID to get test ads on a physical device. e.g.
//        // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
//        AdRequest adRequest = new AdRequest.Builder()
//                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
//                .build();
//        Log.d(this.getClass().getSimpleName(), "onCreate: Loading banner ad");
//        // Note: Make all calls to the Mobile Ads SDK on the main thread.
//        mAdView.loadAd(adRequest);


        return root;
    }

}
