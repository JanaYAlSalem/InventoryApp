package com.example.inventory.database.entity

import androidx.room.*
import kotlinx.coroutines.flow.Flow

// step 2 : Define the DAO -> to make the interface usable with Room.
@Dao
/*
* Add a DAO interface for the Class entity
* ClassDao is Similar to the Class
 */
interface ItemDao {

    // Display the All items
    @Query("SELECT * from item ORDER BY name ASC")
    fun getItems(): Flow<List<Item>>

    // Display a item
    @Query("SELECT * from item WHERE id = :id")
    fun getItem(id: Int): Flow<Item>

    // Insert item to database
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(item: Item)

    // Update the item on database
    @Update
    suspend fun update(item: Item)

    // Delete the item on database
    @Delete
    suspend fun delete(item: Item)

}