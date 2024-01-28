import bagel.*;
public class Start extends Scene{
    // Starting Screen

    private final static int GAME_TITLE_XPOSITION = 220;
    private final static int GAME_TITLE_YPOSITION = 250;
    private final static int GAME_TITLE_FONT_SIZE = 64;
    private final static int LEVEL_XPOSITION = 320;
    private final static int LEVEL_YPOSITION = 440;
    private final static int LEVEL_FONT_SIZE = 24;

    private final static Image GUARDIAN_IMAGE = new Image("res/guardian.png");
    private final static Text gameTitle = new Text(GAME_TITLE_FONT_SIZE, "SHADOW DANCE", GAME_TITLE_XPOSITION, GAME_TITLE_YPOSITION);
    private final static Text levels = new Text(LEVEL_FONT_SIZE, "SELECT LEVELS WITH\nNUMBER KEYS\n\n    1       2      3",LEVEL_XPOSITION, LEVEL_YPOSITION);

    public Start() {
        displays.add(gameTitle);
        gameTitle.center();
        displays.add(levels);
    }

    @Override
    public void update(Input input){
        displayAll();

        if (input.wasPressed(Keys.NUM_1)){
            ShadowDance.levelScene(1);
        }
        if (input.wasPressed(Keys.NUM_2)){
            ShadowDance.levelScene(2);
        }
        if (input.wasPressed(Keys.NUM_3)){
            ShadowDance.levelScene(3);
        }
    }
}
