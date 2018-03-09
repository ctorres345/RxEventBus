package com.example.cesartorres.testmvpbus.presentation.mvp.view.contract;

import com.example.cesartorres.testmvpbus.presentation.mvp.model.User;

import java.util.List;

/**
 * Created by cesar.torres on 3/9/2018.
 */

public interface ListView extends BaseView {
    void showNoListMessage();

    void fillUserList(List<User> users);
}
