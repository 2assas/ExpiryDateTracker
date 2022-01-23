package com.example.thirdwayv_expirydatatracker.data

import com.example.thirdwayv_expirydatatracker.domain.ExpiredItem
import com.example.thirdwayv_expirydatatracker.domain.Item

interface ExpiredItemDataSource {
    suspend fun addToExpiredItems(item: Item, expiredItem: ExpiredItem)

    suspend fun getExpiredItems(item: List<Item>): List<ExpiredItem>
}