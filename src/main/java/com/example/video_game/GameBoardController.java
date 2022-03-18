package com.example.video_game;


import com.example.video_game.sprites.Alien;
import com.example.video_game.sprites.Missile;
import com.example.video_game.sprites.Ship;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashSet;


public class GameBoardController {

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Button startButton;

    private HashSet<KeyCode> activeKeys;

    @FXML
    private void startGame(ActionEvent event)
    {
        startButton.setVisible(false);
        activeKeys = new HashSet<>();

        // asking the anchorPane to get the scene and the scene has a method called setOnKeyReleased();
        // This is an example of an anonymous inner class [new EventHandler<KeyEvent>()]
        anchorPane.getScene().setOnKeyPressed(new EventHandler<KeyEvent>() {
            // abstract method called handle, need to be implemented
            @Override
            public void handle(KeyEvent keyPressed) {
                System.out.println(keyPressed.getCode()+ "-> active keys"+activeKeys);
                    activeKeys.add(keyPressed.getCode());
                    // getCode() is something that we can reference for the key
                    // What will happen now, whenever we pressed the key, we add it into our set of active keys, so if I push the left button, I am going to store the left button..
            }
        });


        anchorPane.getScene().setOnKeyReleased(new EventHandler<KeyEvent>() {
            // abstract method called handle, need to be implemented
            @Override
            public void handle(KeyEvent keyRealeased) {
                System.out.println(keyRealeased.getCode()+ "-> active keys"+activeKeys);
                activeKeys.remove(keyRealeased.getCode());
                // What will happen now, whenever we pressed the key, we add it into our set of active keys, so if I push the left button, I am going to store the left button..
            }
        });



        // we are going to use the tool called canvas or canvas class.... we draw on the canvas...Canvas needs a size, imagine if you are drawing on the canvas the size may be small and large..
        // there are two ways to set its dimension:- one ay to ask the anchor pane for its dimensions, thats the way to make it the exact same size && another way to do it is, we can set up a file for game constants

        //scene builder is just the layout.... its just a fancy tool for just laying out our view but what we are going to do is to paste this canvas over top of the entire view and we are going to set the timer and everytime the timer goes off we are going to redraw over top of the view.(like animation).

        // a canvas can be used to "draw" on. The GraphicsContext is the tool used for the drawing.

        //A canvas is not the scene  and it is an objext that gets added to the scene just like combobox is an object & label is an object...

        //when we draw on the canvas, we need some tools like pencil, marker, paintbrushes but in computer we called it the GraphicContext
        Canvas canvas = new Canvas(GameConfig.getGame_width(),GameConfig.getGame_height());
        GraphicsContext gc = canvas.getGraphicsContext2D(); // this is like asking the canvas to draw on it.


        //we need to load some images to draw the background and the ship.....why we are doing this, so when we put the canvas, its going to overlap everything else and we gonna lose our background if we dont do this....
        //getClass().getResource("images/space.png").toExternalForm() -> this basically going to translate into a path to the file,so the image class can load it.
        Image background= new Image(getClass().getResource("images/space.png").toExternalForm());


        //create the Ship sprite
        Ship ship = new Ship(100,100 );
        Missile missile =new Missile(100,100);// this line will be useful, if we remove the comment from the missile.draw(gc);

        //create a few aliens
        /*Alien alien1 = new Alien(600,400);
        Alien alien2 = new Alien(700,400);
        Alien alien3 = new Alien(400,400); we cant add manually 30 or 40 aliens it will consume time */
        SecureRandom rng = new SecureRandom(); // secure randome has lot of uses than the simple random class
        ArrayList<Alien> aliens = new ArrayList<>();
        for (int i =1;i<=10;i++) {
            // here we are setting the orgin and bounds for the aliens,,, how these aliens should move where ?
            aliens.add(new Alien(rng.nextInt(500, GameConfig.getGame_width()), rng.nextInt(0, GameConfig.getGame_height()-GameConfig.getAlien_height())));
        }





        AnimationTimer timer =new AnimationTimer() {
            // when the timer trigger, it will call the handle method each time...
            @Override
            public void handle(long l){
                gc.drawImage(background,0,0,GameConfig.getGame_width(),GameConfig.getGame_height());

                //missile.draw(gc);

                //update the ship positon and draw it
                updateShipLocation(ship);
                ship.draw(gc);

                //draw the aliens
                  /*alien1.draw(gc);
                    alien2.draw(gc);
                    alien3.draw(gc);*/
                for(Alien alien : aliens)
                {
                    alien.draw(gc);
                }




            }
        };

        timer.start();


        // attach the canvas to the anchorpane like cloth canvas over the anchorpane so as to start drawing.
        anchorPane.getChildren().add(canvas);


    }

    /**
     * This method will update the location of the ship based on the keys pressed. This method is outside the block of the startGame method
     */
    private void updateShipLocation(Ship ship)
    {
        //KeyCode.DOWN -> this is like a standard value, so i can check to see if the activeKeys contains that and then called the appropriate method..We can say that the KeyCode.DOWN is the numeric value of Down...

        if(activeKeys.contains(KeyCode.DOWN))
            ship.moveDown();
        if(activeKeys.contains(KeyCode.UP) || activeKeys.contains("W"))
            ship.moveUp();
        if(activeKeys.contains(KeyCode.RIGHT))
            ship.moveRight();
        if(activeKeys.contains(KeyCode.LEFT))
            ship.moveLeft();
        if(activeKeys.contains(KeyCode.SPACE))
            ship.shootMissile();

    }


}
