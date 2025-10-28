package com.example.projectstartingcoding;

import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;

public class USER {

    private  String user_id;
    private  String password;

    public USER(String user_id,String password){
        this.user_id=user_id;
        this.password=password;
    }

    public void setUser_id(String user_id){
        this.user_id=user_id;
    }

    public void setPassword(String password){
        this.password=password;
    }
    public String getUser_id(){
        return user_id;
    }
    public String getPassword(){
        return password;
    }


}
