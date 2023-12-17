package com.pratwib.animaze.ui.screen.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.pratwib.animaze.R

@Composable
fun ProfileScreen() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = painterResource(R.drawable.photo_profile),
                contentScale = ContentScale.FillWidth,
                contentDescription = null,
                modifier = Modifier
                    .size(160.dp)
                    .clip(
                        CircleShape
                    )
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(text = "Pratama Wibi", style = MaterialTheme.typography.h5)

            Spacer(modifier = Modifier.height(8.dp))

            Text(text = "pratamawibi24@gmail.com", style = MaterialTheme.typography.body1, color = Color.Gray)
        }
    }
}