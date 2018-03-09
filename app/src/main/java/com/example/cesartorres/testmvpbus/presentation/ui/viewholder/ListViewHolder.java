package com.example.cesartorres.testmvpbus.presentation.ui.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.cesartorres.testmvpbus.R;
import com.example.cesartorres.testmvpbus.presentation.mvp.model.User;
import com.example.cesartorres.testmvpbus.presentation.mvp.presenter.ListItemPresenter;
import com.example.cesartorres.testmvpbus.presentation.mvp.view.contract.ListItemView;
import com.example.cesartorres.testmvpbus.presentation.ui.adapter.ListAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by cesar.torres on 3/9/2018.
 */

public class ListViewHolder extends RecyclerView.ViewHolder implements ListItemView{
    @BindView(R.id.tvNickname) TextView tvNickname;

    private ListAdapter.ListAdapterInterface listAdapterInterface;
    private ListItemPresenter presenter;

    public ListViewHolder(View itemView, final ListAdapter.ListAdapterInterface listAdapterInterface) {
        super(itemView);
        ButterKnife.bind(this,itemView);
        presenter = new ListItemPresenter(this);
        this.listAdapterInterface = listAdapterInterface;

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listAdapterInterface.onItemClicked(getAdapterPosition());
            }
        });
    }

    public void bind(int position, List<User> userList) {
        presenter.handleBinding(position,userList);
    }

    @Override
    public void setNickname(String nickname) {
        if(tvNickname != null){
            tvNickname.setText(nickname);
        }
    }
}
