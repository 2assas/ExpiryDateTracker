package com.example.thirdwayv_expirydatatracker.interactors

import com.example.thirdwayv_expirydatatracker.data.ExpiredItemRepository

class GetExpiredItems(private val expiredItemRepository: ExpiredItemRepository) {
    suspend operator fun invoke() =
        expiredItemRepository.getExpiredItems()?.sortedBy { it.id }
}