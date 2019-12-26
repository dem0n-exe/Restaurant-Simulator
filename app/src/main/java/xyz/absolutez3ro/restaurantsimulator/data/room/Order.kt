package xyz.absolutez3ro.restaurantsimulator.data.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "orders_tbl")
data class Order(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val item: String,
    val amount: String,
    @ColumnInfo(name = "extra_instructions", defaultValue = "") val instruction: String
)