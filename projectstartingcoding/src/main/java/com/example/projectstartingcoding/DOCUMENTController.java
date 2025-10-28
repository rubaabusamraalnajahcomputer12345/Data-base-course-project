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

public class DOCUMENTController implements Initializable {
    @FXML
    private Button ADDDOCBUTTON;

    @FXML
    private TextField CASEID_FIELD;

    @FXML
    private TextField CID_FIELD;

    @FXML
    private TextField CREATIONDATE_FIELD;

    @FXML
    private Button DELETEDOCBUTTON;

    @FXML
    private TextField DI_FIELD;

    @FXML
    private TextField DT_FIELD;

    @FXML
    private Button PREVIOUS_BUTTONd;

    @FXML
    private Button SEARCHDOCBUTTON;

    @FXML
    private TextField SEARCH_FIELD;

    @FXML
    private Button UPDOCBUTTON;

    @FXML
    private TableView<DOCUMENT> DOCUMENTTABLE;
    @FXML
    private TableColumn<DOCUMENT, Integer> caseid_col;


    @FXML
    private TableColumn<DOCUMENT, String> date_col;

    @FXML
    private TableColumn<DOCUMENT, String> doctittle_col;

    @FXML
    private TableColumn<DOCUMENT, Integer> documentid_col;
    int index = -1;
    ObservableList<DOCUMENT> listM;
    ObservableList<DOCUMENT> datalist;


    @FXML
    Connection conn = null;
    @FXML
    ResultSet res = null;
    @FXML
    PreparedStatement PS = null;

    /////THE DATA APPEAR IN THE TABLEVIEW
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //clientid_col.setCellValueFactory(new PropertyValueFactory<DOCUMENT, Integer>("client_id"));//SET THE DATA IN A TABLE VIEW
        caseid_col.setCellValueFactory(new PropertyValueFactory<DOCUMENT, Integer>("case_id")); //SET THE DATA IN A TABLE VIEW
        documentid_col.setCellValueFactory(new PropertyValueFactory<DOCUMENT, Integer>("document_id"));//SET THE DATA IN A TABLE VIEW

        doctittle_col.setCellValueFactory(new PropertyValueFactory<DOCUMENT, String>("doctittle"));
        date_col.setCellValueFactory(new PropertyValueFactory<DOCUMENT, String>("date"));


        try {
            listM = MYSQLCONNDOCUMENT.getDatausers();
            //System.out.println("Number of records retrieved rubaahmad: " + listM.size());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DOCUMENTTABLE.setItems(listM);

    }


////THE DATA APPEAR THE TEXTS FIELD WHEN I SELECT IT FROM THE TABLE
    @FXML
    void getSelected(MouseEvent event) {
        index = DOCUMENTTABLE.getSelectionModel().getSelectedIndex();
        if (index <= -1) {
            return;
        }
        CASEID_FIELD.setText(caseid_col.getCellData(index).toString());
        DI_FIELD.setText(documentid_col.getCellData(index).toString());
        DT_FIELD.setText(doctittle_col.getCellData(index).toString());
        CREATIONDATE_FIELD.setText(date_col.getCellData(index).toString());
    }

////REFRESH THE TABLE
@FXML
public void updatetablerefressh() throws SQLException {
        caseid_col.setCellValueFactory(new PropertyValueFactory<DOCUMENT, Integer>("case_id")); //SET THE DATA IN A TABLE VIEW
    documentid_col.setCellValueFactory(new PropertyValueFactory<DOCUMENT, Integer>("document_id"));//SET THE DATA IN A TABLE VIEW
    doctittle_col.setCellValueFactory(new PropertyValueFactory<DOCUMENT, String>("doctittle"));
    date_col.setCellValueFactory(new PropertyValueFactory<DOCUMENT, String>("date"));
    listM = MYSQLCONNDOCUMENT.getDatausers();
    DOCUMENTTABLE.setItems(listM);
}



///DELETE THE DATA FROM THE TABLE
    @FXML
    void deletedocument(ActionEvent event) {

        DOCUMENT delete = DOCUMENTTABLE.getSelectionModel().getSelectedItem();
        conn = MYSQLCONNDOCUMENT.ConnectDB();
        String query = "DELETE  FROM lawpro.Document WHERE \"Document_id\" =? ";
        try {
            PS = conn.prepareStatement(query);
            PS.setInt(1, delete.getDocument_id());
            PS.execute();
             updatetablerefressh();
            searchdocument();

        } catch (Exception e) {
            throw new RuntimeException(e);


        }
    }

        @FXML
        void logintoadddocumentframe (ActionEvent event) throws IOException {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("ADDDOCUMENT.fxml"));
            Parent root=loader.load();
            Scene dashboardScene=new Scene(root);
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(dashboardScene);
            window.show();


        }

        @FXML
        void updatedocument (ActionEvent event){
            try {
                conn=MYSQLCONN.ConnectDB();
                String VALUE1=DI_FIELD.getText();
                String VALUE2=DT_FIELD.getText();
                String VALUE3=CREATIONDATE_FIELD.getText();
                String VALUE4=CASEID_FIELD.getText();

                String sql="UPDATE lawpro.Document set \"Document_id\"="+VALUE1+" ,\"Document_title\"='"+VALUE2+"',\"Creation_date\"='"+VALUE3+"' ,\"Case_id\"="+VALUE4+"  WHERE \"Document_id\"="+VALUE1+" ";
                PS=conn.prepareStatement(sql) ;
                PS.execute();
                updatetablerefressh();
               searchdocument();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }


    @FXML
    void searchdocument() throws SQLException{
        caseid_col.setCellValueFactory(new PropertyValueFactory<DOCUMENT, Integer>("case_id")); //SET THE DATA IN A TABLE VIEW
        documentid_col.setCellValueFactory(new PropertyValueFactory<DOCUMENT, Integer>("document_id"));//SET THE DATA IN A TABLE VIEW
        doctittle_col.setCellValueFactory(new PropertyValueFactory<DOCUMENT, String>("doctittle"));
        date_col.setCellValueFactory(new PropertyValueFactory<DOCUMENT, String>("date"));

        datalist=MYSQLCONNDOCUMENT.getDatausers();
       DOCUMENTTABLE.setItems(datalist);

        FilteredList<DOCUMENT> filteredata=new FilteredList<>(datalist, b->true);
        SEARCH_FIELD.textProperty().addListener( (observable,oldValue,newValue)->{filteredata.setPredicate(person -> {
            if(newValue==null || newValue.isEmpty()){
                return true;
            }
            String lowerCaseFilter=newValue.toLowerCase();
            if (String.valueOf(person.getCase_id()).indexOf(lowerCaseFilter) !=-1) {
                return true;
            }

            if(person.getDate().toLowerCase().indexOf(lowerCaseFilter) !=-1){
                return true;}
            if(person.getDoctittle().toLowerCase().indexOf(lowerCaseFilter) !=-1){
                return true;}
            if (String.valueOf(person.getDocument_id()).indexOf(lowerCaseFilter) !=-1) {
                return true;}
            else
                return false;
        });
        });
        SortedList<DOCUMENT> sotedData=new SortedList<>(filteredata);
        sotedData.comparatorProperty().bind(DOCUMENTTABLE.comparatorProperty());
       DOCUMENTTABLE.setItems(sotedData);


    }



































        @FXML
        protected void exitd (ActionEvent event) throws IOException {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("DASHBOARD FOR ADMIN.fxml"));
            Parent root = loader.load();
            Scene dashboardScene = new Scene(root);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(dashboardScene);
            window.show();
        }



}