package xyz.absolutez3ro.restaurantsimulator

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import xyz.absolutez3ro.restaurantsimulator.adapter.OrderAdapter
import xyz.absolutez3ro.restaurantsimulator.data.room.Order
import xyz.absolutez3ro.restaurantsimulator.data.viewmodel.OrderViewModel

class MainActivity : AppCompatActivity() {

    private val newOrderActivityRequestCode = 1

    private lateinit var viewModel: OrderViewModel
    private lateinit var adapter: OrderAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupViews()

        viewModel = ViewModelProvider(this).get(OrderViewModel::class.java)
        viewModel.allOrders?.observe(this, Observer { orders ->
            orders?.let { adapter.setOrders(it) }
        })
    }

    private fun setupViews() {
        findViewById<MaterialButton>(R.id.button_createOrder).setOnClickListener {
            startActivityForResult(
                Intent(this, NewOrderActivity::class.java),
                newOrderActivityRequestCode
            )
        }

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        adapter = OrderAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        val divider =
            DividerItemDecoration(recyclerView.context, DividerItemDecoration.VERTICAL)
        recyclerView.addItemDecoration(divider)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == newOrderActivityRequestCode && resultCode == Activity.RESULT_OK) {
            val bundle = data?.getBundleExtra(NewOrderActivity.EXTRA_REPLY).let {
                val order = Order(
                    0,
                    it!!.getString(NewOrderActivity.ITEM_NAME)!!,
                    it.getString(NewOrderActivity.ITEM_AMOUNT)!!.toInt(),
                    it.getString(NewOrderActivity.ITEM_INSTRUCTIONS)!!
                )
                viewModel.insert(order)
            }
        } else {
            Toast.makeText(this, "Incomplete data", Toast.LENGTH_SHORT).show()
        }
    }
}
