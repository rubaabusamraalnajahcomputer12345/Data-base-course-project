package com.example.projectstartingcoding;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

import java.sql.*;

public class MYSQLCONNTASK {



    Connection conn=null;
    public static Connection ConnectDB(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn= (Connection) DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","projectproject","12345");
            return conn;
        } catch (Exception e) {
            // Alert A=new Alert(Alert.AlertType.CONFIRMATION,"CONNECT SUCCESSFULL", ButtonType.OK);
            //  A.show();
            return null;
        }
    }


    @FXML
    public static ObservableList<TASK> getDatausers() throws SQLException {
        Connection conn=ConnectDB();
        // con= DriverManager.getConnection(DB_URL,"projectproject","12345");
        ObservableList<TASK>list= FXCollections.observableArrayList();
        try{
            PreparedStatement PS=conn.prepareStatement("SELECT * FROM lawpro.Task ");
            ResultSet rs=PS.executeQuery();
            //Alert A=new Alert(Alert.AlertType.CONFIRMATION,"CONNECT SUCCESSFULL", ButtonType.OK);
            //  A.show();

            while (rs.next()){
                list.add(new TASK(Integer.parseInt(rs.getString("Task_id")), rs.getString("Task_description"),rs.getString("Deadline"),rs.getString("Status"),(Integer.parseInt(rs.getString("Lawyer_id")))));
                // Alert A=new Alert(Alert.AlertType.CONFIRMATION,"CONNECT SUCCESSFULL", ButtonType.OK);
                // A.show();

            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        //  System.out.println("Number of recordslawyer: " + list.size());
        return list;
    }
















}
