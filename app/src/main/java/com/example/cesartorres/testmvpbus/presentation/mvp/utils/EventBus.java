package com.example.cesartorres.testmvpbus.presentation.mvp.utils;


import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

/**
 * Created by cesar.torres on 3/9/2018.
 */

public class EventBus {

    private static final RxBus rxBus = new RxBus();

    public static RxBus getInstance(){return rxBus;}

    public static final class RxBus{
        private final Subject<Object> bus = PublishSubject.create().toSerialized();

        public void send(Object o){
            bus.onNext(o);
        }

        public Observable<Object> toObservable(){
            return bus;
        }
    }
}
