package com.example.thirdwayv_expirydatatracker.framework

import android.app.Application
import com.example.thirdwayv_expirydatatracker.data.ExpiredItemRepository
import com.example.thirdwayv_expirydatatracker.data.ItemRepository
import com.example.thirdwayv_expirydatatracker.interactors.*

class ExpiryDateTrackerApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        val expiryRepository = ExpiredItemRepository(RoomExpiredItemDataSource(this))
        val itemRepository = ItemRepository(RoomItemDataSource(this))
        ExpiryTrackerViewModelFactory.inject(
            this,
            Interactors(
                AddExpiredItem(expiryRepository),
                AddScannedItem(itemRepository),
                MoveItemToExpired(itemRepository),
                GetExpiredItems(expiryRepository),
                GetScannedItems(itemRepository)
            )
        )
    }
}