package com.example.thirdwayv_expirydatatracker.domain

import java.io.Serializable

data class Item(
    val id: Int? = 0,
    val itemName: String? = "",
    val itemCategory: String? = "",
    val itemExpireDate: String? = "",
) : Serializable {
    companion object {
        val EMPTY = Item(null, null, null, null)
    }
}