package com.example.cesartorres.testmvpbus.presentation.mvp.view.contract;

import com.example.cesartorres.testmvpbus.presentation.mvp.model.UserViewModel;

import java.util.List;

/**
 * Created by cesar.torres on 3/9/2018.
 */

public interface UserListView extends BaseView {
    void showNoListMessage();

    void fillUserList(List<UserViewModel> userViewModels);
}
