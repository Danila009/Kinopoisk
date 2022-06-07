package com.example.core_network_domain.useCase.IMDb

import com.example.core_network_domain.apiResponse.BaseApiResponse
import com.example.core_network_domain.common.Response
import com.example.core_network_domain.model.IMDb.externalSite.ExternalSite
import com.example.core_network_domain.repository.IMDbRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetExternalSitesUseCase @Inject constructor(
    private val imDbRepository: IMDbRepository
):BaseApiResponse() {

    operator fun invoke(id:String):Flow<Response<ExternalSite>> = flow {
        emit( safeApiCall { imDbRepository.getExternalSites(id) } )
    }
}