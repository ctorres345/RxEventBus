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
import com.example.cesartorres.testmvpbus.presentation.mvp.presenter.ProductListPresenter
import com.example.cesartorres.testmvpbus.presentation.mvp.view.contract.ProductListView
import com.example.cesartorres.testmvpbus.presentation.ui.adapter.ProductListAdapter
import kotlinx.android.synthetic.main.fragment_list.*

/**
 * Created by cesar.torres on 3/12/2018.
 */
class ProductListFragment : Fragment(),ProductListView,ProductListAdapter.ProductListAdapterInterface {
    private lateinit var presenter : ProductListPresenter
    private lateinit var adapter : ProductListAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_list,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter = ProductListPresenter(this)
        presenter.getStoredProducts()
    }

    override fun onStart() {
        super.onStart()
        presenter.register()
    }

    override fun onStop() {
        super.onStop()
        presenter.unregister()
    }

    override fun initializeUI() {
        adapter = ProductListAdapter(this)
        rvList.layoutManager = LinearLayoutManager(this.context)
        rvList.adapter = adapter
    }

    override fun fillProductList(productViewModelList: List<ProductViewModel>) {
        adapter.let {
            it.productViewModelList =  productViewModelList
            it.notifyDataSetChanged()
        }
    }

    override fun showNoListMessage() {

    }

    override fun showErrorMessage(errorMessage: String) {
        Toast.makeText(activity,errorMessage,Toast.LENGTH_SHORT).show()
    }

    override fun onItemClicked(position: Int) {
        presenter.itemClicked(position)
    }

    companion object {
        fun newInstance () : ProductListFragment {
            return  ProductListFragment()
        }
    }
}