public abstract class SpecialNote extends Note{

    protected int inRange = 50;
    private String dir;
    private final static int BOMB_SCORE = 0;

    public SpecialNote(String dir, int appearanceFrame, int xPos, String imgName) {
        super(imgName, appearanceFrame, xPos);
        this.dir = dir;
    }
    public SpecialNote(String dir, int appearanceFrame, int xPos) {
        super(dir, appearanceFrame, xPos);
        this.dir = dir;
    }

    /**
     * The Score of Special Notes are different based on the type of Special Note
     * checkScore is overridden to return the Special Note Scores
     */
    public int checkScore(int targetHeight, Lane lane) {
        int score = 0;
        if ((getDistance(targetHeight) <= inRange)) {
            Accuracy.setAccuracy(dir);
            score = effect(lane);
        }

        return score;
    }

    /**
     * Plays the special effect when the Special Note is hit
     */
    protected abstract int effect(Lane lane);

    /**
     * Change the current speed of notes
     */
    protected static void noteSpeedChange(int magnitude){
        int newSpeed = Note.getSpeed() + magnitude;
        Note.setSpeed(newSpeed);
    }
}
