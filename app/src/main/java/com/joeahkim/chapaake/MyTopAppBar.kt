package com.joeahkim.chapaake

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.joeahkim.chapaake.ui.theme.GreenJC

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopAppBar() {
    TopAppBar(
navigationIcon = {
    Image(painter = painterResource(id = R.drawable.topappbaricon), contentDescription = "App Logo",
        contentScale = ContentScale.Fit,
        modifier = Modifier
            .padding(8.dp)
            .size(40.dp))
},
        title = { Text("Kash Betting Tips") },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = GreenJC,
            titleContentColor = Color.White
        )
    )
}
