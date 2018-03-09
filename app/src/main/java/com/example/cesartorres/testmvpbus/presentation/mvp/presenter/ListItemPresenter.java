package com.example.cesartorres.testmvpbus.presentation.mvp.presenter;

import com.example.cesartorres.testmvpbus.presentation.mvp.model.User;
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

    public void handleBinding(int position, List<User> userList){
        if (position >= userList.size()) return;
        if (userList.get(position) != null){
            view.setNickname(userList.get(position).getNickname() != null ? userList.get(position).getNickname() : "NO TIENE NOMBRE");
        }
    }
}
