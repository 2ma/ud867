package com.udacity.gradle.builditbigger;

import android.os.Bundle;
import android.support.design.widget.BaseTransientBottomBar;
import android.support.design.widget.Snackbar;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

public class MainActivity extends BaseActivity {

    private InterstitialAd interstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        interstitialAd = new InterstitialAd(this);
        interstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
        loadNewInterstitial();
        interstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                loadNewInterstitial();
                Result<Joke> result = viewModel.getResult();
                if (result != null) {
                    launchJokeDisplay(result);
                } else {
                    Snackbar.make(binding.jokeButton, R.string.error, BaseTransientBottomBar.LENGTH_SHORT).show();
                }
            }
        });

        AdView bannerAd = binding.adView;
        AdRequest adRequest = new AdRequest.Builder()
            .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
            .build();
        bannerAd.loadAd(adRequest);
    }

    @Override
    void onSuccess(Result<Joke> result) {
        super.onSuccess(result);
        if (interstitialAd.isLoaded()) {
            interstitialAd.show();
        } else {
            launchJokeDisplay(result);
        }
    }

    private void loadNewInterstitial() {
        AdRequest adRequest = new AdRequest.Builder()
            .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
            .build();
        interstitialAd.loadAd(adRequest);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (binding.adView != null) {
            binding.adView.resume();
        }
    }

    @Override
    protected void onPause() {
        if (binding.adView != null) {
            binding.adView.pause();
        }
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        if (binding.adView != null) {
            binding.adView.destroy();
        }
        super.onDestroy();
    }
}
