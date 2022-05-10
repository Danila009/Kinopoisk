package com.example.feature_film_info.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.core_network_domain.model.movie.fact.Fact
import com.example.core_ui.ui.theme.secondaryBackground
import com.example.core_utils.common.replaceRange

@Composable
internal fun FactView(
    fact:MutableState<Fact>
) {
    if (fact.value.items.isNotEmpty()){
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Знаете ли вы, что...",
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
            items(fact.value.items){ item ->
                Card(
                    shape = AbsoluteRoundedCornerShape(7.dp),
                    modifier = Modifier
                        .padding(5.dp)
                        .width(200.dp)
                ) {
                    Column {
                        Text(
                            text = if(item.type == "FACT") "Факт о фильме" else "Ощибка в фильме",
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(5.dp)
                        )

                        Text(
                            text = replaceRange(item.text, 150),
                            modifier = Modifier
                                .padding(5.dp)
                        )
                    }
                }
            }
        })
    }
}