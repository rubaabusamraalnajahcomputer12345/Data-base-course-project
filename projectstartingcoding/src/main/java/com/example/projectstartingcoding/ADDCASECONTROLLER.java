package com.example.projectstartingcoding;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class ADDCASECONTROLLER implements Initializable {
    @FXML
    private String DB_URL="jdbc:postgresql://localhost:5432/postgres";

    @FXML
    private Button CANCLEADDING_BUTTON;

    @FXML
    private TextField CASEID_FIELD;

    @FXML
    private ComboBox CASESTATUS_BOX;

    @FXML
    private ComboBox CASETYPE_BOX;

    @FXML
    private TextField CLIENTID_FIELD;

    @FXML
    private TextField DATE_FIELD;

    @FXML
    private TextField LAWYERID_FIELD;

    @FXML
    private Button addnewcase_button;


    @Override
    public void initialize(URL url, ResourceBundle rb) {

        ObservableList<String>listcasestatus=FXCollections.observableArrayList("Pending","In Progress","Completed");
        CASESTATUS_BOX.setItems(listcasestatus);

        ObservableList<String>listcasetype=FXCollections.observableArrayList("Civil","Administrative","Criminal");
        CASETYPE_BOX.setItems(listcasetype);

    }

    ///ADD NEW CASE
    @FXML
    void addnewcase(ActionEvent event) {
        Connection con = null;

        try {
            con = DriverManager.getConnection(DB_URL, "projectproject", "12345");
            String qry = "INSERT INTO lawpro.Case(\"Case_id\" , \"Important_date\" , \"Lawyer_id\" , \"Client_id\" , \"case_type\" ,\"case_status\")" +
                    " values (" + this.CASEID_FIELD.getText() + ",'" + this.DATE_FIELD.getText() + "','" + this.LAWYERID_FIELD.getText() + "'," + this.CLIENTID_FIELD.getText() + ",'" + this.CASETYPE_BOX.getSelectionModel().getSelectedItem().toString() + "','" + this.CASESTATUS_BOX.getSelectionModel().getSelectedItem().toString() + "')";

            Statement ST = con.createStatement();
            ST.executeUpdate(qry);
            Alert A=new Alert(Alert.AlertType.CONFIRMATION,"ADD NEW CLIENT SUCCESSFUL", ButtonType.OK);
            A.show();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();  // Handle the exception appropriately

        }
    }
    @FXML
    void cancleaddingcase(ActionEvent event) throws IOException {

        FXMLLoader loader=new FXMLLoader(getClass().getResource("CASE.fxml"));
        Parent root=loader.load();
        Scene dashboardScene=new Scene(root);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(dashboardScene);
        window.show();

    }




}
