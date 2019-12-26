package xyz.absolutez3ro.restaurantsimulator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.button.MaterialButton
import kotlinx.android.synthetic.main.activity_main.*

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
