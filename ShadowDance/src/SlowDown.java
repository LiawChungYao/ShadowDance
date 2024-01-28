public class SlowDown extends SpecialNote{
    private final static int SLOWDOWN_SCORE = 15;
    private final static int SLOWDOWN_MAGNITUDE = -1;


    public SlowDown(String dir, int appearanceFrame, int xPos) {
        super(dir, appearanceFrame, xPos);
    }

    @Override
    protected int effect(Lane lane) {

        deactivate();
        noteSpeedChange(SLOWDOWN_MAGNITUDE);
        return SLOWDOWN_SCORE;
    }
}
