package com.example.cesartorres.testmvpbus.presentation.ui.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cesartorres.testmvpbus.R;
import com.example.cesartorres.testmvpbus.presentation.mvp.model.User;
import com.example.cesartorres.testmvpbus.presentation.ui.viewholder.ListViewHolder;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by cesar.torres on 3/9/2018.
 */

public class ListAdapter extends RecyclerView.Adapter<ListViewHolder> {
    private List<User> userList;
    private ListAdapterInterface listAdapterInterface;

    public ListAdapter(ListAdapterInterface listAdapterInterface) {
        this.listAdapterInterface = listAdapterInterface;
        this.userList = new ArrayList<>();
    }

    public interface ListAdapterInterface{
        void onItemClicked(int position);
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View rootView = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.item_user,parent,false);
        return new ListViewHolder(rootView,this.listAdapterInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {
        holder.bind(position,getUserList());
    }

    @Override
    public int getItemCount() {
        return getUserList().size();
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
        notifyDataSetChanged();
    }
}
