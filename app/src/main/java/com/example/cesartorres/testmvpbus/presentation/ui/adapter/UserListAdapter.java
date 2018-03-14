package com.example.cesartorres.testmvpbus.presentation.ui.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cesartorres.testmvpbus.R;
import com.example.cesartorres.testmvpbus.presentation.mvp.model.UserViewModel;
import com.example.cesartorres.testmvpbus.presentation.ui.viewholder.UserListViewHolder;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by cesar.torres on 3/9/2018.
 */

public class UserListAdapter extends RecyclerView.Adapter<UserListViewHolder> {
    private List<UserViewModel> userViewModelList;
    private ListAdapterInterface listAdapterInterface;

    public UserListAdapter(ListAdapterInterface listAdapterInterface) {
        this.listAdapterInterface = listAdapterInterface;
        this.userViewModelList = new ArrayList<>();
    }

    public interface ListAdapterInterface{
        void onItemClicked(int position);
    }

    @NonNull
    @Override
    public UserListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View rootView = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.item_user,parent,false);
        return new UserListViewHolder(rootView,this.listAdapterInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull UserListViewHolder holder, int position) {
        holder.bind(position, getUserViewModelList());
    }

    @Override
    public int getItemCount() {
        return getUserViewModelList().size();
    }

    public List<UserViewModel> getUserViewModelList() {
        return userViewModelList;
    }

    public void setUserViewModelList(List<UserViewModel> userViewModelList) {
        this.userViewModelList = userViewModelList;
        notifyDataSetChanged();
    }
}
