package com.example.thirdwayv_expirydatatracker.data

import com.example.thirdwayv_expirydatatracker.domain.Item
import org.junit.Test

class ItemRepositoryTest {

    @Test
    fun `GetItems() with non null items then return items list sorted by expire date`() {
        //arrange
        val items = listOf(
            Item(itemExpireDate = "2022-01-26 09:48"),
            Item(itemExpireDate = "2022-01-26 17:48"),
            Item(itemExpireDate = "2022-01-26 06:48"),
            Item(itemExpireDate = "2022-01-26 16:48"),
        )
    }
}