package com.example.thirdwayv_expirydatatracker.data

import com.example.thirdwayv_expirydatatracker.domain.Item

interface ItemDataSource {

    suspend fun addItem(item: Item) {}

    suspend fun getItems(): List<Item>? = null

    suspend fun moveToExpired(item: Item) {}

}