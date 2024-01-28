import bagel.*;
public class Sprite implements Display{
    final static int WINDOW_WIDTH = 1024;
    final static int WINDOW_HEIGHT = 768;
    private Image picture;
    private int xPos;
    private int yPos;

    public Sprite(Image picture, int xPos, int yPos) {
        this.picture = picture;
        this.xPos = xPos;
        this.yPos = yPos;
    }

    /**
     * Draws the image at the position (x,y)
     */
    public void draw(){
        picture.draw(xPos,yPos);
    }

    /**
     * Draws the image at the position (x,y)
     * will perform Draw Options configuration
     */
    public void draw(DrawOptions rotation){
        picture.draw(xPos,yPos, rotation);
    }

    /**
     * Set the position of the Sprite
     */
    public void setPosition(int x, int y){
        xPos = x;
        yPos = y;
    }

    /**
     * Returns the current X position of the Sprite
     */
    public int getxPos() {
        return xPos;
    }

    /**
     * Returns the current Y position of the Sprite
     */
    public int getyPos() {
        return yPos;
    }

    /**
     * Returns true if the Sprite is out of the Window
     */
    boolean outOfBounds(){
        return xPos < 0 || yPos < 0 || xPos > WINDOW_WIDTH || yPos > WINDOW_HEIGHT;
    }
}
