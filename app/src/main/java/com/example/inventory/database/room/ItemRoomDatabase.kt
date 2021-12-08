package com.example.inventory.database.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.inventory.database.entity.Item
import com.example.inventory.database.entity.ItemDao

// Create a Database instance
@Database(entities = [Item::class], version = 1, exportSchema = false)

abstract class ItemRoomDatabase : RoomDatabase() {

    /*
    * Add an abstract function that returns a ItemDao
    * to allows other classes easy access to the DAO classes.
     */
    abstract fun itemDao(): ItemDao


    /*
     * Defined in the companion object to creates the database for the first time.
     */
    companion object {
        @Volatile
        private var INSTANCE: ItemRoomDatabase? = null

        fun getDatabase(context: Context): ItemRoomDatabase {

           return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(context.applicationContext, ItemRoomDatabase::class.java, "item_database")
                    .fallbackToDestructiveMigration().build()
               // to return instance
               INSTANCE = instance
               instance
            }
        } // end getDatabase functions
    } // end Object

} // end ItemRoomDatabase class