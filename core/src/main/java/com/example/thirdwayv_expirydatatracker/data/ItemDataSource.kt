package com.example.thirdwayv_expirydatatracker.data

import com.example.thirdwayv_expirydatatracker.domain.Item

interface ItemDataSource {

    suspend fun addItem(item: Item)

    suspend fun getItems(): List<Item>

    suspend fun moveToExpired(item: Item)

    suspend fun setExpireDate(item: Item, counter: Int)
}