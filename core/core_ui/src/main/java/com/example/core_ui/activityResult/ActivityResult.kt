package com.example.core_ui.activityResult

import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContract
import androidx.compose.runtime.Composable
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task

@Composable
fun activityResultAuthGoogle(
    account:(GoogleSignInAccount?) -> Unit,
    contract: ActivityResultContract<Int, Task<GoogleSignInAccount>?>,
    error:(String) -> Unit
): ManagedActivityResultLauncher<Int, Task<GoogleSignInAccount>?> {
    return rememberLauncherForActivityResult(contract = contract){ task ->
        try {
            account(task?.getResult(ApiException::class.java))
        }catch (e:Exception){
            error(e.message.toString())
        }
    }
}