package xyz.absolutez3ro.restaurantsimulator.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import xyz.absolutez3ro.restaurantsimulator.R
import xyz.absolutez3ro.restaurantsimulator.data.room.Order

class OrderAdapter internal constructor(
    context: Context
) : RecyclerView.Adapter<OrderAdapter.OrderViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var orders = emptyList<Order>()

    inner class OrderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemName: TextView = itemView.findViewById<TextView>(R.id.item_name)
        val itemAmount: TextView = itemView.findViewById<TextView>(R.id.item_amount)
        val itemInstructions: TextView = itemView.findViewById<TextView>(R.id.item_instructions)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        val itemView = inflater.inflate(R.layout.order_item, parent, false)
        return OrderViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        val current = orders[position]
        holder.itemName.text = current.item
        holder.itemAmount.text = current.amount.toString()
        holder.itemInstructions.text = current.instruction

    }

    override fun getItemCount() = orders.size

    internal fun setOrders(newOrderList: List<Order>) {
        this.orders = newOrderList
        notifyDataSetChanged()
    }
}