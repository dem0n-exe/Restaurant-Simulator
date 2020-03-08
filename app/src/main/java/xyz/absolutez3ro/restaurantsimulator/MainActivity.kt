package xyz.absolutez3ro.restaurantsimulator

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import xyz.absolutez3ro.restaurantsimulator.adapter.OrderAdapter
import xyz.absolutez3ro.restaurantsimulator.data.room.Order
import xyz.absolutez3ro.restaurantsimulator.data.viewmodel.OrderViewModel
import xyz.absolutez3ro.restaurantsimulator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val newOrderActivityRequestCode = 1

    private lateinit var viewModel: OrderViewModel
    private lateinit var adapter: OrderAdapter
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        adapter = OrderAdapter(this)

        viewModel = ViewModelProvider(this).get(OrderViewModel::class.java)
        viewModel.allOrders?.observe(this, Observer { orders ->
            orders?.let {
                if (it.isNotEmpty()) binding.textNoResult.visibility = View.GONE
                else binding.textNoResult.visibility = View.VISIBLE
                adapter.setOrders(it)
            }
        })

        setupViews()

    }

    private fun setupViews() {
        binding.buttonCreateOrder.setOnClickListener {
            startActivityForResult(
                Intent(this, NewOrderActivity::class.java),
                newOrderActivityRequestCode
            )
        }


        binding.buttonClear.setOnClickListener {
            viewModel.delete()
        }

        val recyclerView = binding.recyclerView
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        val divider =
            DividerItemDecoration(recyclerView.context, DividerItemDecoration.VERTICAL)
        recyclerView.addItemDecoration(divider)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == newOrderActivityRequestCode && resultCode == Activity.RESULT_OK) {
            data?.getBundleExtra(NewOrderActivity.EXTRA_REPLY).let {
                val order = Order(
                    0,
                    it!!.getString(NewOrderActivity.ITEM_NAME)!!,
                    it.getString(NewOrderActivity.ITEM_AMOUNT)!!,
                    it.getString(NewOrderActivity.ITEM_INSTRUCTIONS)!!
                )
                viewModel.insert(order)
            }
        }
    }
}
