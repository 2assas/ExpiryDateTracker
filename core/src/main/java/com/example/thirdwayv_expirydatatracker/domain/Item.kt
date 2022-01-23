package com.example.thirdwayv_expirydatatracker.domain

import java.io.Serializable
import java.util.*

data class Item(
    val id: Int?,
    val itemName: String?,
    val itemCategory: String?,
    val itemExpireDate: Date?,
) : Serializable {
    companion object {
        val EMPTY = Item(null, null, null, null)
    }
}