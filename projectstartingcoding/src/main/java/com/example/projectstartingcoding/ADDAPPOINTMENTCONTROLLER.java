package com.example.projectstartingcoding;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.time.format.DateTimeFormatter;

public class ADDAPPOINTMENTCONTROLLER {



    @FXML
    private TextField APPID_FIELD;

    @FXML
    private Button CANCLEBUTTON;

    @FXML
    private TextField CITY_FIELD;

    @FXML
    private TextField CLIENTID_FIELD;

    @FXML
    private TextField CNAME_FIELD;

    @FXML
    private TextField DATE_FIELD;

    @FXML
    private TextField ENDTIME_FIELD;

    @FXML
    private TextField LID_FIELD;

    @FXML
    private Button SAVBUTTON;

    @FXML
    private TextField STARTTIME_FIELD;

    @FXML
    private TextField STREETNUM_FIELD;
    private String DB_URL="jdbc:postgresql://localhost:5432/postgres";

    @FXML
    void addnewappointment(ActionEvent event) {
        Connection con = null;

        try {
            con = DriverManager.getConnection(DB_URL, "projectproject", "12345");


            String qry = "INSERT INTO lawpro.Appointment(\"Lawyer_id\" , \"Court_name\" , \"Date\" , \"Street_number\" , \"City\" , \"Start_time\" , \"End_time\" , \"Client_id\", \"Appointment_id\" )" +
                    " values (" + this.LID_FIELD.getText() + ",'" + this.CNAME_FIELD.getText() + "','" + this.DATE_FIELD.getText() + "'," + this.STREETNUM_FIELD.getText() + ",'" + this.CITY_FIELD.getText() + "','" + this.STARTTIME_FIELD.getText() + "','" + this.ENDTIME_FIELD.getText() + "'," + this.CLIENTID_FIELD.getText() + "," + this.APPID_FIELD.getText() + ")";

            Statement ST = con.createStatement();
            ST.executeUpdate(qry);
            con.close();
            Alert A=new Alert(Alert.AlertType.CONFIRMATION,"ADDING A NEW APPOINTMENT SUCCESSFULL", ButtonType.OK);
            A.show();
        } catch (SQLException e) {
            e.printStackTrace();  // Handle the exception appropriately

        }}

        @FXML
        void cancleaddnewappointment (ActionEvent event) throws IOException {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("APPOINTMENT.fxml"));
            Parent root = loader.load();
            Scene dashboardScene = new Scene(root);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(dashboardScene);
            window.show();
           // window.setFullScreen(true);
        }

    }


