package com.example.cesartorres.testmvpbus.presentation.mvp.presenter;

import com.example.cesartorres.testmvpbus.presentation.mvp.model.UserViewModel;
import com.example.cesartorres.testmvpbus.presentation.mvp.utils.BusEventType;
import com.example.cesartorres.testmvpbus.presentation.mvp.utils.Constants;
import com.example.cesartorres.testmvpbus.presentation.mvp.utils.GenericEvent;
import com.example.cesartorres.testmvpbus.presentation.mvp.view.contract.DetailView;

/**
 * Created by cesar.torres on 3/9/2018.
 */

public class DetailPresenter extends BaseBusPresenter{
    private DetailView view;

    public DetailPresenter(DetailView view) {
        this.view = view;
    }

    @Override
    void eventUpdated(GenericEvent event) {
        final String eventName = event.eventName;
        switch (eventName){
            case BusEventType.USER_SELECTED:
                if(isUserSelectionEvent(event)){
                    handleSelectedUser((UserViewModel)event.mainObject);
                }
                break;
            default:
        }
    }

    private void handleSelectedUser(UserViewModel userViewModel) {
        if(userViewModel != null){
            view.setResultScreen();
            if (userViewModel.getFullName() != null){
                view.setFullname(userViewModel.getFullName());
            }
            if (userViewModel.getEmail() != null){
                view.setEmail(userViewModel.getEmail());
            }
            if (userViewModel.getPhoneNumber() != null){
                view.setPhonenumber(userViewModel.getPhoneNumber());
            }
            if (userViewModel.getProfession() != null){
                view.setProfession(userViewModel.getProfession());
            }
        }
    }

    private boolean isUserSelectionEvent(GenericEvent event) {
        if(event == null || event.mainObject == null){
            return false;
        }

        return event.mainObject instanceof UserViewModel;
    }

    @Override
    void handleEventBusError() {
        view.showErrorMessage(Constants.BUS_EVENT_ERROR);
    }

    public void initialize(){
        view.initializeUI();
        view.setBlankScreen();
    }

}
