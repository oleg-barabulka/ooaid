package com.project.ooaid;

import com.project.ooaid.fromMisha.SwingPaint;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ErrorController implements Initializable {

    @FXML
    public Button okButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        okButton.setOnAction(actionEvent -> {
            okButton.getScene().getWindow().hide();
        });
    }

    static public void makeError(String fxmlName){
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource(fxmlName));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 400, 200);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Stage stage = new Stage();
        stage.setTitle("Ошибка");
        stage.setScene(scene);
        stage.showAndWait();
    }
}
