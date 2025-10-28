package com.example.projectstartingcoding;

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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
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

public class TASKController implements Initializable {
    @FXML
    public Button PREVIOUS_BUTTONt;


    @FXML
    private Button ADDTASKBUTTON;

    @FXML
    private TextField DEADLINE_FIELD;

    @FXML
    private Button DELETETASKBUTTON;

    @FXML
    private TextField LAWYERID_FIELD;

    @FXML
    private Button SEARCHTASKBUTTON;

    @FXML
    private TextField SEARCH_FIELD;

    @FXML
    private TextField STATUS_FIELD;

    @FXML
    private TextField TASKDES_FIELD;

    @FXML
    private TextField TASKID_FIELD;

    @FXML
    private Button UPTASKBUTTON;
    @FXML
    private TableView<TASK> TASKTABLE;
    @FXML
    private TableColumn<TASK, String> deadline_col;

    @FXML
    private TableColumn<TASK, Integer> lawyerid_col;

    @FXML
    private TableColumn<TASK, String> status_col;

    @FXML
    private TableColumn<TASK, String> taskdes_col;

    @FXML
    private TableColumn<TASK, Integer> taskid_col;
int index=-1;
    ObservableList<TASK> listM;
    ObservableList<TASK> datalist;


    @FXML
    Connection conn = null;
    @FXML
    ResultSet res = null;
    @FXML
    PreparedStatement PS = null;

    ////THE DATA APPEAR IN THE TABLE
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //clientid_col.setCellValueFactory(new PropertyValueFactory<DOCUMENT, Integer>("client_id"));//SET THE DATA IN A TABLE VIEW
        taskid_col.setCellValueFactory(new PropertyValueFactory<TASK, Integer>("task_id")); //SET THE DATA IN A TABLE VIEW
        lawyerid_col.setCellValueFactory(new PropertyValueFactory<TASK, Integer>("lawyer_id"));//SET THE DATA IN A TABLE VIEW

        deadline_col.setCellValueFactory(new PropertyValueFactory<TASK, String>("deadline"));
        taskdes_col.setCellValueFactory(new PropertyValueFactory<TASK, String>("task_description"));
        status_col.setCellValueFactory(new PropertyValueFactory<TASK, String>("status"));


