import bagel.*;

import java.util.ArrayList;

/**
 * Class for the lanes which notes fall down
 */
public class Lane {
    private static final int HEIGHT = 384;
    private static final int TARGET_HEIGHT = 657;
    private final String type;
    private final Sprite image;
    private int holdLimit = 20; // Limit for hold notes
    private int normalLimit = 100; // Limit for normal notes
    private ArrayList<Note> notes = new ArrayList<>(); // Notes that have not been active
    private ArrayList<Note> active = new ArrayList<>(); // Notes that are active
    private Keys relevantKey;
    private final int location; // X Position of Lane
    private boolean currHoldNote = false;

    private Level level;

    public Lane(String dir, int location, Level level) {
        this.type = dir;
        this.location = location;
        this.isDone = false;
        this.level = level;
        image = new Sprite(new Image("res/lane" + dir + ".png"), location, HEIGHT);
        switch (dir) {
            case "Left":
                relevantKey = Keys.LEFT;
                break;
            case "Right":
                relevantKey = Keys.RIGHT;
                break;
            case "Up":
                relevantKey = Keys.UP;
                break;
            case "Down":
                relevantKey = Keys.DOWN;
                break;
            case "Special":
                relevantKey = Keys.SPACE;
                break;
        }
    }

    public String getType() {
        return type;
    }
    public Keys getRelevantKey() {
        return relevantKey;
    }

    private boolean isDone;

    /**
     * updates all the notes in the lane
     */
    public int update(Input input, int currFrame) {

        int score = 0;
        // Update Notes
        for (Note note: active){
            note.update();
        }

        removeDeactivate();
        if (!(active.isEmpty())) {
            Note choice = active.get(0);
            // Key Pressed
            if (input.wasPressed(getRelevantKey()) ||(currHoldNote && input.wasReleased(getRelevantKey()))) {
                score += choice.checkScore(TARGET_HEIGHT, this);
            }

            // Out of bounds
            score += choice.outOfScreen();
        }
        else{
            if(notes.isEmpty()){
                // End of Lane
                isDone = true;
                level.gameFinished();
            }
        }



        // Start Notes
        if (!(notes.isEmpty()) && notes.get(0).getAppearanceFrame() <= currFrame){
            active.add(notes.get(0));
            notes.remove(0);
        }

        return score;
    }

    /**
     * Adds note to the ArrayList
     * based on the type
     * and initialize the note with their start Frame
     */
    public void addNote(String type, int startFrame) {

        System.out.println(type);
        /**
         * Add notes to an arraylist of notes
        */
        switch(type){
            case"Hold":
                System.out.println("1");
                notes.add(new HoldNote(this.type, startFrame, location));
                break;
            case"Normal":
                System.out.println("2");
                notes.add(new Note(this.type, startFrame, location));
                break;
            case "DoubleScore":
                System.out.println("3");
                notes.add(new DoubleScore(type, startFrame, location,"2x"));
                break;
            case "Bomb":
                System.out.println("4");
                notes.add(new BombNote(type, startFrame, location));
                break;
            case "SpeedUp":
                System.out.println("5");
                notes.add(new SpeedUp(type, startFrame, location));
                break;
            case "SlowDown":
                System.out.println("5");
                notes.add(new SlowDown(type, startFrame, location));
                break;
        }
    }


    /**
     * Draws the lane and the notes
     */
    public void display() {
        image.draw();
        for(Note note: active){
            if (note.isActive()) {
                note.draw();
            }
        }
    }

    /**
     * Returns true if the lane is empty and have no more notes
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Clear all active notes in the lane
     */
    public void clearLane(){
        active = new ArrayList<>();
    }

    /**
     * Set the boolean
     * To see if the current active note is being held
     * @param currHoldNote
     */
    public void setCurrHoldNote(boolean currHoldNote) {
        this.currHoldNote = currHoldNote;
    }

    /**
     * Remove all deactivated frames in the lane
     */
    private void removeDeactivate(){
        while(!(active.isEmpty()) && !(active.get(0).isActive())){
            active.remove(0);
        }
    }

    /**
     * Returns the Sprite of the Lane
     */
    public Sprite getImage() {
        return image;
    }

    /**
     * Returns the active notes in the lane
     */
    public ArrayList<Note> getActiveNotes() {
        return active;
    }
}
