package com.joeahkim.chapaake.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.joeahkim.chapaake.pages.listss.PreviousResults
import com.joeahkim.chapaake.pages.listss.getPreviousResults

@Composable
fun Results(modifier: Modifier = Modifier) {
    val groupedResults = getPreviousResults().groupBy { it.date }

    LazyColumn(
        modifier = modifier.fillMaxSize()
            .padding(top = 88.dp)
    ) {
        groupedResults.forEach { (date, results) ->
            // Display the date as a header with a darker shade
            item {
                DateHeader(date = date)
            }

            // Display the results under the date header
            items(results) { result ->
                resultItem(item = result)
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
        Column(
            modifier = Modifier.weight(2f)
        ) {
            Text(
                text = item.homeTeam,
                style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.SemiBold)
            )
        }
        Column(
            modifier = Modifier.weight(1.5f)
        ) {
            Text(
                text = item.awayTeam,
                style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.SemiBold),
                modifier = Modifier.padding(start = 3.dp)
            )
        }
        Column(
            modifier = Modifier.weight(1.5f)
        ) {
            Text(
                text = item.prediction,
                style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Medium),
                modifier = Modifier.padding(start = 3.dp)
            )
        }
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = item.result,
                style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Light),
                modifier = Modifier.padding(start = 3.dp)
            )
        }
    }
}
