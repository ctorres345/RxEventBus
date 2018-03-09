package com.example.cesartorres.testmvpbus.presentation.mvp.presenter;

import com.example.cesartorres.testmvpbus.presentation.mvp.model.User;
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
                    handleSelectedUser((User)event.mainObject);
                }
                break;
            default:
        }
    }

    private void handleSelectedUser(User user) {
        if(user != null){
            view.setResultScreen();
            if (user.getFullName() != null){
                view.setFullname(user.getFullName());
            }
            if (user.getEmail() != null){
                view.setEmail(user.getEmail());
            }
            if (user.getPhoneNumber() != null){
                view.setPhonenumber(user.getPhoneNumber());
            }
            if (user.getProfession() != null){
                view.setProfession(user.getProfession());
            }
        }
    }

    private boolean isUserSelectionEvent(GenericEvent event) {
        if(event == null || event.mainObject == null){
            return false;
        }

        return event.mainObject instanceof User;
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
