package xyz.absolutez3ro.restaurantsimulator.data.room

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface OrderDao {

    @Query("SELECT * FROM orders_tbl ORDER BY id ASC")
    fun getAllOrders(): DataSource.Factory<Int, Order>

    @Insert
    fun insertOrder(order: Order)

    @Insert
    fun insertOrder(orders: List<Order>)

    @Query("DELETE FROM orders_tbl")
    fun deleteAllOrders()
}