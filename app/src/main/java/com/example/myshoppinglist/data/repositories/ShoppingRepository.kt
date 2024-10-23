package com.example.myshoppinglist.data.repositories

import com.example.myshoppinglist.data.db.ShoppingDatabase
import com.example.myshoppinglist.data.db.entities.ShoppingItem

class ShoppingRepository (
    private val db:ShoppingDatabase
){
    suspend fun upset(item: ShoppingItem) = db.getShoppingDao().upsert(item)
    suspend fun delete(item: ShoppingItem) = db.getShoppingDao().delete(item)
    fun getAllShoppingItems() = db.getShoppingDao().getAllShoppingItems()
}