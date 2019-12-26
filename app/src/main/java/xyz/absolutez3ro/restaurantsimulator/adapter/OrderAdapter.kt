package xyz.absolutez3ro.restaurantsimulator.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import xyz.absolutez3ro.restaurantsimulator.data.room.Order
import xyz.absolutez3ro.restaurantsimulator.databinding.OrderItemBinding

class OrderAdapter internal constructor(
    context: Context
) : RecyclerView.Adapter<OrderAdapter.OrderViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var orders = emptyList<Order>()

    inner class OrderViewHolder(private val binding: OrderItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(order: Order) {
            binding.order = order
            binding.executePendingBindings()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        val orderItemBinding = OrderItemBinding.inflate(inflater, parent, false)
        return OrderViewHolder(orderItemBinding)
    }

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        val current = orders[position]
        holder.bind(current)

    }

    override fun getItemCount() = orders.size

    internal fun setOrders(newOrderList: List<Order>) {
        this.orders = newOrderList
        notifyDataSetChanged()
    }
}