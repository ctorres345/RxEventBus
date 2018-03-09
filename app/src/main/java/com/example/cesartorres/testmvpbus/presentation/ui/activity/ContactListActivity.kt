package com.example.cesartorres.testmvpbus.presentation.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.cesartorres.testmvpbus.R

/**
 * Created by cesar.torres on 3/9/2018.
 */
class ContactListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)
    }

    companion object {
        fun newIntent(context: Context): Intent{
            val intent = Intent(context, ContactListActivity :: class.java)
            return intent
        }
    }
}