package com.example.aritelxlabs

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.aritelxlabs.fragments.SearchFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        val countriesFragment = SearchFragment()
        fragmentTransaction.replace(R.id.contentFrame, countriesFragment)
        fragmentTransaction.commit()

    }
}
