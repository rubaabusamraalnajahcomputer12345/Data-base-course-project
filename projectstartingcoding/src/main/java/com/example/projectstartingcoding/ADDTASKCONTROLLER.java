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

public class ADDTASKCONTROLLER {
    @FXML
    private String DB_URL="jdbc:postgresql://localhost:5432/postgres";


    @FXML
    private Button CANCLE_BUTTON;

    @FXML
    private TextField DEADLINE_FIELD;

    @FXML
    private TextField LAWYERID_FIELD;

    @FXML
    private Button SAVE_BUTTON;

    @FXML
    private TextField STATUS_FIELD;

    @FXML
    private TextField TASKDES_FIELD;

    @FXML
    private TextField TASKID_FIELD;

    @FXML
    void addnewtask(ActionEvent event) {

        Connection con=null;

        try {
            con= DriverManager.getConnection(DB_URL,"projectproject","12345");

            String qry="INSERT INTO lawpro.Task(\"Task_id\" , \"Task_description\" , \"Deadline\" , \"Status\" , \"Lawyer_id\" )" +
                    " values ("+this.TASKID_FIELD.getText()+" , '"+this.TASKDES_FIELD.getText()+"' , '"+this.DEADLINE_FIELD.getText()+"' , '"+this.STATUS_FIELD.getText()+"' ,"+this.LAWYERID_FIELD.getText()+" )";

            Statement ST=con.createStatement();
            ST.executeUpdate(qry);
            Alert A=new Alert(Alert.AlertType.CONFIRMATION,"ADDING A NEW APPOINTMENT SUCCESSFULL", ButtonType.OK);
            A.show();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();  // Handle the exception appropriately


        }



    }

    @FXML
    void cancleaddnewtask(ActionEvent event) throws IOException {

        FXMLLoader loader=new FXMLLoader(getClass().getResource("TASK.fxml"));
        Parent root=loader.load();
        Scene dashboardScene=new Scene(root);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(dashboardScene);
        window.show();




    }



}
