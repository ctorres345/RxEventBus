package com.example.cesartorres.testmvpbus.presentation.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.cesartorres.testmvpbus.R;
import com.example.cesartorres.testmvpbus.presentation.mvp.model.UserViewModel;
import com.example.cesartorres.testmvpbus.presentation.mvp.presenter.UserListPresenter;
import com.example.cesartorres.testmvpbus.presentation.mvp.view.contract.UserListView;
import com.example.cesartorres.testmvpbus.presentation.ui.adapter.UserListAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by cesar.torres on 3/9/2018.
 */

public class UserListFragment extends Fragment implements UserListAdapter.ListAdapterInterface, UserListView {

    @BindView(R.id.rvList)protected RecyclerView rvList;

    private UserListAdapter adapter;
    private UserListPresenter presenter;

    public static UserListFragment newInstance(){
        return new UserListFragment();
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_list,container,false);
        ButterKnife.bind(this,rootView);
        presenter = new UserListPresenter(this);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter.initialize();
        presenter.getStoredUsers();
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.register();
    }

    @Override
    public void onStop() {
        super.onStop();
        presenter.unregister();
    }

    @Override
    public void onItemClicked(int position) {
        presenter.itemClicked(position);
    }

    @Override
    public void showNoListMessage() {

    }

    @Override
    public void fillUserList(List<UserViewModel> userViewModels) {
        if(adapter != null){
            adapter.setUserViewModelList(userViewModels);
        }
    }

    @Override
    public void showErrorMessage(String message) {
        Toast.makeText(getActivity(),message,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void initializeUI() {
        adapter = new UserListAdapter(this);
        rvList.setLayoutManager(new LinearLayoutManager(getContext()));
        rvList.setAdapter(adapter);
    }
}
