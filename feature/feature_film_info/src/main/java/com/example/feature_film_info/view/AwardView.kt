package com.example.feature_film_info.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.core_network_domain.model.IMDb.award.Award
import com.example.core_ui.ui.theme.secondaryBackground
import com.example.core_utils.R

@Composable
internal fun AwardView(
    award: Award
) {
    award.items?.let{ awardItem ->
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Awards",
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
            awardItem.forEach { item ->
                items(item.outcomeItems){ outcomeItem ->
                    Card(
                        shape = AbsoluteRoundedCornerShape(7.dp),
                        modifier = Modifier.padding(5.dp)
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Image(
                                painter = painterResource(
                                    id = when (outcomeItem.outcomeCategory) {
                                        "Oscar" -> R.drawable.oscar
                                        "Razzie Award" -> R.drawable.raspberry
                                        "Golden Globe" -> R.drawable.golden_globe
                                        "MTV Movie Award" -> R.drawable.mtv
                                        "Jury Prize" -> R.drawable.cannes
                                        "Saturn Award" -> R.drawable.saturn
                                        else -> R.drawable.no_image
                                    }
                                ),
                                contentDescription = null,
                                modifier = Modifier
                                    .size(150.dp)
                                    .padding(5.dp)
                            )

                            Text(
                                text = outcomeItem.outcomeTitle,
                                modifier = Modifier.padding(5.dp)
                            )
                            Text(
                                text = outcomeItem.outcomeCategory,
                                modifier = Modifier.padding(5.dp)
                            )
                        }
                    }
                }
            }
        })
    }
}