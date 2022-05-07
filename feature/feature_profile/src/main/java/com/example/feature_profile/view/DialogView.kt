package com.example.feature_profile.view

import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.unit.dp
import com.example.core_ui.ui.theme.secondaryBackground

@Composable
fun DialogPhotoView(
    checkDialog:MutableState<Boolean>
) {
    if (checkDialog.value){

        AlertDialog(
            onDismissRequest = { checkDialog.value = false },
            buttons = {
                Column {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Button(
                            modifier = Modifier.padding(5.dp),
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = secondaryBackground
                            ), shape = AbsoluteRoundedCornerShape(8.dp),
                            onClick = {

                            }
                        ) {
                            Text(text = "Camera")
                        }
                        Button(
                            modifier = Modifier.padding(5.dp),
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = secondaryBackground
                            ), shape = AbsoluteRoundedCornerShape(8.dp),
                            onClick = {}
                        ) {
                            Text(text = "Gallery")
                        }
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Button(
                            modifier = Modifier.padding(5.dp),
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = secondaryBackground
                            ), shape = AbsoluteRoundedCornerShape(8.dp),
                            onClick = {
                                checkDialog.value = false
                            }
                        ) {
                            Text(text = "Close")
                        }

                        Button(
                            modifier = Modifier.padding(5.dp),
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = secondaryBackground
                            ), shape = AbsoluteRoundedCornerShape(8.dp),
                            onClick = {
                                checkDialog.value = false
                            }
                        ) {
                            Text(text = "Save")
                        }
                    }
                }
            },
            text = {

            }, title = {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
//                    Image(
//                        bitmap = bitmap.value.asImageBitmap(),
//                        contentDescription = null,
//                        modifier = Modifier
//                            .size(150.dp)
//                            .padding(5.dp)
//                    )
                }
            },
            shape = AbsoluteRoundedCornerShape(20.dp),
            backgroundColor = Color.White
        )
    }
}