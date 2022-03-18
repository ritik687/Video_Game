package com.example.video_game.sprites;

import com.example.video_game.GameConfig;
import com.example.video_game.Main;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Missile extends Sprite{
    /**
     * This is the constructor for the Sprite class
     * @param posX        - the left most position of the Sprite
     * @param posY        - the top position of the Sprite
     */
    public Missile(int posX, int posY) {
        super(posX, posY, GameConfig.getMissile_width(),GameConfig.getMissile_height(),GameConfig.getMissile_speed());
        image = new Image(Main.class.getResource("images/missile.png").toExternalForm());
    }

    private void moveRight()
    {
        posX+=speed;
        // if missile drawn off of our scene, missile is no longer of use...
        if(posX > GameConfig.getGame_width())
            setAlive(false);
    }

    // for drawing
    public void draw(GraphicsContext gc)
    {
        // calling the super class method to draw
        super.draw(gc);
        moveRight();
    }
}
