package com.example.thirdwayv_expirydatatracker.data

import com.example.thirdwayv_expirydatatracker.domain.ExpiredItem
import com.example.thirdwayv_expirydatatracker.domain.Item

class ExpiredItemRepository(private val dataSource: ExpiredItemDataSource) {
    suspend fun addExpiredItem(item: Item, expiredItem: ExpiredItem) =
        dataSource.addToExpiredItems(item, expiredItem)

    suspend fun getExpiredItems(item: List<Item>) = dataSource.getExpiredItems(item)

}