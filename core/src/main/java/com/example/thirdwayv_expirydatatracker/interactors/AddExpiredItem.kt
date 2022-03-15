package com.example.thirdwayv_expirydatatracker.interactors

import com.example.thirdwayv_expirydatatracker.data.ExpiredItemRepository
import com.example.thirdwayv_expirydatatracker.domain.ExpiredItem

class AddExpiredItem(private val expiredRepository: ExpiredItemRepository) {
    suspend operator fun invoke(expiredItem: ExpiredItem) =
        expiredRepository.addExpiredItem(expiredItem)
}