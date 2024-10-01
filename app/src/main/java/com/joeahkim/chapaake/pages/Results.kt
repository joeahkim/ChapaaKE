package com.joeahkim.chapaake.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.joeahkim.chapaake.R
import com.joeahkim.chapaake.ads.BannerAdView
import com.joeahkim.chapaake.ads.NativeAdExample
import com.joeahkim.chapaake.pages.listss.PreviousResults
import com.joeahkim.chapaake.pages.listss.fetchPreviousResults
import com.joeahkim.chapaake.pages.titlerows.ResultsTitleRow
import com.joeahkim.chapaake.pages.titlerows.TitleRow
import com.joeahkim.chapaake.ui.theme.GreenJC

@Composable
fun Results(modifier: Modifier = Modifier) {
    var resultsList by remember { mutableStateOf(emptyList<PreviousResults>()) }
    var isLoading by remember { mutableStateOf(true) }

    // Fetch data from Firebase once when the composable is first launched
    LaunchedEffect(Unit) {
        fetchPreviousResults { fetchedResults ->
            resultsList = fetchedResults
            isLoading = false
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (isLoading) {
            CircularProgressIndicator(
                color = Color.Green,
                strokeWidth = 4.dp
                ,modifier = Modifier.padding(top = 80.dp)
            )
            Text(text = "Loading...", style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold))
        } else {
            LazyColumn(
            ) {

                itemsIndexed(items = resultsList) { index, item ->
                    //group by date
                    if (index == 0 || item.date != resultsList[index - 1].date) {
                        NativeAdExample()
                        DateHeader(date = item.date)
                    }
                    ResultItem(item = item)
                }
            }
        }
    }
}

@Composable
fun DateHeader(date: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(GreenJC) // Darker shade for the date header
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = date,
            style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color.White)
        )
    }
}

@Composable
fun ResultItem(item: PreviousResults) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp) // Set a lighter background color
            .padding(10.dp) // Inner padding for content
    ) {
        // Home and away teams at the top
        Text(
            text = "${item.homeTeam} vs ${item.awayTeam}",
            style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Medium),
            modifier = Modifier.align(Alignment.Start) // Align to the start
        )

        Spacer(modifier = Modifier.height(4.dp)) // Small spacing between rows

        // Prediction in the middle
        Text(
            text = "Prediction: ${item.prediction}",
            style = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.Medium),
            modifier = Modifier.align(Alignment.Start) // Align to the start
        )

        Spacer(modifier = Modifier.height(4.dp)) // Small spacing between rows

        // Scores and result icons
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 5.dp),
            horizontalArrangement = Arrangement.SpaceBetween // Spread items out evenly
        ) {
            // Display the scores
            Text(
                text = "Scores: ${item.scores}",
                style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold),
                modifier = Modifier.align(Alignment.CenterVertically)
            )

            // Display result as an icon
            val icon = if (item.result == "Win") {
                R.drawable.checked // Green tick for "Win"
            } else {
                R.drawable.close // Red X for "Loss"
            }

            Image(
                painter = painterResource(id = icon),
                contentDescription = null,
                modifier = Modifier
                    .size(24.dp)
                    .align(Alignment.CenterVertically)
            )
        }
    }

    // Spacer between results
    Spacer(modifier = Modifier.height(5.dp)) // 5dp spacing between each item
}
