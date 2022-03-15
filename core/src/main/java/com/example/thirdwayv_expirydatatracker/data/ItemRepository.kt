package com.example.thirdwayv_expirydatatracker.data

import com.example.thirdwayv_expirydatatracker.domain.Item

class ItemRepository(private val itemDataSource: ItemDataSource) {

    suspend fun addScannedItem(item: Item) = itemDataSource.addItem(item)

    suspend fun moveToExpired(item: Item) = itemDataSource.moveToExpired(item)

    suspend fun getItems(items: List<Item>?) = itemDataSource.getItems()
}