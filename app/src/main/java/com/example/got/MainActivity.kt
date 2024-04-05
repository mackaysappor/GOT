package com.example.got

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.ActionBar
import androidx.fragment.app.Fragment
import com.example.got.databinding.ActivityMainBinding
import com.example.got.fragments.*


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        replaceFragments(HomeFragment())
        loadViewElements()
        binding.bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.home -> replaceFragments(HomeFragment())
                R.id.books -> replaceFragments(BooksFragment())
                R.id.houses -> replaceFragments(HouseFragment())
                R.id.characters -> replaceFragments(CharacterFragment())

                else -> {}
            }
            true
        }
    }

    private fun replaceFragments(fragment: Fragment){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.main_frame_layout, fragment)
        fragmentTransaction.commit()
    }

    fun loadViewElements(){
        //btnClick = findViewById(R.id.btn_click)
    }


}