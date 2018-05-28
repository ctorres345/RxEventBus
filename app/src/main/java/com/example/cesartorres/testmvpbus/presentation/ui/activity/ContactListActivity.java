package com.example.cesartorres.testmvpbus.presentation.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.cesartorres.testmvpbus.R;
import com.example.cesartorres.testmvpbus.presentation.ui.fragment.UserDetailFragment;
import com.example.cesartorres.testmvpbus.presentation.ui.fragment.UserListFragment;

/**
 * Created by cesar.torres on 3/9/2018.
 */

public class ContactListActivity extends AppCompatActivity{

    public static Intent newIntent(Context context){
        Intent intent = new Intent(context,ContactListActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        initFragments();
    }

    private void initFragments() {
        UserListFragment userListFragment = UserListFragment.newInstance();
        getSupportFragmentManager().beginTransaction().add(R.id.fl_left_layout, userListFragment).addToBackStack(null).commit();

        UserDetailFragment userDetailFragment = UserDetailFragment.newInstance();
        getSupportFragmentManager().beginTransaction().add(R.id.fl_right_layout, userDetailFragment).addToBackStack(null).commit();
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
