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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class LAWYERDATACONTROLLER  {
    @FXML
    private Button FINDINGBUTTON;

    @FXML
    private TableView<CASE> LAWYERCASESTABLE;

    @FXML
    private TextField LID_FIELD;

    @FXML
    private TableColumn<CASE, String> casestatus_col;

    @FXML
    private TableColumn<CASE, String> casetype_col;

    @FXML
    private TableColumn<CASE, Integer> cid_col;

    @FXML
    private TableColumn<CASE, Integer> clientid_col;

    @FXML
    private TableColumn<CASE, String> date_col;

    @FXML
    private TableColumn<CASE, Integer> lid_col;

    @FXML
    private TableView<TASK> TASKLAWYERTABLE;

    @FXML
    private TableColumn<TASK, String> taskdes_col;

    @FXML
    private TableColumn<TASK, Integer> taskid_col;

    @FXML
    private TableColumn<TASK, String> deadline_col;

    @FXML
    private TableColumn<TASK, Integer> lawyerid_col;
    @FXML
    private TableColumn<TASK, String> status_col;


    @FXML
    private TableView<APPOINTMENT> APPOINTMENTLAWYERTABLE;
    @FXML
    private TableColumn<APPOINTMENT, Integer> appointmentid_col;
    @FXML
    private TableColumn<APPOINTMENT, String> city_col;
    @FXML
    private TableColumn<APPOINTMENT, Integer> cidapp_col;
    @FXML
    private TableColumn<APPOINTMENT, String> courtname_col;
    @FXML
    private TableColumn<APPOINTMENT, String> dateapp_col;
    @FXML
    private TableColumn<APPOINTMENT, Integer> lidapp_col;
    @FXML
    private TableColumn<APPOINTMENT, String> etime_col;
    @FXML
    private TableColumn<APPOINTMENT, String> stime_col;
    @FXML
    private TableColumn<APPOINTMENT, Integer> street_col;

    @FXML
    private TableView<BILLING> LAWYERBILLINGSTABLE;
    @FXML
    private TableColumn<BILLING, Integer> bid_col;
    @FXML
    private TableColumn<BILLING, Integer> cidb_col;
    @FXML
    private TableColumn<BILLING, Double> expenses_col;
    @FXML
    private TableColumn<BILLING, Integer> hour_col;
    @FXML
    private TableColumn<BILLING, String> invoice_col;
    @FXML
    private TableColumn<BILLING, Integer> lidb_col;
    @FXML
    private TableColumn<BILLING, Double> rate_col;

    @FXML
    private Button LOGOUT_BUTTON;

    @FXML
    private TableView<CLIENT> MYCLIENTTABLE;
    @FXML
    private TableColumn<CLIENT, Integer> cidD_col;
    @FXML
    private TableColumn<CLIENT, String> bdC_col;
    @FXML
    private TableColumn<CLIENT, String> cityC_col;
    @FXML
    private TableColumn<CLIENT, String> emailC_col;
    @FXML
    private TableColumn<CLIENT, Integer> lidC_col;
    @FXML
    private TableColumn<CLIENT, String> nameC_col;

    @FXML
    private TableColumn<CLIENT, String> phoneC_col;
    @FXML
    private TableColumn<CLIENT, String> sexC_col;
    @FXML
    private TableColumn<CLIENT, Integer> streetC_col;

    @FXML
    private String DB_URL = "jdbc:postgresql://localhost:5432/postgres";


    @FXML
    public ObservableList<CASE> finddata(ActionEvent event) throws SQLException {
        String ID = LID_FIELD.getText();
        // Integer ID = (Integer.parseInt(LID_FIELD.getText()));
       // System.out.println("Number of recordslawyer: " + LID_FIELD.getText());
        Connection con = DriverManager.getConnection(DB_URL, "projectproject", "12345");
        ObservableList<CASE> list = FXCollections.observableArrayList();
        ObservableList<TASK> listtask = FXCollections.observableArrayList();
        ObservableList<APPOINTMENT>listapp= FXCollections.observableArrayList();
        ObservableList<BILLING>listbillings= FXCollections.observableArrayList();
        ObservableList<CLIENT>listCLIENT= FXCollections.observableArrayList();


        try {
            PreparedStatement PS = con.prepareStatement("SELECT * FROM lawpro.Case WHERE \"Lawyer_id\"=" + ID + " ");
            ResultSet rs = PS.executeQuery();
            while (rs.next()) {
               list.add(new CASE(Integer.parseInt(rs.getString("Case_id")), rs.getString("Case_type"), rs.getString("Case_status"), rs.getString("Important_date")
                        , (Integer.parseInt(rs.getString("Lawyer_id"))), (Integer.parseInt(rs.getString("Client_id")))));
           }
////////
            PreparedStatement PST = con.prepareStatement("SELECT * FROM lawpro.Task WHERE \"Lawyer_id\"="+ID+ " ");
            ResultSet rst = PST.executeQuery();
            while (rst.next()){
                listtask.add(new TASK(Integer.parseInt(rst.getString("Task_id")), rst.getString("Task_description"),rst.getString("Deadline"),rst.getString("Status"),(Integer.parseInt(rst.getString("Lawyer_id")))));
            }
            /////
            PreparedStatement PSA=con.prepareStatement("SELECT * FROM lawpro.Appointment WHERE \"Lawyer_id\"=" +ID+" ");
            ResultSet rsa=PSA.executeQuery();

            while (rsa.next()){
                listapp.add(new APPOINTMENT(Integer.parseInt(rsa.getString("Appointment_id")),  rsa.getString("Date"), rsa.getString("Start_time"),rsa.getString("End_time")
                        ,  rsa.getString("Court_name"), (Integer.parseInt(rsa.getString("Street_number"))) ,  rsa.getString("City") ,(Integer.parseInt(rsa.getString("Lawyer_id"))) ,(Integer.parseInt(rsa.getString("Client_id")))));

            }
///////
            PreparedStatement PSB=con.prepareStatement("SELECT * FROM lawpro.Billing  WHERE \"Lawyer_id\"=" +ID+" ");
            ResultSet rsb=PSB.executeQuery();
            while (rsb.next()){
                listbillings.add(new BILLING(Integer.parseInt(rsb.getString("Billing_id")),  Integer.parseInt(rsb.getString("Hours")), Double.parseDouble(rsb.getString("Rate")),Double.parseDouble(rsb.getString("Expenses"))
                        ,  rsb.getString("Invoice_status") ,(Integer.parseInt(rsb.getString("Lawyer_id")))  ,(Integer.parseInt(rsb.getString("Case_id")))));

            }

            PreparedStatement PSCL=con.prepareStatement("SELECT * FROM lawpro.Client WHERE \"Lawyer_id\"=" +ID+" ");
            ResultSet rsCL=PSCL.executeQuery();
            // Alert A=new Alert(Alert.AlertType.CONFIRMATION,"CONNECT SUCCESSFULL", ButtonType.OK);
            //  A.show();

            while (rsCL.next()){
                listCLIENT.add(new CLIENT(Integer.parseInt(rsCL.getString("Client_id")), rsCL.getString("Name"),rsCL.getString("City"),rsCL.getString("Phone_number"),rsCL.getString("Email"),
                        rsCL.getString("Sex"),(rsCL.getString("Bdate")),(Integer.parseInt(rsCL.getString("Lawyer_id"))) ,(Integer.parseInt(rsCL.getString("Street_number")))));
                // Alert A=new Alert(Alert.AlertType.CONFIRMATION,"CONNECT SUCCESSFULL", ButtonType.OK);
                // A.show();

            }







        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("Number of recordslawyerTTTT: " + listtask.size());
        clientid_col.setCellValueFactory(new PropertyValueFactory<CASE, Integer>("client_id"));//SET THE DATA IN A TABLE VIEW
        casestatus_col.setCellValueFactory(new PropertyValueFactory<CASE, String>("case_status"));
        date_col.setCellValueFactory(new PropertyValueFactory<CASE, String>("date"));
        lid_col.setCellValueFactory(new PropertyValueFactory<CASE, Integer>("lawyer_id"));//SET THE DATA IN A TABLE VIEW
        casetype_col.setCellValueFactory(new PropertyValueFactory<CASE, String>("case_type"));
        cid_col.setCellValueFactory(new PropertyValueFactory<CASE, Integer>("caseid"));
        LAWYERCASESTABLE.setItems(list);


        ///////
        taskid_col.setCellValueFactory(new PropertyValueFactory<TASK, Integer>("task_id")); //SET THE DATA IN A TABLE VIEW
        lawyerid_col.setCellValueFactory(new PropertyValueFactory<TASK, Integer>("lawyer_id"));//SET THE DATA IN A TABLE VIEW
        deadline_col.setCellValueFactory(new PropertyValueFactory<TASK, String>("deadline"));
        taskdes_col.setCellValueFactory(new PropertyValueFactory<TASK, String>("task_description"));
        status_col.setCellValueFactory(new PropertyValueFactory<TASK, String>("status"));
        TASKLAWYERTABLE.setItems(listtask);
//////

        cidapp_col.setCellValueFactory(new PropertyValueFactory<APPOINTMENT, Integer>("client_id"));//SET THE DATA IN A TABLE VIEW
        courtname_col.setCellValueFactory(new PropertyValueFactory<APPOINTMENT, String>("court_name"));
        dateapp_col.setCellValueFactory(new PropertyValueFactory<APPOINTMENT, String>("date"));
        appointmentid_col.setCellValueFactory(new PropertyValueFactory<APPOINTMENT, Integer>("appointment_id"));//SET THE DATA IN A TABLE VIEW
        stime_col.setCellValueFactory(new PropertyValueFactory<APPOINTMENT, String>("start_time"));
        etime_col.setCellValueFactory(new PropertyValueFactory<APPOINTMENT, String>("end_time"));
        lidapp_col.setCellValueFactory(new PropertyValueFactory<APPOINTMENT, Integer>("lawyer_id"));
        street_col.setCellValueFactory(new PropertyValueFactory<APPOINTMENT, Integer>("street_number"));
        city_col.setCellValueFactory(new PropertyValueFactory<APPOINTMENT, String>("city"));
        APPOINTMENTLAWYERTABLE.setItems(listapp);

        //////




        bid_col.setCellValueFactory(new PropertyValueFactory<BILLING, Integer>("billing_id"));//SET THE DATA IN A TABLE VIEW
        invoice_col.setCellValueFactory(new PropertyValueFactory<BILLING, String>("invoice_status"));
        rate_col.setCellValueFactory(new PropertyValueFactory<BILLING, Double>("rate"));
        hour_col.setCellValueFactory(new PropertyValueFactory<BILLING, Integer>("hours"));//SET THE DATA IN A TABLE VIEW
        expenses_col.setCellValueFactory(new PropertyValueFactory<BILLING, Double>("expenses"));
        cidb_col.setCellValueFactory(new PropertyValueFactory<BILLING, Integer>("caseid"));
        lidb_col.setCellValueFactory(new PropertyValueFactory<BILLING, Integer>("lawyer_id"));//SET THE DATA IN A TABLE VIEW
        LAWYERBILLINGSTABLE.setItems(listbillings);

///////

        cidD_col.setCellValueFactory(new PropertyValueFactory<CLIENT, Integer>("client_id"));//SET THE DATA IN A TABLE VIEW
        nameC_col.setCellValueFactory(new PropertyValueFactory<CLIENT, String>("name"));
        cityC_col.setCellValueFactory(new PropertyValueFactory<CLIENT, String>("city"));


        phoneC_col.setCellValueFactory(new PropertyValueFactory<CLIENT, String>("phonenumber"));
        emailC_col.setCellValueFactory(new PropertyValueFactory<CLIENT, String>("email"));
        sexC_col.setCellValueFactory(new PropertyValueFactory<CLIENT, String>("sex"));

        streetC_col.setCellValueFactory(new PropertyValueFactory<CLIENT, Integer>("streetnumber"));
        bdC_col.setCellValueFactory(new PropertyValueFactory<CLIENT, String>("BD"));
        lidC_col.setCellValueFactory(new PropertyValueFactory<CLIENT, Integer>("lawyer_id"));
MYCLIENTTABLE.setItems(listCLIENT);






        return list;
    }



    @FXML
    void logout(ActionEvent event) throws IOException {
Alert A=new Alert(Alert.AlertType.CONFIRMATION);
A.setTitle("LOGOUT");
A.setHeaderText("You are about to logout!");
A.setContentText("Do you want to logout?");
if(A.showAndWait().get()==ButtonType.OK) {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
    Parent root = loader.load();
    Scene dashboardScene = new Scene(root);
    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
    window.setScene(dashboardScene);
    window.show();

}

    }









}









