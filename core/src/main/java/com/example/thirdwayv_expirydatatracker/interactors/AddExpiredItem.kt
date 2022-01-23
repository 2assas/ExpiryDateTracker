package com.example.thirdwayv_expirydatatracker.interactors

import com.example.thirdwayv_expirydatatracker.data.ExpiredItemRepository
import com.example.thirdwayv_expirydatatracker.domain.ExpiredItem
import com.example.thirdwayv_expirydatatracker.domain.Item

class AddExpiredItem(private val expiredRepository: ExpiredItemRepository) {
    suspend operator fun invoke(item: Item, expiredItem: ExpiredItem) =
        expiredRepository.addExpiredItem(item, expiredItem)
}