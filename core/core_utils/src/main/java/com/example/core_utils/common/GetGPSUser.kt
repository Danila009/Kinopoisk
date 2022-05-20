package com.example.core_utils.common

import android.annotation.SuppressLint
import android.content.Context
import android.content.Context.LOCATION_SERVICE
import android.location.Location
import android.location.LocationManager
import com.google.android.gms.maps.model.LatLng

@SuppressLint("ServiceCast", "MissingPermission")
fun getGPSUser(
    context:Context,
    permission:Boolean
):LatLng {
    if (permission){
        val lm = context.getSystemService(LOCATION_SERVICE) as LocationManager
        val providers = lm.getProviders(true)
        var location: Location? = null
        for (i in providers.indices.reversed()) {
            location = lm.getLastKnownLocation(providers[i])
            if (location != null) break
        }
        location?.let {
            return LatLng(
                location.latitude,
                location.longitude
            )
        }
    }
    return LatLng(
        0.1,
        0.1
    )
}