package com.example.feature_cinema_info.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.core_network_domain.model.cinema.ReviewItem
import com.example.core_ui.ui.theme.primaryBackground
import com.example.core_ui.ui.theme.secondaryBackground

@Composable
fun ReviewView(
    review: ReviewItem
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
            .background(primaryBackground),
        elevation = 8.dp,
        shape = AbsoluteRoundedCornerShape(15.dp)
    ) {
        Column {
            Text(
                text = review.user.username,
                modifier = Modifier.padding(5.dp),
                color = secondaryBackground,
                fontWeight = FontWeight.Bold
            )
            Row {
                Text(
                    text = review.title,
                    modifier = Modifier.padding(5.dp)
                )
                Text(
                    text = review.rating.toString(),
                    modifier = Modifier.padding(5.dp)
                )
            }
            Text(
                text = review.description,
                modifier = Modifier.padding(5.dp)
            )
            Text(
                text = review.date,
                modifier = Modifier.padding(5.dp)
            )
        }
    }
}