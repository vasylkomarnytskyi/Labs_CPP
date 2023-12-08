package com.example.bankingSystem;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.IOException;

public class Application extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("application.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 520, 720);
        stage.setTitle("BankingSystem");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.getIcons().add(new Image("/bank_icon.png"));
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}