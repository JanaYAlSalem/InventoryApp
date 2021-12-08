package com.example.inventory.viewModel

import androidx.lifecycle.*
import com.example.inventory.database.entity.Item
import com.example.inventory.database.entity.ItemDao
import kotlinx.coroutines.launch

// create a view model class -> take a single parameter of type ItemDao
class InventoryViewModel(private val itemDao: ItemDao) : ViewModel() {

    //  for the items from the database
    val allItems: LiveData<List<Item>> = itemDao.getItems().asLiveData()

    // function to add new item by using insertItem fun. from getNewItemEntry fun
    fun addNewItem(itemName: String, itemPrice: String, itemCount: String) {
        val newItem = getNewItemEntry(itemName, itemPrice, itemCount)
        insertItem(newItem)
    }

    // function to inserts the new Item into database.
    private fun insertItem(item: Item) {
        viewModelScope.launch { itemDao.insert(item) }
    }

     // return boolean of EditTexts state (empty-not empty)
    fun isEntryValid(itemName: String, itemPrice: String, itemCount: String): Boolean {
        if (itemName.isBlank() || itemPrice.isBlank() || itemCount.isBlank()) {
            return false // if it empty
        }
        return true // if it not empty
    }

    // get columns values and return it as ITEM
    private fun getNewItemEntry(itemName: String, itemPrice: String, itemCount: String): Item {
        return Item(itemName = itemName, itemPrice = itemPrice.toDouble(), quantityInStock = itemCount.toInt())
    }
}

// create a factory it is inherits from ViewModelProvider.Factory.
class InventoryViewModelFactory(private val itemDao: ItemDao) : ViewModelProvider.Factory {

    // override a method called create() that returns a BusScheduleViewModelFactory
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(InventoryViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return InventoryViewModel(itemDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

} // end factory class
