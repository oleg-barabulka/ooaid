package com.project.ooaid.registration;

import com.project.ooaid.authorization.AuthorizationController;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.Properties;
import java.util.ResourceBundle;

import static com.project.ooaid.ErrorController.makeError;

public class RegistrationController implements Initializable {
    public TextField nameTextField;
    public TextField secondNameTextField;
    public TextField loginTextFIeld;
    public TextField passwordTextField;
    public TextField checkPasswordTextField;
    public Button registrationButton;
    public ChoiceBox<String> userTypeChoiceBox;

    private final String[] userType = {"User", "Laboratory Assistant", "Administrator"};

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        userTypeChoiceBox.getItems().addAll(userType);
        registrationButton.setOnAction(actionEvent -> {
            Registration registration = new Registration(passwordTextField.getText(), checkPasswordTextField.getText(),
                    nameTextField.getText(), secondNameTextField.getText(), loginTextFIeld.getText());
            registration.signup(userTypeChoiceBox.getValue(), registrationButton.getScene());
        });
    }
}
