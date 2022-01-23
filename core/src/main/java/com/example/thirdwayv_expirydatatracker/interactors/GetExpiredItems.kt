package com.example.thirdwayv_expirydatatracker.interactors

import com.example.thirdwayv_expirydatatracker.data.ExpiredItemRepository
import com.example.thirdwayv_expirydatatracker.domain.Item

class GetExpiredItems(private val expiredItemRepository: ExpiredItemRepository) {
    suspend operator fun invoke(items: List<Item>) =
        expiredItemRepository.getExpiredItems(items).sortedBy { it.id }
}