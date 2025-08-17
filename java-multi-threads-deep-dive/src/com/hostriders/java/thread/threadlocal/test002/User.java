package com.hostriders.java.thread.threadlocal.test002;

public class User {
    private String name;
    private String userId;
    public User(String name, String userId) {
        this.name = name;
    }
    public User(){

    }
    // getters and Setters
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
}
