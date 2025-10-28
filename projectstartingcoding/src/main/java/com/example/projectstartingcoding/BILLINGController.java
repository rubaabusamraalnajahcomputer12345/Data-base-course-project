package com.example.projectstartingcoding;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class BILLINGController implements Initializable {
    @FXML
    public Button PREVIOUS_BUTTONb;

    @FXML
    private Button ADDBILLINGBUTTON;

    @FXML
    private TextField BILLINGID_FIELD;

    @FXML
    private TableView<BILLING> BILLINGTABLE;

    @FXML
    private TextField CASEID_FIELD;

    @FXML
    private Button DELETEBILLINGBUTTON;

    @FXML
    private TextField EXPENSES_FIELD;

    @FXML
    private TextField HOURS_FIELD;

    @FXML
    private ComboBox  INVOICSTATUS_BOX;

    @FXML
    private TextField LAWYERID_FIELD;



    @FXML
    private TextField RATE_FIELD;

    @FXML
    private Button SEARCHBILLINGBUTTON;

    @FXML
    private TextField SEARCH_FIELD;

    @FXML
    private Button UPBILLINGBUTTON;

    @FXML
    private TableColumn<BILLING, Integer> billingid_col;

    @FXML
    private TableColumn<BILLING, Integer> caseid_col;

    @FXML
    private TableColumn<BILLING, Double> expenses_col;

    @FXML
    private TableColumn<BILLING, Integer> hours_col;

    @FXML
    private TableColumn<BILLING, String> invoicestatus_col;

    @FXML
    private TableColumn<BILLING, Integer> lid_col;

    @FXML
    private TableColumn<BILLING, Double> rate_col;


    int index = -1;
    ObservableList<BILLING> listM;
    ObservableList<BILLING> datalist;


    @FXML
    Connection conn = null;
    @FXML
    ResultSet res = null;
    @FXML
    PreparedStatement PS = null;



///THE DATA APPEAR IN TABLE

    @Override
    public void initialize(URL url, ResourceBundle rb) {



        billingid_col.setCellValueFactory(new PropertyValueFactory<BILLING, Integer>("billing_id"));//SET THE DATA IN A TABLE VIEW
        invoicestatus_col.setCellValueFactory(new PropertyValueFactory<BILLING, String>("invoice_status"));
        rate_col.setCellValueFactory(new PropertyValueFactory<BILLING, Double>("rate"));

        hours_col.setCellValueFactory(new PropertyValueFactory<BILLING, Integer>("hours"));//SET THE DATA IN A TABLE VIEW

        expenses_col.setCellValueFactory(new PropertyValueFactory<BILLING, Double>("expenses"));

        caseid_col.setCellValueFactory(new PropertyValueFactory<BILLING, Integer>("caseid"));
        lid_col.setCellValueFactory(new PropertyValueFactory<BILLING, Integer>("lawyer_id"));//SET THE DATA IN A TABLE VIEW

        try {
            listM = MYSQLCONNBILLING.getDatausers();
            //System.out.println("Number of records retrieved rubaahmad: " + listM.size());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        BILLINGTABLE.setItems(listM);
    }

///THE SELECTED DATA APPEAR IN FIELDS

    @FXML
    void getSelected(MouseEvent event) {
        index = BILLINGTABLE.getSelectionModel().getSelectedIndex();
        if (index <= -1) {
            return;
        }
        BILLINGID_FIELD.setText(billingid_col.getCellData(index).toString());
        CASEID_FIELD.setText(caseid_col.getCellData(index).toString());

        LAWYERID_FIELD.setText(lid_col.getCellData(index).toString());
ObservableList<String> selectdata= FXCollections.observableArrayList("Paid","Notpaid");
INVOICSTATUS_BOX.setItems(selectdata);
        RATE_FIELD.setText(rate_col.getCellData(index).toString());
        EXPENSES_FIELD.setText(expenses_col.getCellData(index).toString());
        HOURS_FIELD.setText(hours_col.getCellData(index).toString());

    }

////REFRESH TABLE AFTER DELETE AND UPDATE
@FXML
public void updatetablerefressh() throws SQLException {
    billingid_col.setCellValueFactory(new PropertyValueFactory<BILLING, Integer>("billing_id"));//SET THE DATA IN A TABLE VIEW
    invoicestatus_col.setCellValueFactory(new PropertyValueFactory<BILLING, String>("invoice_status"));
    rate_col.setCellValueFactory(new PropertyValueFactory<BILLING, Double>("rate"));

    hours_col.setCellValueFactory(new PropertyValueFactory<BILLING, Integer>("hours"));//SET THE DATA IN A TABLE VIEW

    expenses_col.setCellValueFactory(new PropertyValueFactory<BILLING, Double>("expenses"));

    caseid_col.setCellValueFactory(new PropertyValueFactory<BILLING, Integer>("caseid"));
    lid_col.setCellValueFactory(new PropertyValueFactory<BILLING, Integer>("lawyer_id"));//SET THE DATA IN A TABLE VIEW

    listM = MYSQLCONNBILLING.getDatausers();
    BILLINGTABLE.setItems(listM);
}

///DELETE DATA FROM TABLE AND DATABASE
    @FXML
    void deletebilling(ActionEvent event) {
        BILLING delete = BILLINGTABLE.getSelectionModel().getSelectedItem();
        conn = MYSQLCONNCASE.ConnectDB();
        String query = "DELETE  FROM lawpro.Billing WHERE \"Billing_id\" =? ";
        try {
            PS = conn.prepareStatement(query);
            PS.setInt(1, delete.getBilling_id());
            PS.execute();
           updatetablerefressh();
            searchbilling();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

// LOGIN TO ADD NEW BILLING FRAME
    @FXML
    void logintoaddnewbillingframe(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ADDBILLING.fxml"));
        Parent root = loader.load();
        Scene dashboardScene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(dashboardScene);
        window.show();
    }

    ///UPDATE SELECTED DATA
    @FXML
    void updatebilling(ActionEvent event) {


        try {
            conn = MYSQLCONNLAWYER.ConnectDB();
            String VALUE1 = BILLINGID_FIELD.getText();
            String VALUE2 = HOURS_FIELD.getText();
            String VALUE3= LAWYERID_FIELD.getText();
            String VALUE4 = CASEID_FIELD.getText();
            String VALUE5= EXPENSES_FIELD.getText();
            String VALUE6= RATE_FIELD.getText();
            String VALUE7= INVOICSTATUS_BOX.getSelectionModel().getSelectedItem().toString();

            String sql = "UPDATE lawpro.Billing set \"Billing_id\"='" + VALUE1 + "' ,\"Hours\"='" + VALUE2 + "',\"Lawyer_id\"=" + VALUE3 + ",\"Case_id\"=" + VALUE4 + ",\"Expenses\"=" + VALUE5 + ",\"Rate\"='" + VALUE6 +"',\"Invoice_status\"='" + VALUE7 +  "' WHERE \"Billing_id\"=" + VALUE1 + " " ;

            PS = conn.prepareStatement(sql);
            PS.execute();
            updatetablerefressh();
            searchbilling();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
@FXML
    void searchbilling() throws SQLException{
        billingid_col.setCellValueFactory(new PropertyValueFactory<BILLING, Integer>("billing_id"));//SET THE DATA IN A TABLE VIEW
        invoicestatus_col.setCellValueFactory(new PropertyValueFactory<BILLING, String>("invoice_status"));
        rate_col.setCellValueFactory(new PropertyValueFactory<BILLING, Double>("rate"));

        hours_col.setCellValueFactory(new PropertyValueFactory<BILLING, Integer>("hours"));//SET THE DATA IN A TABLE VIEW

        expenses_col.setCellValueFactory(new PropertyValueFactory<BILLING, Double>("expenses"));

        caseid_col.setCellValueFactory(new PropertyValueFactory<BILLING, Integer>("caseid"));
        lid_col.setCellValueFactory(new PropertyValueFactory<BILLING, Integer>("lawyer_id"));//SET THE DATA IN A TABLE VIEW

        datalist=MYSQLCONNBILLING.getDatausers();
        BILLINGTABLE.setItems(datalist);

        FilteredList<BILLING> filteredata=new FilteredList<>(datalist, b->true);
        SEARCH_FIELD.textProperty().addListener( (observable,oldValue,newValue)->{filteredata.setPredicate(person -> {
            if(newValue==null || newValue.isEmpty()){
                return true;
            }
            String lowerCaseFilter=newValue.toLowerCase();
            if (String.valueOf(person.getLawyer_id()).indexOf(lowerCaseFilter) !=-1) {
                return true;
            } else if(person.getInvoice_status().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                return true;
            }
            if (String.valueOf(person.getBilling_id()).indexOf(lowerCaseFilter) !=-1) {
                return true;}
            if (String.valueOf(person.getCaseid()).indexOf(lowerCaseFilter) !=-1) {
                return true;}
            if (String.valueOf(person.getRate()).indexOf(lowerCaseFilter) !=-1) {
                return true;}
            if (String.valueOf(person.getExpenses()).indexOf(lowerCaseFilter) !=-1) {
                return true;}
            else
                return false;


        });
        });
        SortedList<BILLING> sotedData=new SortedList<>(filteredata);
        sotedData.comparatorProperty().bind(BILLINGTABLE.comparatorProperty());
        BILLINGTABLE.setItems(sotedData);


    }













    @FXML
    protected void exitb (ActionEvent event) throws IOException {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("DASHBOARD FOR ADMIN.fxml"));
        Parent root=loader.load();
        Scene dashboardScene=new Scene(root);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(dashboardScene);
        window.show();
    }




    }