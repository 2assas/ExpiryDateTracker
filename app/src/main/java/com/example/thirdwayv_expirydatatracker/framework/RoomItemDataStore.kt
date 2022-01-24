package com.example.thirdwayv_expirydatatracker.framework

import android.content.Context
import com.example.thirdwayv_expirydatatracker.data.ItemDataSource
import com.example.thirdwayv_expirydatatracker.domain.Item

class RoomItemDataStore(context: Context) : ItemDataSource {
    override suspend fun addItem(item: Item) {
        TODO("Not yet implemented")
    }

    override suspend fun getItems(): List<Item> {
        TODO("Not yet implemented")
    }

    override suspend fun moveToExpired(item: Item) {
        TODO("Not yet implemented")
    }

    override suspend fun setExpireDate(item: Item, counter: Int) {
        TODO("Not yet implemented")
    }

}