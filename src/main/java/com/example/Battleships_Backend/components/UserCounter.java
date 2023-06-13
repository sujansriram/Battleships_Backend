package com.example.Battleships_Backend.components;

import org.springframework.stereotype.Component;

@Component
public class UserCounter {
    private int userCount;

    public UserCounter(int userCount) {
        this.userCount = userCount;
    }

    public UserCounter() {
    }

    public int getUserCount() {
        return userCount;
    }

    public void setUserCount(int userCount) {
        this.userCount = userCount;
    }

    public void addUser(){
        userCount++;
    }

    public void removeUser(){
        userCount--;
    }
}
