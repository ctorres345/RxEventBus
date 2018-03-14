package com.example.cesartorres.testmvpbus.presentation.mvp.view.contract

import com.example.cesartorres.testmvpbus.presentation.mvp.model.ProductViewModel

/**
 * Created by cesar.torres on 3/12/2018.
 */
interface ProductListView {
    fun initializeUI()
    fun showErrorMessage(errorMessage: String)
    fun showNoListMessage()
    fun fillProductList(productViewModelList: List<ProductViewModel>)
}