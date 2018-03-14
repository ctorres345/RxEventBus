package com.example.cesartorres.testmvpbus.presentation.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.cesartorres.testmvpbus.R
import com.example.cesartorres.testmvpbus.presentation.mvp.model.ProductViewModel
import com.example.cesartorres.testmvpbus.presentation.ui.viewholder.ProductListViewHolder

/**
 * Created by cesar.torres on 3/13/2018.
 */
class ProductListAdapter (private val productListAdapterInterface : ProductListAdapterInterface) : RecyclerView.Adapter<ProductListViewHolder>() {

    private val productViewModelList = ArrayList<ProductViewModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductListViewHolder {
        return ProductListViewHolder(LayoutInflater
                .from(parent.context)
                .inflate(R.layout.item_product,parent,false), productListAdapterInterface)
    }

    override fun getItemCount(): Int {
        return productViewModelList.size
    }

    override fun onBindViewHolder(holder: ProductListViewHolder, position: Int) {
        holder.bind(position,productViewModelList)
    }

    interface ProductListAdapterInterface{
        fun onItemClicked(position : Int)
    }

}