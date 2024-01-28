import bagel.*;
/**
 * Skeleton Code for SWEN20003 Project 2, Semester 2, 2023
 * Please enter your name below
 * @author Chung Yao Liaw
 */
public class ShadowDance extends AbstractGame  {

    // Application
    private final static int WINDOW_WIDTH = 1024;
    private final static int WINDOW_HEIGHT = 768;
    private final static String GAME_TITLE = "SHADOW DANCE";
    private final Image BACKGROUND_IMAGE = new Image("res/background.png");

    private static Scene currentScene;

    private ShadowDance(){
        super(WINDOW_WIDTH, WINDOW_HEIGHT, GAME_TITLE);
        currentScene = new Start();
    }


    /**
     * The entry point for the program.
     */
    public static void main(String[] args) {
        ShadowDance game = new ShadowDance();
        game.run();
    }

    /**
     * Performs a state update.
     * Allows the game to exit when the escape key is pressed.
     */
    @Override
    protected void update(Input input) {
        if (input.wasPressed(Keys.ESCAPE)){
            Window.close();
        }
        BACKGROUND_IMAGE.draw(Window.getWidth()/2.0, Window.getHeight()/2.0);
        currentScene.update(input);

    }

    /**
     * Changes from Start scene to level scene
     * Choosing the level
     */
    public static void levelScene(int level){
            currentScene = new Level(level);
    }

    /**
     * Changes from Level scene to End scene
     * Giving the result
     */
    public static void endScene(String result){
        currentScene = new End(result);
    }

    /**
     * Changes from End Scene to Start scene
     */
    public static void startScene(){
        currentScene = new Start();
    }
}
