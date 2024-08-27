package com.joeahkim.chapaake.pages

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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.joeahkim.chapaake.pages.listss.PreviousResults
import com.joeahkim.chapaake.pages.listss.fetchPreviousResults

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
            .fillMaxSize()
            .padding(top = 80.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (isLoading) {
            // Show a loading indicator while the data is being fetched
            CircularProgressIndicator(
                color = Color.Green, // Customize the color if needed
                strokeWidth = 4.dp  // Customize the stroke width if needed
                ,modifier = Modifier.padding(top = 80.dp)
            )
            Text(text = "Loading...", style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold))
        } else {
            LazyColumn {
                itemsIndexed(items = resultsList) { index, item ->
                    // Optionally group by date
                    if (index == 0 || item.date != resultsList[index - 1].date) {
                        DateHeader(date = item.date)
                    }
                    resultItem(item = item)
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
            .background(Color.Gray) // Darker shade for the date header
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        Text(
            text = date,
            style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color.White)
        )
    }
}

@Composable
fun resultItem(item: PreviousResults) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(modifier = Modifier.weight(2f)) {
            Text(
                text = item.homeTeam,
                style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.SemiBold)
            )
        }
        Column(modifier = Modifier.weight(1.5f)) {
            Text(
                text = item.awayTeam,
                style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.SemiBold),
                modifier = Modifier.padding(start = 3.dp)
            )
        }
        Column(modifier = Modifier.weight(1.5f)) {
            Text(
                text = item.prediction,
                style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Medium),
                modifier = Modifier.padding(start = 3.dp)
            )
        }
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = item.result,
                style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Light),
                modifier = Modifier.padding(start = 3.dp)
            )
        }
    }
}
