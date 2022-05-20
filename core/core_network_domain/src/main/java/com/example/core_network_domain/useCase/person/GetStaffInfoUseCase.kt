package com.example.core_network_domain.useCase.person

import com.example.core_network_domain.apiResponse.BaseApiResponse
import com.example.core_network_domain.common.Response
import com.example.core_network_domain.model.movie.staff.StaffInfo
import com.example.core_network_domain.repository.PersonRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetStaffInfoUseCase @Inject constructor(
    private val personRepository: PersonRepository
):BaseApiResponse() {
    operator fun invoke(id:Int):Flow<Response<StaffInfo>>  = flow {
        emit( safeApiCall { personRepository.getStaffInfo(id) } )
    }
}