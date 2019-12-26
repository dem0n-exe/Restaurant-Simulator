package xyz.absolutez3ro.restaurantsimulator.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Order::class], version = 1, exportSchema = false)
abstract class OrderRoomDatabase : RoomDatabase() {

    abstract fun orderDao(): OrderDao

    companion object {
        @Volatile
        private var INSTANCE: OrderRoomDatabase? = null

        fun getDatabase(context: Context): OrderRoomDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) return tempInstance
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    OrderRoomDatabase::class.java,
                    "orders_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}