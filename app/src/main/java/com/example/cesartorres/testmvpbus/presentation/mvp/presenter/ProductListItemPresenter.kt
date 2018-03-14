package com.example.cesartorres.testmvpbus.presentation.mvp.presenter

import com.example.cesartorres.testmvpbus.presentation.mvp.model.ProductViewModel
import com.example.cesartorres.testmvpbus.presentation.mvp.view.contract.ProductListItemView

/**
 * Created by cesar.torres on 3/12/2018.
 */
class ProductListItemPresenter (val view : ProductListItemView?) {

    fun handleBinding(position : Int, productViewModelList : List<ProductViewModel>){
        if(position >= productViewModelList.size) return
        productViewModelList[position].let { view?.setProductName(it.productName?: "NO NAME REGISTERED") }
    }
}