package com.example.video_game;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("game-board.fxml"));
        Scene scene = new Scene(fxmlLoader.load()); // removing the dimensions of the window that we get when  we run the main(), fxml also has the dimension
        stage.setTitle("Alien Attack");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}