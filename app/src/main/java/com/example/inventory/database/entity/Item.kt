package com.example.inventory.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.text.NumberFormat


// step 1: Create an entity -> is to initial a table of database

@Entity
data class Item(@PrimaryKey(autoGenerate = true) val id: Int = 0,
                @ColumnInfo(name = "name") val itemName: String,
                @ColumnInfo(name = "price") val itemPrice: Double,
                @ColumnInfo(name = "quantity") val quantityInStock: Int)

// extension function to get format price
fun Item.getFormattedPrice(): String = NumberFormat.getCurrencyInstance().format(itemPrice)

