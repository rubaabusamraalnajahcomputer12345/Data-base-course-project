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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class ADDBILLINGCONTROLLER implements Initializable {
    @FXML
    private String DB_URL="jdbc:postgresql://localhost:5432/postgres";


    @FXML
    private Button ADDINGBUTTON;

    @FXML
    private TextField BILLINGID_FIELD;

    @FXML
    private Button CANCLEBUTTON;

    @FXML
    private TextField CASEIDFIELD;

    @FXML
    private TextField EXPENSESFIELD;

    @FXML
    private TextField HOURSFIELD;

    @FXML
    private ComboBox INVOICESTATUSBOX;

    @FXML
    private TextField LAWYERIIDFIELD;

    @FXML
    private TextField RATEFIELD;


    @Override
    public void initialize(URL url, ResourceBundle rb) {

        ObservableList<String> listcasestatus= FXCollections.observableArrayList("Paid","Notpaid");
        INVOICESTATUSBOX.setItems(listcasestatus);

    }


//CANCLE ADDING DATA

    @FXML
    void cancleadding(ActionEvent event) throws IOException {

        FXMLLoader loader=new FXMLLoader(getClass().getResource("BILLING.fxml"));
        Parent root=loader.load();
        Scene dashboardScene=new Scene(root);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(dashboardScene);
        window.show();

    }


    ///ADD NEW BILLING
    @FXML
    void savethenewdata(ActionEvent event) {

        Connection con = null;

        try {
            con = DriverManager.getConnection(DB_URL, "projectproject", "12345");
            String qry = "INSERT INTO lawpro.Billing(\"Case_id\" , \"Lawyer_id\" , \"Billing_id\" , \"Hours\" , \"Invoice_status\" ,\"Rate\" ,\"Expenses\")" +
                    " values (" + this.CASEIDFIELD.getText() + ",'" + this.LAWYERIIDFIELD.getText() + "','" + this.BILLINGID_FIELD.getText() + "'," + this.HOURSFIELD.getText() + ",'" + this.INVOICESTATUSBOX.getSelectionModel().getSelectedItem().toString() + "','" + this.RATEFIELD.getText() + "' ,'"+ this.EXPENSESFIELD.getText()+ "')";

            Statement ST = con.createStatement();
            ST.executeUpdate(qry);
            Alert A=new Alert(Alert.AlertType.CONFIRMATION,"ADD NEW CLIENT SUCCESSFUL", ButtonType.OK);
            A.show();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();  // Handle the exception appropriately

        }


    }


}
