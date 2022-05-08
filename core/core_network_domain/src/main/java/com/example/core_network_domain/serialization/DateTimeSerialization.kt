package com.example.core_network_domain.serialization

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializer
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import java.text.SimpleDateFormat

@ExperimentalSerializationApi
@Serializer(forClass = String::class)
internal object DateTimeSerialization : KSerializer<String> {

    @Suppress("SimpleDateFormat")
    private val df = SimpleDateFormat("dd-MM-yyyy")

    override fun deserialize(decoder: Decoder): String {
        return df.parse(decoder.decodeString()).toString()
    }
    override fun serialize(encoder: Encoder, value: String) {
        encoder.encodeString(df.format(value)).toString()
    }
}