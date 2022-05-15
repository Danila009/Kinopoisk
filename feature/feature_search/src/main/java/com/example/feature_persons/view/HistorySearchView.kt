package com.example.feature_persons.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.core_network_domain.common.Response
import com.example.core_network_domain.model.movie.history.HistorySearch
import com.example.core_ui.animation.ImageShimmer
import com.example.core_ui.ui.theme.secondaryBackground
import kotlinx.serialization.ExperimentalSerializationApi

@ExperimentalMaterialApi
@ExperimentalSerializationApi
@Composable
internal fun HistorySearchView(
    historySearch: Response<HistorySearch>,
    search:(String) -> Unit
){
    if (historySearch !is Response.Error){

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "History Search",
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
            if (
                historySearch is Response.Success
            ){
                if (historySearch.data!!.items.isNotEmpty()){
                    items(historySearch.data!!.items){ item ->
                        Card(
                            modifier = Modifier.padding(5.dp),
                            shape = AbsoluteRoundedCornerShape(10.dp),
                            elevation = 8.dp,
                            onClick = {
                                search(item.title)
                            }
                        ) {
                            Column {
                                Text(
                                    text = item.title,
                                    modifier = Modifier.padding(5.dp)
                                )

                                Text(
                                    text = item.date,
                                    modifier = Modifier.padding(5.dp)
                                )
                            }
                        }
                    }
                }else{
                    item {
                        Card(
                            modifier = Modifier.padding(5.dp),
                            shape = AbsoluteRoundedCornerShape(10.dp),
                            elevation = 8.dp
                        ) {
                            Column {
                                Text(
                                    text = "Search history null",
                                    modifier = Modifier.padding(5.dp)
                                )
                            }
                        }
                    }
                }
            }else {
                items(4){
                    ImageShimmer(
                        modifier = Modifier
                            .padding(5.dp)
                            .clip(AbsoluteRoundedCornerShape(10.dp))
                    )
                }
            }
        })
    }
}