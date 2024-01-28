public class BombNote extends SpecialNote{
    private final static int BOMB_SCORE =0;


    public BombNote(String dir, int appearanceFrame, int xPos) {
        super(dir, appearanceFrame, xPos);
    }


    @Override
    public int checkScore(int targetHeight, Lane lane) {

        int score = 0;
        if ((getDistance(targetHeight) <= inRange)) {
            score = effect(lane);
        }
        return Accuracy.NOT_SCORED;
    }

    protected int effect(Lane lane){
        lane.clearLane();
        return BOMB_SCORE;
    }

    public int outOfScreen(){

        if (image.outOfBounds()) {
            deactivate();
        }
        return Accuracy.NOT_SCORED;
    }
}
