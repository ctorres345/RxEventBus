package com.example.cesartorres.testmvpbus.presentation.mvp.presenter;

import com.example.cesartorres.testmvpbus.presentation.mvp.model.User;
import com.example.cesartorres.testmvpbus.presentation.mvp.utils.BusEventType;
import com.example.cesartorres.testmvpbus.presentation.mvp.utils.Constants;
import com.example.cesartorres.testmvpbus.presentation.mvp.utils.GenericEvent;
import com.example.cesartorres.testmvpbus.presentation.mvp.view.contract.ListView;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by cesar.torres on 3/9/2018.
 */

public class ListPresenter extends BaseBusPresenter {
    private ListView view;
    private List<User> userList;

    public ListPresenter(ListView view) {
        this.view = view;
        this.userList = new ArrayList<>();
    }

    @Override
    void eventUpdated(GenericEvent event) {

    }

    @Override
    void handleEventBusError() {
        view.showErrorMessage(Constants.BUS_EVENT_ERROR);
    }

    public void initialize() {
        view.initializeUI();
    }

    public void getStoredUsers() {
        List<User> dummyUsers = getDummyList();
        final Observable<List<User>> listObservable = Observable.fromArray(dummyUsers)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        listObservable.subscribe(new Observer<List<User>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(List<User> users) {
                userList = users;
            }

            @Override
            public void onError(Throwable e) {
                view.showNoListMessage();
                view.showErrorMessage(e.getMessage());
            }

            @Override
            public void onComplete() {
                if(userList != null && userList.size() > 0){
                    view.fillUserList(userList);
                }
            }
        });
    }

    public void itemClicked(int position) {
        if(userList != null && userList.size() > 0){
            User user = userList.get(position);
            GenericEvent userSelectionEvent = new GenericEvent(BusEventType.USER_SELECTED,user);
            sendEvent(userSelectionEvent);
        }
    }

    private List<User> getDummyList() {
        List<User> results = new ArrayList<>();
        results.add(new User(results.size(),"Cesar Torres","ctorres@gmail.com","123123123","Software Engineer 2","ctorres345"));
        results.add(new User(results.size(),"Asahel Zavaleta","azavaleta@gmail.com","123123123","Software Engineer 2","azavaleta345"));
        results.add(new User(results.size(),"Lesli Bautista","lbautista@gmail.com","123123123","UX Engineer 1","lbautista345"));
        results.add(new User(results.size(),"Joshua Rojas","jrojas@gmail.com","123123123","Software Engineer 1","jrojas345"));
        results.add(new User(results.size(),"Ana Lucia Chalco","achalco@gmail.com","123123123","UX Engineer 2","achalco345"));
        return results;
    }

}
