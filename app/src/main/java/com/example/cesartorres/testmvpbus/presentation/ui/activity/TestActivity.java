package com.example.cesartorres.testmvpbus.presentation.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.cesartorres.testmvpbus.R;
import com.example.cesartorres.testmvpbus.presentation.ui.fragment.DetailFragment;
import com.example.cesartorres.testmvpbus.presentation.ui.fragment.ListFragment;

/**
 * Created by cesar.torres on 3/9/2018.
 */

public class TestActivity extends AppCompatActivity{

    public static Intent newIntent(Context context){
        Intent intent = new Intent(context,TestActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        initFragments();
    }

    private void initFragments() {
        ListFragment listFragment = ListFragment.newInstance();
        getSupportFragmentManager().beginTransaction().add(R.id.fl_left_layout,listFragment).addToBackStack(null).commit();

        DetailFragment detailFragment = DetailFragment.newInstance();
        getSupportFragmentManager().beginTransaction().add(R.id.fl_right_layout,detailFragment).addToBackStack(null).commit();
    }


}
