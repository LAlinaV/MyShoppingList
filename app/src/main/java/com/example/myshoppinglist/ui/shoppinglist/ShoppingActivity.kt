package com.example.myshoppinglist.ui.shoppinglist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myshoppinglist.R
import com.example.myshoppinglist.data.db.ShoppingDatabase
import com.example.myshoppinglist.data.db.entities.ShoppingItem
import com.example.myshoppinglist.data.repositories.ShoppingRepository
import com.example.myshoppinglist.databinding.ActivityShoppingBinding
import com.example.myshoppinglist.databinding.DialogAddShoppingItemBinding
import com.example.myshoppinglist.other.ShoppingItemAdapter
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class ShoppingActivity : AppCompatActivity(), KodeinAware {
    override val kodein by kodein()
    private val factory: ShoppingViewModelFactory by instance()
    private lateinit var binding: ActivityShoppingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShoppingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val viewModel = ViewModelProviders.of(this, factory).get(ShoppingViewModel::class.java)

        val adapter = ShoppingItemAdapter(listOf(), viewModel)
        binding.rvShoppingItems.layoutManager = LinearLayoutManager(this)
        binding.rvShoppingItems.adapter = adapter

        viewModel.getAllShoppingItems().observe(this, Observer{
            adapter.items = it
            adapter.notifyDataSetChanged()
        })
        binding.fab.setOnClickListener {
            ShoppingItemDialog(this,
                object:AddDialogListener {
                    override fun onAddButtonClicked(item: ShoppingItem) {
                        viewModel.upsert(item)
                    }
                }).show()
        }
    }
}