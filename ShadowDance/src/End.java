import bagel.Input;
import bagel.Keys;

public class End extends Scene{

    private final static int RESULT_XPOSITION = 0;
    private final static int RESULT_YPOSITION = 300;
    private final static int RESULT_FONT_SIZE = 64;
    private final static int MESSAGE_FONT_SIZE = 24;
    private final static int MESSAGE_XPOSITION = 0;
    private final static int MESSAGE_YPOSITION = 500;
    private final static Text win = new Text(RESULT_FONT_SIZE, "CLEAR!", RESULT_XPOSITION, RESULT_YPOSITION);
    private final static Text lose = new Text(RESULT_FONT_SIZE, "TRY AGAIN", RESULT_XPOSITION, RESULT_YPOSITION);

    private final static Text message = new Text(MESSAGE_FONT_SIZE, "PRESS SPACE TO RETURN TO LEVEL SELECTION", MESSAGE_XPOSITION, MESSAGE_YPOSITION);
    public End(String result) {
        if (result.equals("Win")) {
            win.center();
            displays.add(win);
        }
        else{
            lose.center();
            displays.add(lose);
        }
        message.center();
        displays.add(message);

    }

    public void update(Input input){
        displayAll();

        if (input.wasPressed(Keys.SPACE)){
            ShadowDance.startScene();
        }
    }
}
