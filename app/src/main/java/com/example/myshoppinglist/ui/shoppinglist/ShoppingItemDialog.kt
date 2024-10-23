package com.example.myshoppinglist.ui.shoppinglist

import android.content.Context
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatDialog
import com.example.myshoppinglist.R
import com.example.myshoppinglist.data.db.entities.ShoppingItem
import com.example.myshoppinglist.databinding.ActivityShoppingBinding.*
import com.example.myshoppinglist.databinding.DialogAddShoppingItemBinding


class ShoppingItemDialog(context: Context, var addDialogListener: AddDialogListener):AppCompatDialog(context) {
    private lateinit var binding: DialogAddShoppingItemBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DialogAddShoppingItemBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvAdd.setOnClickListener {
            val name = binding.etName.text.toString()
            val amount = binding.etAmount.text.toString()

            if (name.isEmpty() || amount.isEmpty()) {
                Toast.makeText(context, "Please enter all the information", Toast.LENGTH_SHORT)
                    .show()
            }
            val item = ShoppingItem(name, amount.toInt())
            addDialogListener.onAddButtonClicked(item)
            dismiss()
        }

        binding.tvCancel.setOnClickListener {
            cancel()
        }

    }
}