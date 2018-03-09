package com.example.cesartorres.testmvpbus.presentation.ui.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.cesartorres.testmvpbus.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val intent = TestActivity.newIntent(this)
        startActivity(intent)
    }
}
