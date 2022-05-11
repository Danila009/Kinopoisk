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
import com.example.core_network_domain.model.movie.budget.Budget
import com.example.core_network_domain.model.movie.distribution.Distribution
import com.example.core_ui.ui.theme.secondaryBackground
import com.example.core_utils.common.getTime

@Composable
internal fun BudgetView(
    budget:MutableState<Budget>,
    distribution:MutableState<Distribution>
) {
    if (budget.value.items.isNotEmpty()){
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Прокат",
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
            items(budget.value.items){ item ->
                Card(
                    shape = AbsoluteRoundedCornerShape(7.dp),
                    modifier = Modifier.padding(5.dp)
                ) {
                    Column {
                        Text(
                            text = item.type,
                            modifier = Modifier.padding(5.dp),
                            color = secondaryBackground
                        )
                        Text(
                            text = "${item.amount} ${item.symbol}",
                            modifier = Modifier.padding(5.dp)
                        )
                    }
                }
            }
            items(distribution.value.items){ item ->
                Card(
                    shape = AbsoluteRoundedCornerShape(7.dp),
                    modifier = Modifier.padding(5.dp)
                ) {
                    Column {
                        item.country?.let {
                            if (it.country.isNotEmpty()){
                                Text(
                                    text = it.country,
                                    modifier = Modifier.padding(5.dp),
                                    color = secondaryBackground
                                )
                            }
                        }

                        if (item.date.isNotEmpty()){
                            Text(
                                text = getTime(item.date),
                                modifier = Modifier.padding(5.dp)
                            )
                        }
                    }
                }
            }
        })
    }
}