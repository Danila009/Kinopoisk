package com.example.core_ui.view

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.core_ui.ui.theme.Shapes
import com.example.core_ui.R
import com.example.core_ui.ui.theme.secondaryBackground

@Composable
fun BaseButtonView(
    modifier: Modifier = Modifier,
    text: String,
    shape: Shape = AbsoluteRoundedCornerShape(10.dp),
    elevation:ButtonElevation = ButtonDefaults.elevation(),
    onClick: () -> Unit
) {
    Button(
        modifier = modifier
            .padding(5.dp),
        onClick = onClick,
        shape = shape,
        elevation = elevation,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = secondaryBackground
        )
    ) {
        Text(text = text)
    }
}

@Composable
fun GoogleButton(
    modifier: Modifier = Modifier,
    clickedClickable:MutableState<Boolean>,
    text: String = "Sign Up with Google",
    loadingText: String = "Creating Account...",
    icon: Int = R.drawable.google_logo,
    shape: Shape = Shapes.medium,
    borderColor: Color = Color.LightGray,
    backgroundColor: Color = MaterialTheme.colors.surface,
    progressIndicatorColor: Color = MaterialTheme.colors.primary,
    onClicked: () -> Unit
) {

    Surface(
        modifier = modifier
            .clickable {
                clickedClickable.value = !clickedClickable.value
            },
        shape = shape,
        border = BorderStroke(width = 1.dp, color = borderColor),
        color = backgroundColor
    ) {
        Row(
            modifier = Modifier
                .padding(
                    start = 12.dp,
                    end = 16.dp,
                    top = 12.dp,
                    bottom = 12.dp
                )
                .animateContentSize(
                    animationSpec = tween(
                        durationMillis = 300,
                        easing = LinearOutSlowInEasing
                    )
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                painter = painterResource(id = icon),
                contentDescription = "Google Button",
                tint = Color.Unspecified
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = if (clickedClickable.value) loadingText else text)
            if (clickedClickable.value) {
                Spacer(modifier = Modifier.width(16.dp))
                CircularProgressIndicator(
                    modifier = Modifier
                        .height(16.dp)
                        .width(16.dp),
                    strokeWidth = 2.dp,
                    color = progressIndicatorColor
                )
                onClicked()
            }
        }
    }
}