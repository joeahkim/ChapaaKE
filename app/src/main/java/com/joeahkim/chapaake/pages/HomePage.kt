package com.joeahkim.chapaake.pages


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.joeahkim.chapaake.ads.BannerAdView
import com.joeahkim.chapaake.ads.NativeAdExample
import com.joeahkim.chapaake.convertKenyanTimeToLocal
import com.joeahkim.chapaake.pages.listss.Betslip
import com.joeahkim.chapaake.pages.listss.TodaysTip
import com.joeahkim.chapaake.pages.titlerows.TitleRow
@Composable
fun HomePage(modifier: Modifier = Modifier) {
    var tipsList by remember { mutableStateOf(emptyList<TodaysTip>()) }
    var isLoading by remember { mutableStateOf(true) }

    // Fetch today's tips from Firebase
    fetchTodaysTips { fetchedTips ->
        tipsList = fetchedTips
        isLoading = false
    }


    if (isLoading) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CircularProgressIndicator(
                color = Color.Green,
                strokeWidth = 4.dp, modifier = Modifier.padding(top = 80.dp)
            )
            Text(text = "Loading...", style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold))

        }
    } else {
        Column (
            modifier = Modifier.fillMaxSize()
        ){


            // Display Today's Tips
            if (tipsList.isEmpty()) {
                // Center the "No tips for today." text
                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "No tips for today.",
                        style = TextStyle(fontSize = 22.sp, fontWeight = FontWeight.Medium),
                        color = Color.Gray
                    )
                }
            } else {
                LazyColumn(
                    modifier = modifier
                        .fillMaxSize()
                        .padding(16.dp)
                ) {
                    item{
                        NativeAdExample()
                    }
                    item {

                        TitleRow()
                    }
                    items(tipsList) { tip ->
                        tipItem(item = tip)
                    }
                }
            }
        }
    }
}

@Composable
fun tipItem(item: TodaysTip) {
    val localTime = convertKenyanTimeToLocal(item.time)

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "${item.country}\n${item.league}", // Display the league name
            style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Medium),
            modifier = Modifier.weight(2f)
        )
        Text(
            text = "${item.homeTeam}\n${item.awayTeam}", // Display home and away teams
            style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Medium),
            modifier = Modifier.weight(2f)
        )
        Text(
            text = item.prediction, // Display the prediction
            style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Medium),
            modifier = Modifier.weight(1.5f)
        )
        Text(
            text = localTime, // Display the time
            style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Light),
            modifier = Modifier.weight(0.75f)
        )
    }
}


fun fetchTodaysTips(onDataFetched: (List<TodaysTip>) -> Unit) {
    val database = Firebase.database
    val ref = database.getReference("todaysTips")

    ref.addListenerForSingleValueEvent(object : ValueEventListener {
        override fun onDataChange(snapshot: DataSnapshot) {
            val tipsList = mutableListOf<TodaysTip>()
            for (tipSnapshot in snapshot.children) {
                val tip = tipSnapshot.getValue(TodaysTip::class.java)
                tip?.let {
                    tipsList.add(it)
                }
            }
            // Sort the list by time
            val sortedList = tipsList.sortedBy { it.time }
            onDataFetched(sortedList)
        }

        override fun onCancelled(error: DatabaseError) {
            // Handle error
        }
    })
}

