package com.example.projectstartingcoding;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.sql.*;
import java.time.LocalDate;

public class MYSQLCONNLAWYER {


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
    public static ObservableList<LAWYER> getDatausers() throws SQLException {
        Connection conn=ConnectDB();
        // con= DriverManager.getConnection(DB_URL,"projectproject","12345");
        ObservableList<LAWYER>list= FXCollections.observableArrayList();
       // ObservableList<LAWYER>listedu= FXCollections.observableArrayList();
        try{
            PreparedStatement PS=conn.prepareStatement("SELECT * FROM lawpro.Lawyer ");
            ResultSet rs=PS.executeQuery();

            //Alert A=new Alert(Alert.AlertType.CONFIRMATION,"CONNECT SUCCESSFULL", ButtonType.OK);
           //  A.show();

            while (rs.next()){
                list.add(new LAWYER(Integer.parseInt(rs.getString("Lawyer_id")), rs.getString("FName"),rs.getString("LName"),rs.getString("City"),rs.getString("Phone_number"),rs.getString("Email"),(Integer.parseInt(rs.getString("Number_of_years_Professional_experience"))),
                        rs.getString("Sex"),(rs.getString("Bdate")),(Integer.parseInt(rs.getString("Street_number")))));

            }

        }
        catch (SQLException e){
            e.printStackTrace();
        }
       //  System.out.println("Number of recordslawyer: " + list.size());
        return list ;
    }














}
