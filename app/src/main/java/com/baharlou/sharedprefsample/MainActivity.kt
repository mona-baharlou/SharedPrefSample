package com.baharlou.sharedprefsample

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
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

        showDataIfExist()

        submitClicked()
    }

    private fun showDataIfExist() {
        val name = sharedPref.getString("name", "")
        val email = sharedPref.getString("email", "")
        val gender = sharedPref.getBoolean("isMale", false)


        binding.txtName.setText(name)
        binding.txtEmail.setText(email)

        if (gender) {
            binding.radioMale.isChecked = true
        } else {
            binding.radioFemale.isChecked = true

        }

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

            Toast.makeText(this, "Data Saved ", Toast.LENGTH_SHORT).show()
        }
    }
}