package com.example.filmai.controller;

import com.example.filmai.MainApplication;
import com.example.filmai.model.User;
import com.example.filmai.model.UserDAO;
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

public class SignupController {
    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    private PasswordField confirmedPassword;

    @FXML
    private TextField email;

    @FXML
    private Label signupStatus;

    @FXML
    private RadioButton isAdmin;

    @FXML
    private RadioButton isUser;

    @FXML
    public void initialize() {
        Tooltip usernameTooltip = new Tooltip();
        usernameTooltip.setText("Must have length of 5-13\nCan contain upper and " +
                "lower case letters\nCan contain numbers and no special characters");
        Tooltip.install(username,usernameTooltip);

        Tooltip passwordTooltip = new Tooltip();
        passwordTooltip.setText("Must have length of 5-13 letters\nCan contain upper and " +
                "lower case letters\nCan contain numbers\nCan only contain these special " +
                "characters: !@#$%");
        Tooltip.install(password, passwordTooltip);

        Tooltip confirmedPasswordTooltip = new Tooltip();
        confirmedPasswordTooltip.setText("Must match the password");
        Tooltip.install(confirmedPassword, confirmedPasswordTooltip);

        Tooltip emailTooltip = new Tooltip();
        emailTooltip.setText("Must have length of 5-30 letters\nCan contain upper and " +
                "lower case letters\nCan contain numbers\nMust have '@' character");
        Tooltip.install(email,emailTooltip);
    }

    @FXML
    public void onSignUpButtonClick(ActionEvent event) throws IOException {
        String nameOfTheUser = username.getText();
        String userPassword = password.getText();
        String confirmedUserPassword = confirmedPassword.getText();
        String userEmail = email.getText();
        if (!Validation.isValidUsername(nameOfTheUser)) {
            signupStatus.setWrapText(true);
            signupStatus.setText("Invalid username. Hover text field to see username requirements");
        } else if (!Validation.isValidPassword(userPassword)) {
            signupStatus.setWrapText(true);
            signupStatus.setText("Invalid password. Hover text field to see password requirements");
        } else if (!userPassword.equals(confirmedUserPassword)) {
            signupStatus.setWrapText(true);
            signupStatus.setText("Passwords do not match");
        } else if (!Validation.isValidEmail(userEmail) || !userEmail.contains("@")) {
            signupStatus.setWrapText(true);
            signupStatus.setText("Invalid email. Hover text field to see email requirements");
        } else {
            signupStatus.setText("Login successful");
            String encryptedBCryptPassword = BCryptPassword.hashPassword(userPassword);
            User user1 = new User(nameOfTheUser, encryptedBCryptPassword, userEmail, isAdmin.isSelected());
            UserDAO.create(user1);
            goToSignInWindow(event);
        }
    }

    @FXML
    public void goToSignInWindow(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(MainApplication.class.getResource("Login-view.fxml"));
        Stage signupStage = new Stage();
        signupStage.setTitle("Sign in");
        signupStage.setScene(new Scene(root, 350, 281));
        signupStage.show();
        ((Node) (event.getSource())).getScene().getWindow().hide();
    }
}
