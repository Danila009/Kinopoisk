package com.example.core_network_domain.repository

import com.example.core_network_domain.model.movie.staff.StaffInfo
import com.example.core_network_domain.model.person.Person
import retrofit2.Response

interface PersonRepository {

    suspend fun getSearchPerson(
        name:String,
        page:Int
    ):Person

    suspend fun getStaffInfo(
        id:Int
    ):Response<StaffInfo>
}