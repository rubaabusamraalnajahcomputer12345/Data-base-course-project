package com.example.projectstartingcoding;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import com.example.projectstartingcoding.LOGINController;
import java.io.IOException;
import javafx.application.Application;

public class LOGINFRAME extends Application {

    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(LOGINFRAME.class.getResource("LOGIN.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 700, 500);
       stage.setTitle("WELCOME USER!");
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }

}


