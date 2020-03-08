package xyz.absolutez3ro.restaurantsimulator.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import xyz.absolutez3ro.restaurantsimulator.data.room.Order
import xyz.absolutez3ro.restaurantsimulator.databinding.OrderItemBinding

class OrderAdapter internal constructor(
    context: Context
) : PagedListAdapter<Order, OrderAdapter.OrderViewHolder>(diffCallback) {

    private val inflater: LayoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        val orderItemBinding = OrderItemBinding.inflate(inflater, parent, false)
        return OrderViewHolder(orderItemBinding)
    }

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        holder.bind(getItem(position))

    }

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<Order>() {
            override fun areContentsTheSame(oldItem: Order, newItem: Order) =
                oldItem == newItem

            override fun areItemsTheSame(oldItem: Order, newItem: Order) =
                oldItem.id == newItem.id
        }
    }

    inner class OrderViewHolder(private val binding: OrderItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(order: Order?) {
            binding.order = order
            binding.executePendingBindings()
        }

    }
}