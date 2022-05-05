package com.example.core_network_domain.useCase.shop

import com.example.core_network_domain.model.shop.Shop
import com.example.core_network_domain.repository.ShopRepository
import javax.inject.Inject

class GetShopUseCase @Inject constructor(
    private val shopRepository: ShopRepository
){
    suspend operator fun invoke():Shop {
        return shopRepository.getShop()
    }
}