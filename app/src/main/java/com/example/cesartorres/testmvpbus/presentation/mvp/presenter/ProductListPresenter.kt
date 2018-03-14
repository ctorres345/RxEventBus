package com.example.cesartorres.testmvpbus.presentation.mvp.presenter

import com.example.cesartorres.testmvpbus.presentation.mvp.model.ProductViewModel
import com.example.cesartorres.testmvpbus.presentation.mvp.utils.BusEventType
import com.example.cesartorres.testmvpbus.presentation.mvp.utils.Constants
import com.example.cesartorres.testmvpbus.presentation.mvp.utils.GenericEvent
import com.example.cesartorres.testmvpbus.presentation.mvp.view.contract.ProductListView
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

/**
 * Created by cesar.torres on 3/12/2018.
 */
class ProductListPresenter(var view : ProductListView?) : BaseBusPresenter() {

    private var productViewModelList = ArrayList<ProductViewModel>()

    override fun eventUpdated (event: GenericEvent?) {

    }

    override fun handleEventBusError () {
        view?.showErrorMessage(Constants.BUS_EVENT_ERROR)
    }

    fun initialize(){
        view?.initializeUI()
    }

    fun getStoredProducts (){
        Observable.fromArray(getDummyList())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(onNext = {productViewModelList = it},
                onComplete = {view?.fillProductList(productViewModelList)},
                onError = {
                    view?.showNoListMessage()
                    view?.showErrorMessage(it.message?:"Observable Error")
                })
    }

    fun itemClicked (position : Int?){
        position?.let { sendEvent(GenericEvent(BusEventType.PRODUCT_SELECTED,productViewModelList.get(it))) }
    }

    private fun getDummyList(): ArrayList<ProductViewModel>{
        val results = ArrayList<ProductViewModel>()
        results.add(ProductViewModel(results.size.toLong(),"Tic Tac Clásico","Cajeta de pastillas con sabor mentolado","Caramelos",1.50))
        results.add(ProductViewModel(results.size.toLong(),"Tic Tac Naranja","Cajeta de pastillas con sabor a naranja","Caramelos",1.50))
        results.add(ProductViewModel(results.size.toLong(),"Oreo","Paquete con 4 galletas de chocolate con crema","Galletas",1.00))
        results.add(ProductViewModel(results.size.toLong(),"Pan con Pollo","Pan frances relleno del día relleno con pollo, lechuga y papas al hilo","Desayuno",3.00))
        return results
    }
}