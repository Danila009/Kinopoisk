package com.example.feature_film_info.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.items
import com.example.core_network_domain.model.movie.review.ReviewItem
import com.example.core_ui.ui.theme.secondaryBackground
import com.example.core_utils.common.getTime
import com.example.core_utils.common.replaceRange
import com.example.core_utils.navigation.filmNavGraph.filmMoreNavGraph.FilmMoreScreenRoute
import com.example.core_utils.navigation.filmNavGraph.reviewFilmNavGraph.ReviewFilmScreenRoute

@Composable
internal fun ReviewView(
    navController: NavController,
    review: LazyPagingItems<ReviewItem>,
    filmId:String
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "Рецензии:",
            modifier = Modifier.padding(5.dp),
            fontWeight = FontWeight.Bold,
            color = secondaryBackground
        )

        TextButton(
            onClick = { navController.navigate(FilmMoreScreenRoute.ReviewFilmMore.base(filmId = filmId)) },
            modifier = Modifier.padding(5.dp)
        ) {
            Text(
                text = "Все ->",
                color = secondaryBackground
            )
        }
    }

    LazyRow(content = {
        items(review){ item ->
            Card(
                modifier = Modifier
                    .padding(5.dp)
                    .clickable {
                        navController.navigate(
                            ReviewFilmScreenRoute.ReviewDetail.base(
                                reviewId = item?.reviewId.toString(),
                                filmId = filmId
                            )
                        )
                    }
                    .width(250.dp),
                shape = AbsoluteRoundedCornerShape(20.dp)
            ) {
                Column {
                    Row {
                        Text(
                            text = item?.reviewAutor.toString(),
                            modifier = Modifier.padding(5.dp),
                            color = secondaryBackground
                        )
                        Text(
                            text = "+ ${item?.userPositiveRating.toString()}",
                            color = Color.Green,
                            modifier = Modifier.padding(5.dp)
                        )
                        Text(
                            text = "- ${item?.userNegativeRating.toString()}",
                            color = Color.Red,
                            modifier = Modifier.padding(5.dp)
                        )
                    }
                    Text(
                        text = item?.reviewTitle.toString(),
                        modifier = Modifier.padding(5.dp)
                    )
                    Text(
                        text = replaceRange(item?.reviewDescription.toString(), 230),
                        modifier = Modifier.padding(5.dp)
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ) {
                        item?.reviewData?.let {
                            Text(
                                text = getTime(it),
                                modifier = Modifier.padding(5.dp)
                            )
                        }
                    }
                }
            }
        }
    })
}