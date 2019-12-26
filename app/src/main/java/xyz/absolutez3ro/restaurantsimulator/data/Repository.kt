package xyz.absolutez3ro.restaurantsimulator.data

import androidx.lifecycle.LiveData
import xyz.absolutez3ro.restaurantsimulator.data.room.Order
import xyz.absolutez3ro.restaurantsimulator.data.room.OrderRoomDatabase

class Repository private constructor(private val database: OrderRoomDatabase) {


    companion object {
        @Volatile
        private var INSTANCE: Repository? = null

        fun getInstance(database: OrderRoomDatabase): Repository {
            val tempInstance = INSTANCE
            if (tempInstance != null) return tempInstance
            synchronized(this) {
                val newInstance = Repository(database)
                INSTANCE = newInstance
                return newInstance
            }
        }
    }

    fun allOrders(): LiveData<List<Order>>? = database.orderDao().getAllOrders()

    suspend fun insertOrder(order: Order) {
        database.orderDao().insertOrder(order)
    }

    suspend fun deleteOrders() {
        database.orderDao().deleteAllOrders()
    }
}