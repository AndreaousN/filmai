package com.example.filmai.controller;

import com.example.filmai.MainApplication;
import com.example.filmai.model.UserDAO;
import com.example.filmai.model.UserSingleton;
import com.example.filmai.utils.BCryptPassword;
import com.example.filmai.utils.Validation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {
    @FXML
    private Label loginStatus;

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    public void initialize() {
        Tooltip usernameTooltip = new Tooltip();
        usernameTooltip.setText("Must have length of 5-13\nCan contain upper and " +
                "lower case letters\nCan contain numbers and no special characters");
        Tooltip.install(username,usernameTooltip);

        Tooltip passwordTooltip = new Tooltip();
        passwordTooltip.setText("Must have length of 5-13 letters\nCan contain upper and " +
                "lower case letters\nCan contain numbers\nCan only contain these special" +
                "characters: !@#$%");
        Tooltip.install(password, passwordTooltip);
    }

    @FXML
    public void onLoginButtonClick(ActionEvent event) throws IOException {
        String nameOfTheUser = username.getText();
        String userPassword = password.getText();
        if (Validation.isValidUsername(nameOfTheUser) && Validation.isValidPassword(userPassword)) {
            //loginStatus.setText("Login successful");
            String passwordDataBase = UserDAO.getBCryptPassword(nameOfTheUser);
            if (passwordDataBase.isEmpty()) {
                loginStatus.setText("Wrong username or password");
            } else {
                boolean isValidPassword = BCryptPassword.checkPassword(userPassword, passwordDataBase);
                if (isValidPassword) {
                    loginStatus.setText("Successfully entered database username and password");
                    UserSingleton userSingleton = UserSingleton.getInstance();
                    userSingleton.setUsername(nameOfTheUser);
                    goToDashboard(event);
                } else {
                    loginStatus.setText("Wrong username or password");
                }
            }
        } else if (!Validation.isValidUsername(nameOfTheUser)) {
            loginStatus.setWrapText(true);
            loginStatus.setText("Invalid username. Hover text field to see username requirements");
        } else {
            loginStatus.setWrapText(true);
            loginStatus.setText("Invalid password. Hover text field to see password requirements");
        }
        //loginStatus.setText("Username: " + nameOfTheUser + " " + "Password: " + userPassword);
    }

    @FXML
    public void onSignUpButtonClick(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(MainApplication.class.getResource("Signup-view.fxml"));
        Stage signupStage = new Stage();
        signupStage.setTitle("Sign up");
        signupStage.setScene(new Scene(root, 400, 415));
        signupStage.show();
        //Kodas reikalingas paslepti pries tai buvusi langa
        ((Node) (event.getSource())).getScene().getWindow().hide();
    }

    @FXML
    public void goToDashboard(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(MainApplication.class.getResource("Dashboard-view.fxml"));
        Stage signupStage = new Stage();
        signupStage.setTitle("Dashboard");
        signupStage.setScene(new Scene(root, 850, 650));
        signupStage.show();
        //Kodas reikalingas paslepti pries tai buvusi langa
        ((Node) (event.getSource())).getScene().getWindow().hide();
    }
}