package com.example.thirdwayv_expirydatatracker.interactors

import com.example.thirdwayv_expirydatatracker.data.ItemRepository
import com.example.thirdwayv_expirydatatracker.domain.Item

class MoveItemToExpired(private val itemRepository: ItemRepository) {
    suspend operator fun invoke(item: Item) =
        itemRepository.moveToExpired(item)
}