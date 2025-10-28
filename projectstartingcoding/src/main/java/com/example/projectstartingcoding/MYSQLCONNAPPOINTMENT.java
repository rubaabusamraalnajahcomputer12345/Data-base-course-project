package com.example.projectstartingcoding;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

import java.sql.*;

public class MYSQLCONNAPPOINTMENT {


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
    public static ObservableList<APPOINTMENT> getDatausers() throws SQLException {
        Connection conn=ConnectDB();
        // con= DriverManager.getConnection(DB_URL,"projectproject","12345");
        ObservableList<APPOINTMENT>list= FXCollections.observableArrayList();
        try{
            PreparedStatement PS=conn.prepareStatement("SELECT * FROM lawpro.Appointment ");
            ResultSet rs=PS.executeQuery();
            // Alert A=new Alert(Alert.AlertType.CONFIRMATION,"CONNECT SUCCESSFULL", ButtonType.OK);
            //  A.show();

            while (rs.next()){
                list.add(new APPOINTMENT(Integer.parseInt(rs.getString("Appointment_id")),  rs.getString("Date"), rs.getString("Start_time"),rs.getString("End_time")
                        ,  rs.getString("Court_name"), (Integer.parseInt(rs.getString("Street_number"))) ,  rs.getString("City") ,(Integer.parseInt(rs.getString("Lawyer_id"))) ,(Integer.parseInt(rs.getString("Client_id")))));
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
