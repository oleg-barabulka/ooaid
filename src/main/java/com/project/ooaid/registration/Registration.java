package com.project.ooaid.registration;

import javafx.scene.Scene;
import lombok.AllArgsConstructor;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

import static com.project.ooaid.ErrorController.makeError;
@AllArgsConstructor
public class Registration {
    private String password;
    private String checkPassword;
    private String name;
    private String secondName;
    private String login;
    public void signup(String userType, Scene scene) {
        if (Objects.equals(password, checkPassword)) {
            FileInputStream in = null;
            Properties properties = new Properties();
            try {
                in = new FileInputStream("user.properties");
                properties.load(in);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            if (properties.getProperty(login) == null) {
                String userString = name + " " + secondName +
                        " " + password + " " + userType + " " + "Uncheck";
                properties.setProperty(login, userString);
                try {
                    in.close();
                    FileOutputStream out = new FileOutputStream("user.properties");
                    properties.store(out, "");
                    out.close();
                    scene.getWindow().hide();

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } else {
                makeError("already_existed_user.fxml");
            }
        } else {
            makeError("defferent_passwords-viewError.fxml");
        }
    }
}
