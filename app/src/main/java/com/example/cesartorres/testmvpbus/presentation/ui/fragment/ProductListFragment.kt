package com.example.cesartorres.testmvpbus.presentation.ui.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.cesartorres.testmvpbus.R
import com.example.cesartorres.testmvpbus.presentation.mvp.model.ProductViewModel
import com.example.cesartorres.testmvpbus.presentation.mvp.view.contract.ProductListView
import kotlinx.android.synthetic.main.fragment_list.*

/**
 * Created by cesar.torres on 3/12/2018.
 */
class ProductListFragment : Fragment(),ProductListView {

    companion object {
        fun newInstance () : ProductListFragment {
            return  ProductListFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_list,container)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var layoutManager : LinearLayoutManager = LinearLayoutManager(activity)
        rvList?.layoutManager = layoutManager
    }

    override fun initializeUI() {

    }

    override fun showErrorMessage(errorMessage: String) {
        Toast.makeText(activity,errorMessage,Toast.LENGTH_SHORT).show()
    }

    override fun showNoListMessage() {

    }

    override fun fillProductList(productViewModelList: List<ProductViewModel>) {

    }
}