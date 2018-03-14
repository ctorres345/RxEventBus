package com.example.cesartorres.testmvpbus.presentation.mvp.presenter;

import com.example.cesartorres.testmvpbus.presentation.mvp.utils.EventBus;
import com.example.cesartorres.testmvpbus.presentation.mvp.utils.GenericEvent;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * Created by cesar.torres on 3/9/2018.
 */

public abstract class BaseBusPresenter {

    private EventBus.RxBus rxBus;
    private Disposable rxBusSubscription;

    public BaseBusPresenter() {
    }

    public void register() {
        initializeBus();
    }

    public void unregister() {
        if (rxBusSubscription != null && !rxBusSubscription.isDisposed()){
            rxBusSubscription.dispose();
        }
    }

    public void sendEvent(GenericEvent genericEvent) {
        rxBus.send(genericEvent);
    }

    abstract void eventUpdated(GenericEvent event);
    abstract void handleEventBusError();

    private void initializeBus() {
        rxBus = EventBus.getInstance();
        rxBusSubscription = rxBus.toObservable().subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object event) throws Exception {
                if (event instanceof GenericEvent) {
                    eventUpdated((GenericEvent) event);
                }
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                handleEventBusError();
            }
        });
    }

}
