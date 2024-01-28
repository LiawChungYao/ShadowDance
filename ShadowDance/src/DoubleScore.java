public class DoubleScore extends SpecialNote{
    private final static int DOUBLESCORE_MAGNITUDE = 2;
    private final static int DOUBLESCORE_SCORE = 0;

    public DoubleScore(String dir, int appearanceFrame, int xPos, String imgName) {
        super(dir, appearanceFrame, xPos, imgName);
    }

    @Override
    protected int effect(Lane lane) {

        deactivate();
        Level.setScoreMultiplier(DOUBLESCORE_MAGNITUDE);
        return DOUBLESCORE_SCORE;
    }
}
