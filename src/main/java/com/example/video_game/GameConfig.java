package com.example.video_game;

// in this we can set the sizing for everthing like for screen, ships and everything..
public class GameConfig {
    // we say instances static, that means all game config objects share the exact they share the variable
        private static int game_width = 1000;
        private static int game_height =800;

    public static int getGame_width() {
        return game_width;
    }

    public static void setGame_width(int game_width) {
        GameConfig.game_width = game_width;
    }

    public static int getGame_height() {
        return game_height;
    }

    public static void setGame_height(int game_height) {
        GameConfig.game_height = game_height;
    }
}
