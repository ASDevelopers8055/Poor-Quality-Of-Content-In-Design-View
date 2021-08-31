package com.example.tiptime

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.tiptime.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.calculateButton.setOnClickListener{ code() }
        binding.costOfServiceEditText.setOnKeyListener { view, keyCode, _ ->
            handleKeyEvent(
                    view,
                    keyCode
            )
        }
        binding.tipResult.text = getString(R.string.tip_amount, "")
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun code() {
        val stringInTextField = binding.costOfServiceEditText.text.toString()
        val output = when (binding.tipOptions.checkedRadioButtonId) {
            R.id.option_twenty_percent -> fullEncode(stringInTextField, 22)
            R.id.option_eighteen_percent -> fullDecode(stringInTextField, 22)
            else -> fullEncode(stringInTextField, 22)
        }
        binding.tipResult.text = getString(R.string.tip_amount, output)
    }

    private fun handleKeyEvent(view: View, keyCode: Int): Boolean {
        if (keyCode == KeyEvent.KEYCODE_ENTER) {
            val inputMethodManager =
                getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
            return true
        }
        return false
    }
}