package com.example.cesartorres.testmvpbus.presentation.mvp.view.contract

/**
 * Created by cesar.torres on 3/12/2018.
 */
interface ProductDetailView {
    fun initializeUI()
    fun showErrorMessage(errorMessage: String)
    fun setDescription(description : String?)
    fun setPrice(price : Double?)
    fun setType(type : String?)
    fun setResultScreen()
    fun setBlankScreen()
}