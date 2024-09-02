package com.joeahkim.chapaake.ads

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.View
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.google.android.gms.ads.*
import com.google.android.gms.ads.nativead.NativeAd
import com.google.android.gms.ads.nativead.NativeAdOptions
import com.google.android.gms.ads.nativead.NativeAdView
import com.joeahkim.chapaake.R
@Composable
fun NativeAdExample() {
    val context = LocalContext.current
    var nativeAd by remember { mutableStateOf<NativeAd?>(null) }

    // Load the native ad
    LaunchedEffect(Unit) {
        loadNativeAd(context, context.getString(R.string.native_ad_id)) {
            nativeAd = it
        }
    }

    // Ensure that the native ad is released when the composable is removed
    DisposableEffect(nativeAd) {
        onDispose {
            nativeAd?.destroy()  // Clean up the native ad to avoid memory leaks
        }
    }

    // Display the native ad if it's loaded
    nativeAd?.let {
        CallNativeAd(it)
    }
}

fun loadNativeAd(context: Context, adUnitId: String, callback: (NativeAd?) -> Unit) {
    val adLoader = AdLoader.Builder(context, adUnitId)
        .forNativeAd { nativeAd ->
            callback(nativeAd)
        }
        .withAdListener(object : AdListener() {
            override fun onAdFailedToLoad(adError: LoadAdError) {
                callback(null)
            }
        })
        .withNativeAdOptions(NativeAdOptions.Builder().build())
        .build()

    adLoader.loadAd(AdRequest.Builder().build())
}

@Composable
fun CallNativeAd(nativeAd: NativeAd) {
    NativeAdViewComposable(nativeAd = nativeAd)
}

@Composable
fun NativeAdViewComposable(
    nativeAd: NativeAd,
) {
    AndroidView(
        factory = { context ->
            NativeAdView(context).apply {
                setNativeAd(nativeAd)

                // Create and add a ComposeView to the NativeAdView
                val composeView = ComposeView(context).apply {
                    setContent {
                        LoadAdContent(nativeAd, this)
                    }
                }
                addView(composeView)
            }
        },
        update = { nativeAdView ->
            // Ensure proper handling of views and content
            nativeAdView.setNativeAd(nativeAd)
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoadAdContent(nativeAd: NativeAd, nativeAdView: View) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            Row(modifier = Modifier.fillMaxWidth()) {
                val iconDrawable: Drawable? = nativeAd.icon?.drawable
                iconDrawable?.let { drawable ->
                    Image(
                        painter = rememberAsyncImagePainter(model = drawable),
                        contentDescription = null, // Manually set the content description or set to null
                        modifier = Modifier
                            .size(40.dp)
                            .padding(end = 8.dp)
                    )
                }
                Column {
                    Text(
                        text = nativeAd.headline ?: "",
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onSurface,
                    )
                    Text(
                        text = nativeAd.body ?: "",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurface,
                    )
                }
            }

            nativeAd.callToAction?.let { callToAction ->
                Button(
                    onClick = { nativeAdView.performClick() },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = callToAction)
                }
            }
        }
    }
}
