package xyz.absolutez3ro.restaurantsimulator.data.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import xyz.absolutez3ro.restaurantsimulator.data.Repository
import xyz.absolutez3ro.restaurantsimulator.data.room.Order
import xyz.absolutez3ro.restaurantsimulator.data.room.OrderRoomDatabase

class OrderViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = Repository.getInstance(OrderRoomDatabase.getDatabase(application))

    val allOrders = repository.allOrders()

    fun insert(order: Order) = viewModelScope.launch { repository.insertOrder(order) }

    fun delete() = viewModelScope.launch { repository.deleteOrders() }
}