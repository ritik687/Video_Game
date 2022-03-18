package com.example.video_game.sprites;

import com.example.video_game.GameConfig;
import com.example.video_game.Main;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Alien extends Sprite{
    /**
     * This is the constructor for the Sprite class
     *
     * @param posX        - the left most position of the Sprite
     * @param posY        - the top position of the Sprite

     */
    public Alien(int posX, int posY) {
        super(posX, posY, GameConfig.getAlien_width(),GameConfig.getAlien_height(),GameConfig.getAlien_speed());
        image = new Image(Main.class.getResource("images/alien.png").toExternalForm());
    }

    /**
     * the alien will move from the right side of the game to the left. When the alien gets to the far left side it will reappear on the right side
     */
    public void moveLeft()
    {
        posX-=speed;
        if(posX < 0)
            posX = GameConfig.getGame_width();// it means when the alien position get to x=0 then it will come again through the right hand side and start coming across again.
    }

    public void draw(GraphicsContext gc)
    {
        super.draw(gc);
        moveLeft();
    }

}
