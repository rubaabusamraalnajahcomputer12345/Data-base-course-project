package com.example.projectstartingcoding;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.sql.*;

public class MYSQLCONN {
Connection con=null;
    public static Connection ConnectDB(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con= (Connection) DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","projectproject","12345");

            return con;
        } catch (Exception e) {
                 //   Alert A=new Alert(Alert.AlertType.CONFIRMATION,"CONNECT SUCCESSFULL", ButtonType.OK);
           // A.show();
            return null;
        }
    }
@FXML
    public static ObservableList<USER> getDatausers() throws SQLException {
        Connection con=ConnectDB();
       // con= DriverManager.getConnection(DB_URL,"projectproject","12345");
        ObservableList<USER>list= FXCollections.observableArrayList();
        try{
            PreparedStatement PS=con.prepareStatement("SELECT \"User_id\",\"Password\" FROM lawpro.User ");
            ResultSet rs=PS.executeQuery();

            while (rs.next()){
                list.add(new USER(rs.getString("User_id"),rs.getString("Password")));

            }
        }
        catch (SQLException e){
e.printStackTrace();
        }
   // System.out.println("Number of records: " + list.size());
        return list;
    }






}
