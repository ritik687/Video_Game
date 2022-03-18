module com.example.video_game {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.video_game to javafx.fxml;
    exports com.example.video_game;
    exports com.example.video_game.sprites;
    opens com.example.video_game.sprites to javafx.fxml;
}