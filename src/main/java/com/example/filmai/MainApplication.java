package com.example.filmai;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        // vaizdo uzkrovimas
        FXMLLoader loginView = new FXMLLoader(MainApplication.class.getResource("Login-view.fxml"));
        //sukurimas scenos is vaizdo
        Scene scene = new Scene(loginView.load(), 350, 281);
        //scene.getStylesheets().add(getClass().getResource( "style.css").toExternalForm());
        //scene.getStylesheets().add("style.css");
        //scene.getStylesheets().getClass().getResource("style.css");

        //Stage (langas) bus vienas, scenu gali buti daug
        stage.setTitle("Sign in");
        // Langui priskiriama scena
        stage.setScene(scene);
        // paroodymas lango
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}