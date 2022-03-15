package com.example.thirdwayv_expirydatatracker.data

import com.example.thirdwayv_expirydatatracker.domain.ExpiredItem

interface ExpiredItemDataSource {
    suspend fun addToExpiredItems(expiredItem: ExpiredItem) {}
    suspend fun getExpiredItems(): List<ExpiredItem>? = null
}