        try {
            listM = MYSQLCONNTASK.getDatausers();
            //System.out.println("Number of records retrieved rubaahmad: " + listM.size());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        TASKTABLE.setItems(listM);

    }
// THE DATA APPEAR IN FIELDS IN FIELDS
    @FXML
    void getSelected(MouseEvent event) {
        index = TASKTABLE.getSelectionModel().getSelectedIndex();
        if (index <= -1) {
            return;
        }
        LAWYERID_FIELD.setText(lawyerid_col.getCellData(index).toString());
        TASKDES_FIELD.setText(taskdes_col.getCellData(index).toString());
        TASKID_FIELD.setText(taskid_col.getCellData(index).toString());
        DEADLINE_FIELD.setText(deadline_col.getCellData(index).toString());
        STATUS_FIELD.setText(status_col.getCellData(index).toString());
    }

//DELETE DATA OF TASK
    @FXML
    void deletetask(ActionEvent event) {

        TASK delete = TASKTABLE.getSelectionModel().getSelectedItem();
        conn = MYSQLCONNTASK.ConnectDB();
        String query = "DELETE  FROM lawpro.Task WHERE \"Task_id\" =? ";
        try {
            PS = conn.prepareStatement(query);
            PS.setInt(1, delete.getTask_id());
            PS.execute();
            updatetablerefressh();
            searchtask();

        } catch (Exception e) {
            throw new RuntimeException(e);


        }
    }
// LOGIN TO ADD TASK FRAME
    @FXML
    void logintoaddtaskframe(ActionEvent event) throws IOException {

        FXMLLoader loader=new FXMLLoader(getClass().getResource("ADDTASK.fxml"));
        Parent root=loader.load();
        Scene dashboardScene=new Scene(root);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(dashboardScene);
        window.show();


    }

// UPDATE DATA
    @FXML
    void updatetask(ActionEvent event) throws SQLException {
        try {
            conn = MYSQLCONN.ConnectDB();
            String VALUE1 = TASKID_FIELD.getText();
            String VALUE2 = DEADLINE_FIELD.getText();
            String VALUE3 = STATUS_FIELD.getText();
            String VALUE4 = LAWYERID_FIELD.getText();
            String VALUE5 = TASKDES_FIELD.getText();

            String sql = "UPDATE lawpro.Task set \"Task_id\"=" + VALUE1 + " ,\"Deadline\"='" + VALUE2 + "',\"Status\"= '" + VALUE3 + "' ,\"Lawyer_id\"= " + VALUE4 + " , \"Task_description\"='"+VALUE5+"'  WHERE \"Task_id\"=" +VALUE1+ " ";
            PS = conn.prepareStatement(sql);
            PS.execute();
             updatetablerefressh();
             searchtask();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }


    //REFRESH DATA
        @FXML
        public void updatetablerefressh() throws SQLException {
            //clientid_col.setCellValueFactory(new PropertyValueFactory<DOCUMENT, Integer>("client_id"));//SET THE DATA IN A TABLE VIEW
            taskid_col.setCellValueFactory(new PropertyValueFactory<TASK, Integer>("task_id")); //SET THE DATA IN A TABLE VIEW
            lawyerid_col.setCellValueFactory(new PropertyValueFactory<TASK, Integer>("lawyer_id"));//SET THE DATA IN A TABLE VIEW

            deadline_col.setCellValueFactory(new PropertyValueFactory<TASK, String>("deadline"));
            taskdes_col.setCellValueFactory(new PropertyValueFactory<TASK, String>("task_description"));
            status_col.setCellValueFactory(new PropertyValueFactory<TASK, String>("status"));
            listM = MYSQLCONNTASK.getDatausers();
            TASKTABLE.setItems(listM);

        }

    @FXML
    void searchtask() throws SQLException{

        taskid_col.setCellValueFactory(new PropertyValueFactory<TASK, Integer>("task_id")); //SET THE DATA IN A TABLE VIEW
        lawyerid_col.setCellValueFactory(new PropertyValueFactory<TASK, Integer>("lawyer_id"));//SET THE DATA IN A TABLE VIEW

        deadline_col.setCellValueFactory(new PropertyValueFactory<TASK, String>("deadline"));
        taskdes_col.setCellValueFactory(new PropertyValueFactory<TASK, String>("task_description"));
        status_col.setCellValueFactory(new PropertyValueFactory<TASK, String>("status"));
        datalist=MYSQLCONNTASK.getDatausers();
        TASKTABLE.setItems(datalist);

        FilteredList<TASK> filteredata=new FilteredList<>(datalist, b->true);
        SEARCH_FIELD.textProperty().addListener( (observable,oldValue,newValue)->{filteredata.setPredicate(person -> {
            if(newValue==null || newValue.isEmpty()){
                return true;
            }
            String lowerCaseFilter=newValue.toLowerCase();
            if (String.valueOf(person.getTask_id()).indexOf(lowerCaseFilter) !=-1) {
                return true;
            }

            if(person.getDeadline().toLowerCase().indexOf(lowerCaseFilter) !=-1){
                return true;}
            if(person.getTask_description().toLowerCase().indexOf(lowerCaseFilter) !=-1){
                return true;}

            if(person.getStatus().toLowerCase().indexOf(lowerCaseFilter) !=-1){
                return true;}

            if (String.valueOf(person.getLawyer_id()).indexOf(lowerCaseFilter) !=-1) {
                return true;}
            else
                return false;
        });
        });
        SortedList<TASK> sotedData=new SortedList<>(filteredata);
        sotedData.comparatorProperty().bind(TASKTABLE.comparatorProperty());
        TASKTABLE.setItems(sotedData);


    }






















    @FXML
    protected void exitt (ActionEvent event) throws IOException {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("DASHBOARD FOR ADMIN.fxml"));
        Parent root=loader.load();
        Scene dashboardScene=new Scene(root);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(dashboardScene);
        window.show();
    }

}
