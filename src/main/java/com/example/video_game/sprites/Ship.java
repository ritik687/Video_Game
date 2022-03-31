package com.example.video_game.sprites;

import com.example.video_game.GameConfig;
import com.example.video_game.Main;
import com.example.video_game.sprites.Sprite;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.ArrayList;

public class Ship extends Sprite {
    //final is no longer something that can change, often refer to those as constants. or its that something that cannot be edited or change.

    // these basically written in upper case and it recognise as that dont change.
    private final int REFRESH_RATE =8;// refresh rate is just how many cycles go by before we allow another missile to be sent
    private int currentMissilePause; // this variable will keep track of how many clock cycles have happened and when it set to zero, we will set it back to 20..So basically it allows to put spacing between the shooting missiles.

    // container to hold the missiles
     private ArrayList<Missile> activeMissiles;



    /**
     * This is the constructor for the Sprite class
     * @param posX        - the left most position of the Sprite
     * @param posY        - the top position of the Sprite
     */
    public Ship(  int posX, int posY ) {
        super(posX, posY, GameConfig.getShip_width(), GameConfig.getShip_height(), GameConfig.getShip_speed());

        // the problem is that the super class needs the image to be set
        image = new Image(Main.class.getResource("images/ship.png").toExternalForm());
        // we have got two errors to deal with, the first is we need to change the superclass so that its not expecting an image to be passed in the constructor. And then we need to alter that image attribute so that we can access it from our subclass. Lets handle two items now.
        activeMissiles = new ArrayList<>();
        currentMissilePause = REFRESH_RATE;
    }

    public ArrayList<Missile> getActiveMissiles() {
        return activeMissiles;
    }


    /**
     * This method will decrease the y-coordinate based on the ship speed until it gets to 0
     */
    public void moveUp()
     {
        posY-= speed;
        if(posY < 0)
            posY=0;
     }

    /**
     * This method will increase the Y coordinate based on the ship speed until it reaches the bottom of the scene
     */
    public void moveDown()
    {
        int furthestDown= GameConfig.getGame_height()-GameConfig.getShip_height();
        posY+=speed;
        if(posY > furthestDown)
            posY = furthestDown;

    }

    /**
     * This method will move the ship to the left until it reaches the left most edge of the screen
     */
    public void moveLeft()
    {
        posX-=speed;
        if(posX <0)
            posX =0;

    }

    /**
     * This method will move the ship to the right until it reaches the far right side of the screen
     */
    // this method is overwriting what's in the Sprite class
    public void moveRight()
    {
        int furthestRight= GameConfig.getGame_width()-GameConfig.getShip_width();
        posX+=speed;
        if(posX > furthestRight)
            posX = furthestRight;
    }

    /**
     * This method will shoot a missile from the middle of the ship
     */
        public void shootMissile()
        {

            if (currentMissilePause <0){

                // our ship will keep track of these missiles
                // when we draw the ship, then we draw all the missiles...
                Missile newMissile = new Missile(posX+imageWidth, posY+imageHeight/2-GameConfig.getMissile_height()/2);
                activeMissiles.add(newMissile);
                currentMissilePause = REFRESH_RATE;
            }

        }

    /**
     * This method will draw the ship and then loop over all the active missiles to draw them
     * @param gc
     */
    public void draw(GraphicsContext gc) {

        currentMissilePause--;

        //calling the draw method that is in the Sprite,  but most importantly I only passed  one arguements. draw() is inherited from the Sprite. draw() is an example of Polymorphism.
        super.draw(gc);
        for (Missile missile : activeMissiles)
        {
            missile.draw(gc);
        }

    }
}
