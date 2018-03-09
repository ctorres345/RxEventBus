package com.example.cesartorres.testmvpbus.presentation.mvp.presenter;

import com.example.cesartorres.testmvpbus.presentation.mvp.model.UserViewModel;
import com.example.cesartorres.testmvpbus.presentation.mvp.view.contract.ListItemView;

import java.util.List;

/**
 * Created by cesar.torres on 3/9/2018.
 */

public class ListItemPresenter {
    private ListItemView view;

    public ListItemPresenter(ListItemView view) {
        this.view = view;
    }

    public void handleBinding(int position, List<UserViewModel> userViewModelList){
        if (position >= userViewModelList.size()) return;
        if (userViewModelList.get(position) != null){
            view.setNickname(userViewModelList.get(position).getNickname() != null ? userViewModelList.get(position).getNickname() : "NO TIENE NOMBRE");
        }
    }
}
