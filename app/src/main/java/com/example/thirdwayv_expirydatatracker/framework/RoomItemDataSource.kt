package com.example.thirdwayv_expirydatatracker.framework

import android.content.Context
import com.example.thirdwayv_expirydatatracker.data.ItemDataSource
import com.example.thirdwayv_expirydatatracker.domain.Item
import com.example.thirdwayv_expirydatatracker.framework.db.ExpiryTrackerDatabase
import com.example.thirdwayv_expirydatatracker.framework.db.ItemEntity

class RoomItemDataSource(context: Context) : ItemDataSource {

    private val itemDao = ExpiryTrackerDatabase.getInstance(context).itemsDao()

    override suspend fun addItem(item: Item) {
        itemDao.addItem(
            item = ItemEntity(
                item.id,
                item.itemName,
                item.itemCategory,
                item.itemExpireDate
            )
        )
    }

    override suspend fun getItems(): List<Item> {
        return itemDao.getItems()
            .map { Item(it.id, it.itemName, it.itemCategory, it.itemExpireDate) }
    }

    override suspend fun moveToExpired(item: Item) {
        itemDao.removeItem(
            item = ItemEntity(
                item.id,
                item.itemName,
                item.itemCategory,
                item.itemExpireDate
            )
        )
    }
}