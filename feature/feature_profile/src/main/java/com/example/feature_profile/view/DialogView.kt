package com.example.feature_profile.view

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.core_ui.ui.theme.secondaryBackground
import com.example.feature_profile.viewModel.ProfileViewModel
import java.io.ByteArrayOutputStream

@Composable
fun DialogPhotoView(
    checkDialog:MutableState<Boolean>,
    profileViewModel: ProfileViewModel
) {
    if (checkDialog.value){

        val galleryCheck = remember { mutableStateOf(false) }

        val galleryLauncher = selectImage(
            boolean = galleryCheck,
            byteArray = {
                profileViewModel.patchUpdatePhoto(it)
            }
        )

        if (galleryCheck.value){
            galleryLauncher.launch("image/*")
        }

        AlertDialog(
            onDismissRequest = { checkDialog.value = false },
            buttons = {
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
                        onClick = { galleryCheck.value = true }
                    ) {
                        Text(text = "Gallery")
                    }
                }
            },
            shape = AbsoluteRoundedCornerShape(20.dp),
            backgroundColor = Color.White
        )
    }
}

@SuppressLint("NewApi")
@Composable
private fun selectImage(
    boolean: MutableState<Boolean>,
    byteArray:(ByteArray) -> Unit
): ManagedActivityResultLauncher<String, Uri?> {
    val context = LocalContext.current
    val select = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()){
        it?.let { uri: Uri ->
            val sourse = ImageDecoder.createSource(context.applicationContext.contentResolver,uri)
            val bitmap = ImageDecoder.decodeBitmap(sourse)
            val outputStream = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.JPEG, 40, outputStream)
            byteArray(outputStream.toByteArray())
            boolean.value = false
        }
    }
    return select
}