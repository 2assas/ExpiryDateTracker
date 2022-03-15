package com.example.thirdwayv_expirydatatracker.data

import com.example.thirdwayv_expirydatatracker.domain.ExpiredItem

class ExpiredItemRepository(private val dataSource: ExpiredItemDataSource) {
    suspend fun addExpiredItem(expiredItem: ExpiredItem) =
        dataSource.addToExpiredItems(expiredItem)

    suspend fun getExpiredItems() = dataSource.getExpiredItems()

}