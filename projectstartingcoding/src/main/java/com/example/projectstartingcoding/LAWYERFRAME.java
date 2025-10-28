package com.example.projectstartingcoding;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class LAWYERFRAME extends Application {

    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(LOGINFRAME.class.getResource("LAWYER.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 700, 500);
        stage.setTitle("WELCOME USER!");
        stage.setScene(scene);
        stage.show();
}


    public static void main(String[] args) {
        launch();
    }




}
