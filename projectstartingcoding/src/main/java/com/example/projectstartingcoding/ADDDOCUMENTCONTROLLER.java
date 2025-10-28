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

public class ADDDOCUMENTCONTROLLER {

    @FXML
    private Button ADDNEWLAWYER;

    @FXML
    private Button CANCLEADDING_BUTTON;

    @FXML
    private TextField CASEID_FIELD;

    @FXML
    private TextField DOCUMENTDATE_FIELD;

    @FXML
    private TextField DOCUMENTID_FIELD;

    @FXML
    private TextField DOCUMENTTITLE_FIELD;

    @FXML
    private String DB_URL="jdbc:postgresql://localhost:5432/postgres";


    ///ADDING NEW DOCUMENT

    @FXML
    void addnewdocument(ActionEvent event)  {


        Connection con=null;

        try {
            con= DriverManager.getConnection(DB_URL,"projectproject","12345");

            String qry="INSERT INTO lawpro.Document(\"Document_id\" , \"Document_title\" , \"Case_id\" , \"Creation_date\" )" +
                    " values ("+this.DOCUMENTID_FIELD.getText()+" , '"+this.DOCUMENTTITLE_FIELD.getText()+"' , "+this.CASEID_FIELD.getText()+" , '"+this.DOCUMENTDATE_FIELD.getText()+"')";

            Statement ST=con.createStatement();
            ST.executeUpdate(qry);
            Alert A=new Alert(Alert.AlertType.CONFIRMATION,"ADDING A NEW APPOINTMENT SUCCESSFULL", ButtonType.OK);
            A.show();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();  // Handle the exception appropriately


        }


    }



    ////CANCLE ADDING FRAME

    @FXML
    void cancleaddingdocument(ActionEvent event) throws IOException {

        FXMLLoader loader=new FXMLLoader(getClass().getResource("DOCUMENT.fxml"));
        Parent root=loader.load();
        Scene dashboardScene=new Scene(root);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(dashboardScene);
        window.show();



    }



}
