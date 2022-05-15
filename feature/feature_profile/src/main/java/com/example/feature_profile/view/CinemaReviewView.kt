package com.example.feature_profile.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.core_network_domain.common.Response
import com.example.core_network_domain.model.cinema.ReviewItem
import com.example.core_ui.animation.ImageShimmer
import com.example.core_ui.ui.theme.secondaryBackground
import com.example.core_utils.common.rating
import com.example.core_utils.common.replaceRange
import kotlinx.serialization.ExperimentalSerializationApi

@ExperimentalSerializationApi
@Composable
internal fun CinemaReviewView(
    reviewItem: Response<List<ReviewItem>>
) {
    if (reviewItem !is Response.Error){
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Cinema reviews:",
                modifier = Modifier.padding(5.dp),
                fontWeight = FontWeight.Bold,
                color = secondaryBackground
            )

            TextButton(
                onClick = { /*TODO*/ },
                modifier = Modifier.padding(5.dp)
            ) {
                Text(
                    text = "Все ->",
                    color = secondaryBackground
                )
            }
        }

        LazyRow(content = {
            if (reviewItem is Response.Success){
                items(reviewItem.data!!){ item ->
                    Card(
                        modifier = Modifier.padding(5.dp),
                        shape = AbsoluteRoundedCornerShape(15.dp)
                    ) {
                        Column {

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Column {
                                    Text(
                                        text = item.title,
                                        modifier = Modifier.padding(5.dp)
                                    )

                                    Text(
                                        text = replaceRange(item.description, 120),
                                        modifier = Modifier.padding(5.dp)
                                    )
                                }
                                Text(
                                    text = item.rating.toString(),
                                    modifier = Modifier.padding(5.dp),
                                    color = rating(item.rating)
                                )
                            }

                            Text(
                                text = item.date,
                                modifier = Modifier
                                    .padding(5.dp)
                                    .fillMaxWidth(),
                                textAlign = TextAlign.End
                            )
                        }
                    }
                }
            }else {
                items(3){
                    ImageShimmer(
                        modifier = Modifier.size(
                            width = 150.dp,
                            height = 100.dp
                        ).clip(AbsoluteRoundedCornerShape(15.dp))
                            .padding(5.dp)
                    )
                }
            }
        })
    }
}