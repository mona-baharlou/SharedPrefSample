package com.baharlou.sharedprefsample

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
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

        checkFirstRun()

//        showDataIfExist()

        submitClicked()
    }

    private fun checkFirstRun() {
        val editor = sharedPref.edit()
        val firstRun = sharedPref.getBoolean("firstRun", true)
        if (firstRun) {
            //Toast.makeText(this, "first time", Toast.LENGTH_SHORT).show()
            editor.putBoolean("firstRun", false)
            editor.commit()

        } else {
           // Toast.makeText(this, "second time", Toast.LENGTH_SHORT).show()
            showDataIfExist()
        }

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