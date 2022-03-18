package com.example.video_game;

import javafx.scene.image.Image;

public class Ship extends Sprite{
    /**
     * This is the constructor for the Sprite class
     * @param posX        - the left most position of the Sprite
     * @param posY        - the top position of the Sprite
     */
    public Ship(  int posX, int posY ) {
        super( posX, posY,GameConfig.getShip_width(),GameConfig.getShip_height(),GameConfig.getShip_speed());

        // the problem is that the super class needs the image to be set
        image = new Image(Main.class.getResource("images/ship.png").toExternalForm());
        // we have got two errors to deal with, the first is we need to change the superclass so that its not expecting an image to be passed in the constructor. And then we need to alter that image attribute so that we can access it from our subclass. Lets handle two items now.
    }
}
