package com.example.cesartorres.testmvpbus.presentation.ui.viewholder

import android.support.v7.widget.RecyclerView
import android.view.View
import com.example.cesartorres.testmvpbus.presentation.mvp.model.ProductViewModel
import com.example.cesartorres.testmvpbus.presentation.mvp.presenter.ProductListItemPresenter
import com.example.cesartorres.testmvpbus.presentation.mvp.view.contract.ProductListItemView
import com.example.cesartorres.testmvpbus.presentation.ui.adapter.ProductListAdapter
import kotlinx.android.synthetic.main.item_product.view.*

/**
 * Created by cesar.torres on 3/13/2018.
 */
class ProductListViewHolder(itemView: View , productListAdapterInterface: ProductListAdapter.ProductListAdapterInterface) : ProductListItemView, RecyclerView.ViewHolder(itemView) {

    var presenter = ProductListItemPresenter(this)

    init {
        itemView.setOnClickListener( { productListAdapterInterface.onItemClicked(adapterPosition) } )
    }

    fun bind(position : Int, productViewModelList : List<ProductViewModel>){
        presenter.handleBinding(position,productViewModelList)
    }

    override fun setProductName(name: String?) {
        name.let { itemView.tvProductName.text = it }
    }

}