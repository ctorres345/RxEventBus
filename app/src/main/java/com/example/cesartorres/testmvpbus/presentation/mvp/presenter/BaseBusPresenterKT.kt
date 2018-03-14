package com.example.cesartorres.testmvpbus.presentation.mvp.presenter

import com.example.cesartorres.testmvpbus.presentation.mvp.utils.EventBus
import com.example.cesartorres.testmvpbus.presentation.mvp.utils.GenericEvent
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer

/**
 * Created by cesar.torres on 3/13/2018.
 */
abstract class BaseBusPresenterKT {
    private lateinit var rxBus : EventBus.RxBus
    private var rxBusSubscription : Disposable? = null

    fun register () { initializeBus() }
    fun unregister () { rxBusSubscription?.let { if (!it.isDisposed) it.dispose()}}

    abstract fun eventUpdated(event : GenericEvent)
    abstract fun handleEventBusError()

    private fun initializeBus() {
        rxBus = EventBus.getInstance()
        rxBusSubscription = rxBus
                .toObservable()
                .subscribe(Consumer { if (it is GenericEvent) eventUpdated(it)} , Consumer { handleEventBusError() })
    }
}