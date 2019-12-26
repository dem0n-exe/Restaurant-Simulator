package xyz.absolutez3ro.restaurantsimulator.data.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface OrderDao {

    @Query("SELECT * FROM orders_tbl ORDER BY id ASC")
    fun getAllOrders(): LiveData<List<Order>>

    @Insert
    fun insertOrder(order: Order)

    @Query("DELETE FROM orders_tbl")
    fun deleteAllOrders()
}