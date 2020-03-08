package xyz.absolutez3ro.restaurantsimulator.data

import androidx.lifecycle.LiveData
import androidx.paging.Config
import androidx.paging.PagedList
import androidx.paging.toLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
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

    fun allOrders(): LiveData<PagedList<Order>>? = database.orderDao().getAllOrders().toLiveData(
        Config(
            pageSize = 10,
            enablePlaceholders = false,
            maxSize = 50
        )
    )

    suspend fun insertOrder(order: Order) = withContext(Dispatchers.IO) {
        database.orderDao().insertOrder(order)
    }

    suspend fun insertOrder(orders: List<Order>) = withContext(Dispatchers.IO) {
        database.orderDao().insertOrder(orders)
    }

    suspend fun deleteOrders() = withContext(Dispatchers.IO) {
        database.orderDao().deleteAllOrders()
    }
}