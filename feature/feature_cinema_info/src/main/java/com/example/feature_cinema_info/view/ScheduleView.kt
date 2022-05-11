package com.example.feature_cinema_info.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.core_network_domain.model.cinema.Schedule
import com.example.core_ui.ui.theme.secondaryBackground

@Composable
fun ScheduleView(
    schedule: Schedule
) {
    Row(
        modifier = Modifier
            .width(220.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = schedule.week.name.lowercase(),
            modifier = Modifier.padding(5.dp),
            color = secondaryBackground
        )
        Text(
            text = "${schedule.startDate} - ${schedule.endDate}",
            modifier = Modifier.padding(5.dp)
        )
    }
}