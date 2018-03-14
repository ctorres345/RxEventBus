package com.example.cesartorres.testmvpbus.presentation.ui.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.cesartorres.testmvpbus.R;
import com.example.cesartorres.testmvpbus.presentation.mvp.model.UserViewModel;
import com.example.cesartorres.testmvpbus.presentation.mvp.presenter.UserListItemPresenter;
import com.example.cesartorres.testmvpbus.presentation.mvp.view.contract.UserListItemView;
import com.example.cesartorres.testmvpbus.presentation.ui.adapter.UserListAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by cesar.torres on 3/9/2018.
 */

public class UserListViewHolder extends RecyclerView.ViewHolder implements UserListItemView {
    @BindView(R.id.tvNickname) TextView tvNickname;

    private UserListAdapter.ListAdapterInterface listAdapterInterface;
    private UserListItemPresenter presenter;

    public UserListViewHolder(View itemView, final UserListAdapter.ListAdapterInterface listAdapterInterface) {
        super(itemView);
        ButterKnife.bind(this,itemView);
        presenter = new UserListItemPresenter(this);
        this.listAdapterInterface = listAdapterInterface;

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listAdapterInterface.onItemClicked(getAdapterPosition());
            }
        });
    }

    public void bind(int position, List<UserViewModel> userViewModelList) {
        presenter.handleBinding(position, userViewModelList);
    }

    @Override
    public void setNickname(String nickname) {
        if(tvNickname != null){
            tvNickname.setText(nickname);
        }
    }
}
