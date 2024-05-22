package com.baharlou.sharedprefsample

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.baharlou.sharedprefsample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var sharedPref: SharedPreferences
    private val SHARED_DATA = "data"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPref = getSharedPreferences(SHARED_DATA, Context.MODE_PRIVATE)

        submitClicked()
    }

    private fun submitClicked() {


        binding.btnSubmit.setOnClickListener {

            val editor = sharedPref.edit()
            val name = binding.txtName.text.toString()
            val email = binding.txtEmail.text.toString()

            //save to sharedPref
            //apply save to another thread
            //commit save to this thread--> not optimize
            //sharedPref.edit().putString("name", name).apply()
            // sharedPref.edit().putString("email", email).apply()

            editor.putString("name", name)
            editor.putString("email", email)

            if (binding.radioMale.isChecked) {
                editor.putBoolean("isMale", true)
                //sharedPref.edit().putBoolean("isMale", true).apply()
            } else {
                editor.putBoolean("isMale", false)
                //sharedPref.edit().putBoolean("isMale", false).apply()
            }

            editor.apply()
        }
    }
}