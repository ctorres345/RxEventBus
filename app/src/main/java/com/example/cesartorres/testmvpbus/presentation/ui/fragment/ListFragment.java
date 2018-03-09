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
import com.example.cesartorres.testmvpbus.presentation.mvp.model.User;
import com.example.cesartorres.testmvpbus.presentation.mvp.presenter.ListPresenter;
import com.example.cesartorres.testmvpbus.presentation.mvp.view.contract.ListView;
import com.example.cesartorres.testmvpbus.presentation.ui.adapter.ListAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by cesar.torres on 3/9/2018.
 */

public class ListFragment extends Fragment implements ListAdapter.ListAdapterInterface, ListView{

    @BindView(R.id.rvList)protected RecyclerView rvList;

    private ListAdapter adapter;
    private ListPresenter presenter;

    public static ListFragment newInstance(){
        return new ListFragment();
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_list,container,false);
        ButterKnife.bind(this,rootView);
        presenter = new ListPresenter(this);
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
    public void fillUserList(List<User> users) {
        if(adapter != null){
            adapter.setUserList(users);
        }
    }

    @Override
    public void showErrorMessage(String message) {
        Toast.makeText(getActivity(),message,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void initializeUI() {
        adapter = new ListAdapter(this);
        rvList.setLayoutManager(new LinearLayoutManager(getContext()));
        rvList.setAdapter(adapter);
    }
}
