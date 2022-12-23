package com.project.ooaid.authorization;

import com.project.ooaid.fromMisha.SwingPaint;
import javafx.scene.Scene;
import lombok.AllArgsConstructor;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static com.project.ooaid.ErrorController.makeError;
@AllArgsConstructor
public class Authorization {
    private String login;
    private String password;
    private Scene scene;

    public void login(){
        FileInputStream in = null;
        Properties properties = new Properties();
        try {
            in = new FileInputStream("user.properties");
            properties.load(in);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if (properties.getProperty(login) != null){
            String[] user = properties.getProperty(login).split(" ");
            if (user[2].equals(password)){
                if (user[3].equals("User")){
                    scene.getWindow().hide();
                    SwingPaint swingPaint = new SwingPaint();
                    swingPaint.show();
                }else if (user[4].equals("Uncheck")){
                    makeError("not_approved_user-viewError.fxml");
                }else if(user[3].equals("Laboratory Assistant")){
                    //Приложуха лаборанта
                }else if(user[3].equals("Administrator")){
                    //приложуха администратора
                }
            } else{
                makeError("wrong_password-viewError.fxml");
            }
        } else{
            makeError("no_such_user-viewError.fxml");
        }
    }

}
