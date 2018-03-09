package com.example.cesartorres.testmvpbus.presentation.mvp.view.contract;

/**
 * Created by cesar.torres on 3/9/2018.
 */

public interface DetailView extends BaseView{
    void setFullname(String fullname);
    void setPhonenumber(String phonenumber);
    void setEmail(String email);
    void setProfession(String profession);
    void setResultScreen();
    void setBlankScreen();
}
