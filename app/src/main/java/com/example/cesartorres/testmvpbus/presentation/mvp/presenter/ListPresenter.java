package com.example.cesartorres.testmvpbus.presentation.mvp.presenter;

import com.example.cesartorres.testmvpbus.presentation.mvp.model.UserViewModel;
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
    private List<UserViewModel> userViewModelList;

    public ListPresenter(ListView view) {
        this.view = view;
        this.userViewModelList = new ArrayList<>();
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
        List<UserViewModel> dummyUserViewModels = getDummyList();
        final Observable<List<UserViewModel>> listObservable = Observable.fromArray(dummyUserViewModels)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        listObservable.subscribe(new Observer<List<UserViewModel>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(List<UserViewModel> userViewModels) {
                userViewModelList = userViewModels;
            }

            @Override
            public void onError(Throwable e) {
                view.showNoListMessage();
                view.showErrorMessage(e.getMessage());
            }

            @Override
            public void onComplete() {
                if(userViewModelList != null && userViewModelList.size() > 0){
                    view.fillUserList(userViewModelList);
                }
            }
        });
    }

    public void itemClicked(int position) {
        if(userViewModelList != null && userViewModelList.size() > 0){
            UserViewModel userViewModel = userViewModelList.get(position);
            GenericEvent userSelectionEvent = new GenericEvent(BusEventType.USER_SELECTED, userViewModel);
            sendEvent(userSelectionEvent);
        }
    }

    private List<UserViewModel> getDummyList() {
        List<UserViewModel> results = new ArrayList<>();
        results.add(new UserViewModel(results.size(),"Cesar Torres","ctorres@gmail.com","123123123","Software Engineer 2","ctorres345"));
        results.add(new UserViewModel(results.size(),"John Doe","jdoe@gmail.com","123123123","Software Engineer 2","jdoe123"));
        results.add(new UserViewModel(results.size(),"Christine Jackson","cjackson@gmail.com","123123123","Software Engineer 2","cjackson123"));
        results.add(new UserViewModel(results.size(),"Pedro Contoso","pcontoso@gmail.com","123123123","UX Engineer 1","pcontoso678"));
        results.add(new UserViewModel(results.size(),"Max Myers","mmyers@gmail.com","123123123","Software Engineer 1","mmyers567"));
        results.add(new UserViewModel(results.size(),"Mary Sue","msue@gmail.com","123123123","UX Engineer 2","msue345"));
        return results;
    }

}
