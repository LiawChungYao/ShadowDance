import bagel.*;

/**
 * Class for hold notes
 */
public class HoldNote extends Note{

    private static final int HEIGHT_OFFSET = 82;
    protected int holdNoteY = 24;
    private boolean holdStarted = false;

    public HoldNote(String dir, int appearanceFrame, int xPos) {
        super(dir, appearanceFrame, xPos);
        y = holdNoteY;
        image = new Sprite(new Image("res/holdNote" + dir + ".png"),xPos,y);
    }


    /**
     * Set holdStarted boolean to true
     */
    public void startHold() {
        holdStarted = true;
    }

    /**
     *  Location of the Hold Note
     *  If holdStarted is false, the y-return will be the bottom of the Hold Note image
     *  If holdStarted is true, the y-return will be the top of the Hold Note image
     */
    protected int getCurrLocation(){
        if (holdStarted){
            return y - HEIGHT_OFFSET;
        }
        else{
            return y + HEIGHT_OFFSET;
        }
    }

    /**
     * If the note is a registered hit (within range)
     * Normal Note will just deactivate
     */

    /**
     * Checks if the hit is first or second
     * If it is first, Initialize Hold State in Note and Lane
     * If it is second, treat as a normal Note
     * @param lane
     */
    protected void hit(Lane lane){
        if(holdStarted){
            deactivate();
            lane.setCurrHoldNote(false);
        }
        else {
            // Deactivate the Note
            lane.setCurrHoldNote(true);
            startHold();
        }
    }
}
