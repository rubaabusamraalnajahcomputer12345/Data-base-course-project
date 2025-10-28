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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ADDUSERController {

    @FXML
    private String DB_URL="jdbc:postgresql://localhost:5432/postgres";
    @FXML
    private Button CANCLEUSER_BUTTON;

    @FXML
    private TextField NEWLAWYERUSERID_FIELD;

    @FXML
    private TextField NEWPASSWORD_FIELD;

    @FXML
    private TextField NEWUSERID_FIELD;

    @FXML
    private Button SAVEUSER_BUTTON;

    @FXML
    void canclenewuser(ActionEvent event) throws IOException {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("DASHBOARD FOR ADMIN.fxml"));
        Parent root=loader.load();
        Scene dashboardScene=new Scene(root);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(dashboardScene);
        window.show();

    }

    @FXML
    void savenewuser(ActionEvent event){
        Connection con=null;

        try {
            con= DriverManager.getConnection(DB_URL,"projectproject","12345");
String qry="INSERT INTO lawpro.User (\"User_id\" , \"Password\" , \"Lawyer_id\")" +
        "values ('"+this.NEWUSERID_FIELD.getText()+"','"+this.NEWPASSWORD_FIELD.getText()+"',"+this.NEWLAWYERUSERID_FIELD.getText()+" )";
            Statement ST=con.createStatement();
            ST.executeUpdate(qry);
            Alert A=new Alert(Alert.AlertType.CONFIRMATION,"ADDING A NEW APPOINTMENT SUCCESSFULL", ButtonType.OK);
            A.show();
            con.close();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

}






