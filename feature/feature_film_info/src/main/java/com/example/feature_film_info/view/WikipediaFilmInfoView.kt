package com.example.feature_film_info.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.core_network_domain.model.IMDb.wikipedia.Wikipedia
import com.example.core_ui.ui.theme.secondaryBackground
import com.example.core_utils.common.parseHtml

@Composable
internal fun WikipediaFilmInfoView(
    wikipedia: Wikipedia
) {
    wikipedia.plotShort?.let { plotShot ->
        Column {
            Text(
                text = "Wikipedia",
                modifier = Modifier.padding(5.dp),
                fontWeight = FontWeight.Bold,
                color = secondaryBackground
            )

            Text(
                text = plotShot.html.parseHtml(),
                modifier = Modifier.padding(5.dp)
            )
        }
    }
}