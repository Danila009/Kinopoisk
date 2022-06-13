package com.example.core_network_domain.useCase.translate

import com.example.core_network_domain.enums.TranslateState
import com.google.mlkit.common.model.DownloadConditions
import com.google.mlkit.nl.translate.TranslateLanguage
import com.google.mlkit.nl.translate.Translation
import com.google.mlkit.nl.translate.TranslatorOptions
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class TranslateText {

    operator fun invoke(
        text:String,
        translateState: TranslateState
    ):Flow<String> = callbackFlow{

        val options = when(translateState){
            TranslateState.RUSSIAN_ENGLISH -> {
                TranslatorOptions.Builder()
                    .setSourceLanguage(TranslateLanguage.RUSSIAN)
                    .setTargetLanguage(TranslateLanguage.ENGLISH)
                    .build()
            }
            TranslateState.ENGLISH_RUSSIAN -> {
                TranslatorOptions.Builder()
                    .setSourceLanguage(TranslateLanguage.ENGLISH)
                    .setTargetLanguage(TranslateLanguage.RUSSIAN)
                    .build()
            }
        }

        val englishGermanTranslator = Translation.getClient(options)

        val conditions = DownloadConditions.Builder()
            .requireWifi()
            .build()

        englishGermanTranslator.downloadModelIfNeeded(conditions)
            .addOnSuccessListener {
                englishGermanTranslator.translate(text)
                    .addOnSuccessListener { translatedText ->
                        trySend(translatedText)
                    }
            }
            .addOnFailureListener {
                trySend(text)
            }


        awaitClose {
            englishGermanTranslator.close()
        }
    }
}