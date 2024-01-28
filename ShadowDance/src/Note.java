import bagel.*;

/**
 * Class for normal notes
 */
public class Note {
    protected Sprite image;
    protected int appearanceFrame;
    protected int y = 100;
    private boolean active = true;
    private static int speed = 2;


    public Note(String dir, int appearanceFrame, int xPos) {
        image = new Sprite(new Image("res/note" + dir + ".png"),xPos,y);
        this.appearanceFrame = appearanceFrame;
    }

    public boolean isActive() {
        return active;
    }

    public void deactivate() {
        active = false;
    }

    public void update() {
        y += speed;
        image.setPosition(image.getxPos(), y);
    }

    public void draw(){
        image.draw();
    }

    public int getAppearanceFrame() {
        return appearanceFrame;
    }

    /**
     * Compares note current y position with target height
     * Get feedback from accuracy
     * Conduct actions if it is within range
     */
    public int checkScore(int targetHeight, Lane lane) {
        Feedback feedback = Accuracy.getFeedback(getDistance(targetHeight));
        int score = Accuracy.evaluateScore(feedback);
        if (score != Accuracy.NOT_SCORED) {
            hit(lane);
        }
        return score;
    }

    /**
     * If the note is a registered hit (within range)
     * Normal Note will just deactivate
     */
    protected void hit(Lane lane){
        // Deactivate the Note
        deactivate();
    }

    /**
     * If the Note is out of the Window
     * Note will be deactivated
     * and register miss score
     */
    public int outOfScreen(){
        if (image.outOfBounds()) {
            deactivate();
            return Accuracy.evaluateScore(Accuracy.MISS);
        }

        return Accuracy.NOT_SCORED;
    }

    /**
     * Get the current location of the center of the Normal Note
     * @return y - position of the Object
     */
    protected int getCurrLocation(){
        return y;
    }

    /**
     * Get the distance between the Y value of the Note and input
     */
    protected int getDistance(int yPos){
        return Math.abs(getCurrLocation() - yPos);
    }

    /**
     * Set the speed of the Note to input
     */
    public static void setSpeed(int speed) {
        Note.speed = speed;
    }

    /**
     * Get the current speed of Note
     */
    public static int getSpeed() {
        return speed;
    }
}
