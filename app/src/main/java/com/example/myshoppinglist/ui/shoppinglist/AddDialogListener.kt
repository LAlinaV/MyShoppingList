package com.example.myshoppinglist.ui.shoppinglist

import com.example.myshoppinglist.data.db.entities.ShoppingItem

interface AddDialogListener {
    fun onAddButtonClicked(item: ShoppingItem)
}