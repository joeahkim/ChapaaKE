package com.joeahkim.chapaake

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.rewarded.RewardItem
import com.google.android.gms.ads.rewarded.RewardedAd
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.joeahkim.chapaake.ui.theme.ChapaaKETheme

class MainActivity : ComponentActivity() {

    private var rewardedAd: RewardedAd? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Initialize Firebase & AdMob
        MobileAds.initialize(this)
        Firebase.database.setPersistenceEnabled(true)

        // Splash screen implementation
        val splashScreen = installSplashScreen()

        // Load the rewarded ad
        loadRewardedAd()

        // Set up the app content
        setContent {
            ChapaaKETheme {
                MainScreen()  // This will display after the rewarded ad or if ad fails
            }
        }
    }

    private fun loadRewardedAd() {
        val adRequest = AdRequest.Builder().build()

        RewardedAd.load(this, "ca-app-pub-4576642380177322/8084832248", adRequest, object : RewardedAdLoadCallback() {
            override fun onAdFailedToLoad(adError: LoadAdError) {
                // If the ad fails, directly proceed to the main screen
                navigateToMainScreen()
            }

            override fun onAdLoaded(ad: RewardedAd) {
                rewardedAd = ad
                showRewardedAd()
            }
        })
    }

    private fun showRewardedAd() {
        rewardedAd?.show(this) { rewardItem: RewardItem ->
            // Handle the reward
            navigateToMainScreen()  // Proceed to the main screen after ad
        } ?: run {
            // If the ad is not loaded, directly proceed to the main screen
            navigateToMainScreen()
        }
    }

    private fun navigateToMainScreen() {
        // Navigate to the main screen content
        setContent {
            ChapaaKETheme {
                MainScreen()
            }
        }
    }
}
