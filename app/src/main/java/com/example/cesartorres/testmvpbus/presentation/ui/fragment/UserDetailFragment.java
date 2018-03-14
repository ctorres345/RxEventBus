package com.example.cesartorres.testmvpbus.presentation.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cesartorres.testmvpbus.R;
import com.example.cesartorres.testmvpbus.presentation.mvp.presenter.UserDetailPresenter;
import com.example.cesartorres.testmvpbus.presentation.mvp.view.contract.UserDetailView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by cesar.torres on 3/9/2018.
 */

public class UserDetailFragment extends Fragment implements UserDetailView {
    @BindView(R.id.tvFullname)protected TextView tvFullname;
    @BindView(R.id.tvEmail)protected TextView tvEmail;
    @BindView(R.id.tvPhone)protected TextView tvPhone;
    @BindView(R.id.tvProfession)protected TextView tvProfession;
    @BindView(R.id.tvDummy)protected TextView tvDummy;

    private UserDetailPresenter presenter;

    public static UserDetailFragment newInstance(){
        return new UserDetailFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_user_detail,container,false);
        ButterKnife.bind(this,rootView);
        presenter = new UserDetailPresenter(this);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter.initialize();
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
    public void initializeUI() {

    }

    @Override
    public void showErrorMessage(String errorMessage) {
        Toast.makeText(getContext(),errorMessage,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setFullname(String fullname) {
        if(tvFullname != null){
            tvFullname.setVisibility(View.VISIBLE);
            tvFullname.setText(fullname);
        }
    }

    @Override
    public void setPhonenumber(String phonenumber) {
        if(tvPhone != null){
            tvPhone.setVisibility(View.VISIBLE);
            tvPhone.setText(phonenumber);
        }
    }

    @Override
    public void setEmail(String email) {
        if(tvEmail != null){
            tvEmail.setVisibility(View.VISIBLE);
            tvEmail.setText(email);
        }
    }

    @Override
    public void setProfession(String profession) {
        if(tvProfession != null){
            tvProfession.setVisibility(View.VISIBLE);
            tvProfession.setText(profession);
        }
    }

    @Override
    public void setResultScreen() {
        tvDummy.setVisibility(View.GONE);
    }

    @Override
    public void setBlankScreen() {
        tvDummy.setVisibility(View.VISIBLE);
        tvFullname.setVisibility(View.GONE);
        tvEmail.setVisibility(View.GONE);
        tvPhone.setVisibility(View.GONE);
        tvProfession.setVisibility(View.GONE);
    }

}
