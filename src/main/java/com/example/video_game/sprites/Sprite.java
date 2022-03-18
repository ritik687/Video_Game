package com.example.video_game.sprites;

import com.example.video_game.GameConfig;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

// abstract means you cannot implement it on its own you have to have a sub-class implemented
public abstract class Sprite {

    //protected status means that they can only be accessed by this class or the sub-class(Ship)
    protected Image image;
    protected int posX,posY,imageWidth,imageHeight,speed;
    private boolean alive;

    /**
     * This is the constructor for the Sprite class
     * @param posX - the left most position of the Sprite
     * @param posY - the top position of the Sprite
     * @param imageWidth - the width of the image when drawn
     * @param imageHeight - the height of the image when drawn
     * @param speed - how many pixels the Sprite can move
     */
    public Sprite(int posX, int posY, int imageWidth, int imageHeight,int speed) {

        setPosX(posX);
        setPosY(posY);
        setImageWidth(imageWidth);
        setImageHeight(imageHeight);
        setSpeed(speed);
        alive = true;
    }

    public Image getImage() {
        return image;
    }

    // there are not lot of validations for the image, its already an image object. Constructor for the image has already validated it.
    public void setImage(Image image) {
        this.image = image;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        //furthestRight -> our image can be drawn and still be visible on the canvas so as long as we're inside that range we're good to go.
        int furthestRight = GameConfig.getGame_width()-imageWidth;
        if(posX>=0 && posX<=furthestRight)
            this.posX = posX;
        else
            throw new IllegalArgumentException("posX must be in the range of 0-"+furthestRight);
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        int furthestDown = GameConfig.getGame_height()-imageHeight;
        if(posY>=0 && posY <= furthestDown)
            this.posY = posY;
        else
            throw new IllegalArgumentException(String.format("posY must be in the range of 0-%d",furthestDown));
        // exception always need a string in the bracket;
    }

    public int getImageWidth() {
        return imageWidth;
    }

    public void setImageWidth(int imageWidth) {
        this.imageWidth = imageWidth;
    }

    public int getImageHeight() {
        return imageHeight;
    }

    public void setImageHeight(int imageHeight) {
        this.imageHeight = imageHeight;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }


    public void draw(GraphicsContext gc)
    {

//        gc.drawImage(image,posX,posY,imageWidth,imageHeight);

        // if missile hit the aliens, it will not draw the aliens any more....
        if(alive)
            gc.drawImage(image,posX,posY,imageWidth,imageHeight);
    }

    //aliens, missiles and ship are all sprites, they will all use this method, so they will inherit it automatically, so we dont have to place this to three or four different places
    public boolean collidesWith(Sprite sprite)
    {
        return ((posX + imageWidth/2 > sprite.posX) &&
                (posX < sprite.posX + sprite.imageWidth/2 )  &&
                (posY + imageHeight/2 > sprite.posY) &&
                (posY < sprite.posY + sprite.imageHeight/2));
    }


}
