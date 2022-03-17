package com.example.video_game;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;



public class GameBoardController {

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Button startButton;

    @FXML
    private void startGame(ActionEvent event)
    {
        startButton.setVisible(false);

        // we are going to use the tool called canvas or canvas class.... we draw on the canvas...Canvas needs a size, imagine if you are drawing on the canvas the size may be small and large..
        // there are two ways to set its dimension:- one ay to ask the anchor pane for its dimensions, thats the way to make it the exact same size && another way to do it is, we can set up a file for game constants

        //scene builder is just the layout.... its just a fancy tool for just laying out our view but what we are going to do is to paste this canvas over top of the entire view and we are going to set the timer and everytime the timer goes off we are going to redraw over top of the view.(like animation).

        // a canvas can be used to "draw" on. The GraphicsContext is the tool used for the drawing.

        //A canvas is not the scene  and it is an objext that gets added to the scene just like combobox is an object & label is an object...

        //when we draw on the canvas, we need some tools like pencil, marker, paintbrushes but in computer we called it the GraphicContext
        Canvas canvas = new Canvas(GameConfig.getGame_width(),GameConfig.getGame_height());
        GraphicsContext gc = canvas.getGraphicsContext2D(); // this is like asking the canvas to draw on it.




        // attach the canvas to the anchorpane like cloth canvas over the anchorpane so as to start drawing.
        anchorPane.getChildren().add(canvas);


    }

}