package com.project.ooaid.authorization;

import com.project.ooaid.Application;
import com.project.ooaid.authorization.Authorization;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AuthorizationController implements Initializable {
    @FXML
    protected Button logInButton;
    @FXML
    protected TextField loginTextField;
    @FXML
    protected TextField passwordTextField;
    @FXML
    protected ChoiceBox<String> userTypeChoiceBox;
    @FXML
    protected Button registrationButton;

    private final String[] userType = {"User", "Laboratory Assistant", "Administrator"};

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        userTypeChoiceBox.getItems().addAll(userType);
        logInButton.setOnAction(actionEvent -> {
            Authorization authorization = new Authorization(loginTextField.getText(), passwordTextField.getText(), logInButton.getScene());
            authorization.login();
        });
        registrationButton.setOnAction(actionEvent -> {
            FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("registration-view.fxml"));
            Scene scene = null;
            try {
                scene = new Scene(fxmlLoader.load(), 700, 400);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Stage stage = new Stage();
            stage.setTitle("Регистрация");
            stage.setScene(scene);
            stage.showAndWait();
        });
    }

}