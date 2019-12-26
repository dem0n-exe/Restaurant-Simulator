package xyz.absolutez3ro.restaurantsimulator

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.textfield.TextInputEditText
import kotlinx.android.synthetic.main.activity_add_order.*

class NewOrderActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_order)
        setupViews()
    }

    private fun setupViews() {
        val itemName = findViewById<TextInputEditText>(R.id.input_item_name)
        val itemAmount = findViewById<TextInputEditText>(R.id.input_item_amount)
        val itemInstructions = findViewById<TextInputEditText>(R.id.input_item_instructions)
        val save = findViewById<FloatingActionButton>(R.id.floating_action_button_save)

        save.setOnClickListener {
            val replyIntent = Intent()

            if (!emptyTextFields()) {
                val bundle = Bundle().apply {
                    putString(ITEM_NAME, itemName.text.toString())
                    putString(ITEM_AMOUNT, itemAmount.text.toString())
                    putString(ITEM_INSTRUCTIONS, itemInstructions.text.toString())
                }
                replyIntent.putExtra(EXTRA_REPLY, bundle)
                setResult(Activity.RESULT_OK, replyIntent)
                finish()
            }
        }

    }

    private fun emptyTextFields(): Boolean {
        var isEmpty = false
        if (input_item_name.text!!.isEmpty()) {
            inputLayout_item_name.error = getString(R.string.error_name)
            isEmpty = true
        } else inputLayout_item_name.error = null

        if (input_item_amount.text!!.isEmpty()) {
            inputLayout_item_amount.error = getString(R.string.error_amount)
            isEmpty = true
        } else inputLayout_item_amount.error = null

        if (input_item_instructions.text!!.isEmpty()) {
            inputLayout_item_instructions.error = getString(R.string.error_instruction)
            isEmpty = true
        } else inputLayout_item_instructions.error = null

        return isEmpty

    }

    companion object {
        const val EXTRA_REPLY = "xyz.absolute3ro.restaurantsimulator.REPLY"
        const val ITEM_NAME = "ITEM_NAME"
        const val ITEM_AMOUNT = "ITEMAMOUNT"
        const val ITEM_INSTRUCTIONS = "ITEM_INSTRUCTIONS"
    }
}