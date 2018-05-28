package com.example.cesartorres.testmvpbus.presentation.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.cesartorres.testmvpbus.R
import com.example.cesartorres.testmvpbus.presentation.ui.fragment.ProductDetailFragment
import com.example.cesartorres.testmvpbus.presentation.ui.fragment.ProductListFragment

/**
 * Created by cesar.torres on 3/9/2018.
 */
class ProductListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)
        initFragments()
    }

    private fun initFragments() {
        var productListFragment = ProductListFragment.newInstance()
        supportFragmentManager.beginTransaction().replace(R.id.fl_left_layout,productListFragment).addToBackStack(null).commit()

        var productDetailFragment = ProductDetailFragment.newInstance()
        supportFragmentManager.beginTransaction().replace(R.id.fl_right_layout,productDetailFragment).addToBackStack(null).commit()
    }

    companion object {
        fun newIntent(context: Context): Intent{
            val intent = Intent(context, ProductListActivity :: class.java)
            return intent
        }
    }

    override fun onBackPressed() {
        finish()
    }

}