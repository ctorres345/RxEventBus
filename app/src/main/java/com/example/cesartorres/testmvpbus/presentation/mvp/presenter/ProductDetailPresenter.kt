package com.example.cesartorres.testmvpbus.presentation.mvp.presenter

import com.example.cesartorres.testmvpbus.presentation.mvp.model.ProductViewModel
import com.example.cesartorres.testmvpbus.presentation.mvp.utils.BusEventType
import com.example.cesartorres.testmvpbus.presentation.mvp.utils.Constants
import com.example.cesartorres.testmvpbus.presentation.mvp.utils.GenericEvent
import com.example.cesartorres.testmvpbus.presentation.mvp.view.contract.ProductDetailView

/**
 * Created by cesar.torres on 3/12/2018.
 */
class ProductDetailPresenter (var view : ProductDetailView?) : BaseBusPresenter() {

    init {
        initialize()
    }

    override fun eventUpdated(event: GenericEvent?) {
        val eventName = event?.eventName
        val eventObject = event?.mainObject
        when(eventName){
            BusEventType.PRODUCT_SELECTED -> eventObject?.let { if((it is ProductViewModel)) handleSelectedProduct(it) }
            else -> null
        }
    }

    private fun handleSelectedProduct(productViewModel: ProductViewModel) {
        view?.let {
            it.setResultScreen()
            it.setProductName(productViewModel.productName ?: Constants.NAME_NOT_DEFINED)
            it.setDescription(productViewModel.productDescription ?: Constants.DESCRIPTION_NOT_DEFINED)
            it.setPrice(productViewModel.productPrice ?: Constants.DEFAULT_AMOUNT)
            it.setType(productViewModel.productType ?: Constants.TYPE_NOT_DEFINED)
        }
    }

    override fun handleEventBusError() {
        view?.showErrorMessage(Constants.BUS_EVENT_ERROR)
    }

    fun initialize(){
        view?.initializeUI()
        view?.setBlankScreen()
    }
}