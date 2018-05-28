package com.example.cesartorres.testmvpbus.presentation.ui.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.cesartorres.testmvpbus.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initUI()
    }

    private fun initUI() {
        btnContactList.setOnClickListener({
            val intent = ContactListActivity.newIntent(this)
            startActivity(intent)
        })

        btnProductList.setOnClickListener({
            val intent = ProductListActivity.newIntent(this)
            startActivity(intent)
        })
    }

}
