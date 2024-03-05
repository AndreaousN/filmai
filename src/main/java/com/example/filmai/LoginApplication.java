package com.example.filmai;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(LoginApplication.class.getResource("Login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 350, 281);
        //scene.getStylesheets().add(getClass().getResource( "style.css").toExternalForm());
        //scene.getStylesheets().add("style.css");
        //scene.getStylesheets().getClass().getResource("style.css");
        stage.setTitle("Sign in");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}