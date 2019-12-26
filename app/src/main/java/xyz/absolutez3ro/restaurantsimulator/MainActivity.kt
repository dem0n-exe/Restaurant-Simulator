package xyz.absolutez3ro.restaurantsimulator

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupViews()
    }

    private fun setupViews() {
        findViewById<MaterialButton>(R.id.button_createOrder).setOnClickListener {
            startActivity(Intent(this, NewOrderActivity::class.java))
        }
    }
}
