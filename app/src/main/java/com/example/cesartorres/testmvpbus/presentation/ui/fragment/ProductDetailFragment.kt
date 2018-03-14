package com.example.cesartorres.testmvpbus.presentation.ui.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.cesartorres.testmvpbus.R

/**
 * Created by cesar.torres on 3/12/2018.
 */
class ProductDetailFragment : Fragment() {

    companion object {
        fun newInstances () : ProductDetailFragment{
            return ProductDetailFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_user_detail,container,false)
    }

}