package com.example.thirdwayv_expirydatatracker.interactors

import com.example.thirdwayv_expirydatatracker.data.ItemRepository

class GetScannedItems(private val itemRepository: ItemRepository) {
    suspend operator fun invoke() =
        itemRepository.getItems(null)?.sortedBy { it.itemExpireDate }
}