package com.example.feature_comics_info.view.marvel

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.items
import com.example.core_network_domain.model.marvel.character.Result
import com.example.core_ui.view.Image

@Composable
internal fun Characters(
    characters: LazyPagingItems<Result>
) {
    Text(
        text = "Characters",
        modifier = Modifier.padding(5.dp),
        fontWeight = FontWeight.W900
    )

    LazyRow(content = {
        items(characters){ item -> item?.let { CharacterItem(item) } }
    })
}

@Composable
private fun CharacterItem (
    character:Result
) {
    Column(
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            url = "${character.thumbnail.path}.${character.thumbnail.extension}",
            modifier = Modifier
                .size(100.dp, 200.dp)
                .padding(5.dp)
                .clip(AbsoluteRoundedCornerShape(15.dp))
        )

        Text(
            text = character.name,
            modifier = Modifier.padding(5.dp),
            fontWeight = FontWeight.W900
        )
    }
}