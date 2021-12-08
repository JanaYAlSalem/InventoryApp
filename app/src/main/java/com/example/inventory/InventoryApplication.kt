package com.example.inventory

import android.app.Application
import com.example.inventory.database.room.ItemRoomDatabase

// call database as lazily created when you first need/access the reference
class InventoryApplication : Application() {
    val database: ItemRoomDatabase by lazy { ItemRoomDatabase.getDatabase(this) }
}
