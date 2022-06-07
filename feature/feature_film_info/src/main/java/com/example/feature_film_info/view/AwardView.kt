package com.example.feature_film_info.view

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
import com.example.core_network_domain.common.Response
import com.example.core_network_domain.model.movie.award.Award
import com.example.core_ui.ui.theme.secondaryBackground
import com.example.core_ui.view.Image
import com.example.core_utils.R

@Composable
internal fun AwardView(
    award: Response<Award>
) {
    if (award is Response.Success){
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

            items(award.data?.items ?: emptyList()){ item ->
                Card(
                    shape = AbsoluteRoundedCornerShape(7.dp),
                    modifier = Modifier.padding(5.dp)
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        if (item.imageUrl != null){
                            Image(
                                url = item.imageUrl!!,
                                modifier = Modifier.size(70.dp, 140.dp)
                            )
                        }else {
                            androidx.compose.foundation.Image(
                                painter = painterResource(
                                    id = when (item.name) {
                                        "Оскар" -> R.drawable.oscar
                                        "Золотая малина" -> R.drawable.raspberry
                                        "Золотой глобус" -> R.drawable.golden_globe
                                        "Премия канала «MTV»" -> R.drawable.mtv
                                        "Каннский кинофестиваль" -> R.drawable.cannes
                                        "Сатурн" -> R.drawable.saturn
                                        else -> R.drawable.no_image
                                    }
                                ),
                                contentDescription = null,
                                modifier = Modifier
                                    .size(70.dp, 140.dp)
                                    .padding(5.dp)
                            )
                        }

                        Text(
                            text = item.name,
                            modifier = Modifier.padding(5.dp),
                            fontWeight = FontWeight.W100
                        )

                        Text(
                            text = item.nominationName,
                            modifier = Modifier.padding(5.dp),
                            fontWeight = FontWeight.W900
                        )
                    }
                }
            }
        })
    }
}