package com.example.cesartorres.testmvpbus.presentation.mvp.model;

/**
 * Created by cesar.torres on 3/9/2018.
 */

public class User {
    private long id;
    private String fullName;
    private String email;
    private String phoneNumber;
    private String profession;
    private String nickname;

    public User() {
    }

    public User(long id, String fullName, String email, String phoneNumber, String profession, String nickname) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.profession = profession;
        this.nickname = nickname;
    }

    public long getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getProfession() {
        return profession;
    }

    public String getNickname() {
        return nickname;
    }
}
