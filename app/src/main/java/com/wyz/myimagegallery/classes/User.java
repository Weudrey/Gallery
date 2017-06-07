package com.wyz.myimagegallery.classes;

/**
 * Created by W€µÐr€Y™ on 06/06/2017.
 */

public class User {
    private int userID;
    private String name;
    private String email;
    private String password;

    public int getUserID(){
        return userID;
    }

    public void setUserID(int id){
        this.userID = id;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getPassword(){
        return password;
    }

    public void setPassword(String password){
        this.password = password;
    }
}
