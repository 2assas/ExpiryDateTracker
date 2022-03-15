package com.example.thirdwayv_expirydatatracker.framework

import com.example.thirdwayv_expirydatatracker.interactors.*

data class Interactors(
    val addExpiredItem: AddExpiredItem,
    val addScannedItem: AddScannedItem,
    val moveItemToExpired: MoveItemToExpired,
    val getExpiredItems: GetExpiredItems,
    val getScannedItems: GetScannedItems,
)