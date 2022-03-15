package com.example.thirdwayv_expirydatatracker.framework

import android.content.Context
import com.example.thirdwayv_expirydatatracker.data.ExpiredItemDataSource
import com.example.thirdwayv_expirydatatracker.domain.ExpiredItem
import com.example.thirdwayv_expirydatatracker.domain.Item
import com.example.thirdwayv_expirydatatracker.framework.db.ExpiredEntity
import com.example.thirdwayv_expirydatatracker.framework.db.ExpiryTrackerDatabase

class RoomExpiredItemDataSource(context: Context) : ExpiredItemDataSource {
    private val expiredDao = ExpiryTrackerDatabase.getInstance(context).expiredItemsDao()

    override suspend fun addToExpiredItems(expiredItem: ExpiredItem) {
        expiredDao.addExpiredItem(
            ExpiredEntity(
                expiredItem.id,
                expiredItem.item?.itemName,
                expiredItem.item?.itemCategory,
                expiredItem.item?.itemExpireDate
            )
        )
    }

    override suspend fun getExpiredItems(): List<ExpiredItem> {
        return expiredDao.getExpiredItem().map {
            ExpiredItem(
                it.id,
                Item(it.id, it.itemName, it.itemCategory, it.itemExpireDate)
            )
        }
    }

}