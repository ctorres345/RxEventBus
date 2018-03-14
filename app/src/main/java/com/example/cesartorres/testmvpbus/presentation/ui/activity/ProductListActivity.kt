package com.example.cesartorres.testmvpbus.presentation.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.cesartorres.testmvpbus.R

/**
 * Created by cesar.torres on 3/9/2018.
 */
class ProductListActivity : AppCompatActivity() {

    companion object {
        fun newIntent(context: Context): Intent{
            val intent = Intent(context, ProductListActivity :: class.java)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)
    }


}