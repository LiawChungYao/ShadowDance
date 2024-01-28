public class SpeedUp extends SpecialNote{
    private final static int SPEEDUP_SCORE = 15;
    private final static int SPEEDUP_MAGNITUDE = 1;

    public SpeedUp(String dir, int appearanceFrame, int xPos) {
        super(dir, appearanceFrame, xPos);
    }

    @Override
    protected int effect(Lane lane) {

        deactivate();
        noteSpeedChange(SPEEDUP_MAGNITUDE);
        return SPEEDUP_SCORE;
    }
}
