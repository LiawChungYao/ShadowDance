import bagel.Image;
import bagel.Input;
import bagel.Keys;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Objects;

public class Level extends Scene{

    // Before Gameplay
    private boolean level3;
    private final static int startingSpeed = 2;

    // During Gameplay
    private boolean paused = false;
    private int score = 0;
    private final ArrayList<Lane> lanes = new ArrayList<>();
    private final static int SCORE_TEXT_SIZE = 30;
    private final static int SCORE_TEXT_POS = 35;

    private final static Text scoreText = new Text(SCORE_TEXT_SIZE, "SCORE = 0", SCORE_TEXT_POS, SCORE_TEXT_POS);
    private int currFrame = 0;
    private final Track track;
    private static int scoreMultiplier = 1;


        // Double Score

    private final static int ACTIVE_FRAMES = 480;
    private static int doubleScoreFrames;

        // Guardian

    private final static int GUARDIAN_XPOS = 800;
    private final static int GUARDIAN_YPOS = 600;
    private final static Image GUARDIAN_IMAGE = new Image("res/guardian.png");
    private final static Sprite GUARDIAN = new Sprite(GUARDIAN_IMAGE, GUARDIAN_XPOS, GUARDIAN_YPOS);

        // Enemy
    private final static int ENEMY_SPAWN_FRAMES = 600;

    private static int enemyFrames;

    private final ArrayList<Enemy> enemies = new ArrayList<>();

        // Projectile

    private final ArrayList<Projectile> projectiles = new ArrayList<>();

    // After Gameplay
    private final Accuracy accuracy;
    private final int target1 = 150;
    private final int target2 = 400;
    private final int target3 = 350;
    private int target;

    /**
     * Initialize level based on the input level
     */
    public Level(int level) {
        // Set target score and set guardian enemy true/false
        if (level == 3){
            level3 = true;
            displays.add(GUARDIAN);
            target = target3;
        }
        else if(level == 2){
            target = target2;
        }
        else{
            target = target1;
        }


        // Start Track
        track = new Track("res/track"+ level + ".wav");

        // Set Note Speed
        Note.setSpeed(startingSpeed);

        // Set empty Accuracy
        accuracy = new Accuracy();

        // Add Score text
        displays.add(scoreText);


        //track.run();

        // Read file
        readCsv("res/level" + level + ".csv");
    }

    /**
     * Method used to read file and create objects.
     */
    private void readCsv(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String textRead;
            while ((textRead = br.readLine()) != null) {
                String[] splitText = textRead.split(",");

                if (splitText[0].equals("Lane")) {
                    // reading lanes
                    String laneType = splitText[1];
                    int pos = Integer.parseInt(splitText[2]);
                    lanes.add(new Lane(laneType, pos, this));
                } else {
                    // reading notes
                    String dir = splitText[0];

                    for(Lane lane: lanes){
                        if (dir.equals(lane.getType())){
                            lane.addNote(splitText[1], Integer.parseInt(splitText[2]));
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(-1);
        }

    }

    @Override
    public void update(Input input){
        // Displaying all
        displayAll();

        // Paused
        if (paused){
            if (input.wasPressed(Keys.TAB)) {
                paused = false;
                //track.run();
            }
        }
        else{
            currFrame++;
            doubleScoreFrames++;
            enemyFrames++;
            accuracy.update();


            // DoubleScore Check
            if(doubleScoreFrames > ACTIVE_FRAMES){
                scoreMultiplier = 1;
            }

            // Enemy update
            if (level3){
                removeDeactivate();
                // Spawn Enemy
                if (enemyFrames > ENEMY_SPAWN_FRAMES){
                    enemies.add(new Enemy());
                    enemyFrames = 0;
                }
                // Update Enemy Array
                for(Enemy enemy: enemies){
                    enemy.update(lanes, projectiles);
                }

                // Projectile Spawn
                if (input.wasPressed(Keys.LEFT_SHIFT)) {
                    System.out.println("Shooting");
                    projectiles.add(new Projectile(enemies, GUARDIAN_XPOS, GUARDIAN_YPOS));
                }

                for(Projectile projectile: projectiles){
                    projectile.update();
                }
            }


            // Lane update
            for (Lane lane: lanes) {
                score += (lane.update(input, currFrame) * scoreMultiplier);
            }
            scoreText.newText("Score " + score);


            // Pause
            if (input.wasPressed(Keys.TAB)) {
                paused = true;
                track.pause();
            }
        }

    }

    /**
     * Display all text and object in the Scene
     */
    public void displayAll(){
        super.displayAll();

        for (Lane lane: lanes){
            lane.display();
        }
    }

    /**
     * Change scene if the game is finished
     */
    public void gameFinished(){
        for (Lane lane: lanes){
            if (!(lane.isDone())){
                return;
            }
        }
        // Change scene
        accuracy.end();
        if (score > target) {
            ShadowDance.endScene("Win");
        }
        else {
            ShadowDance.endScene("Lose");
        }
    }

    /**
     * Set Score multiplier
     * For any score that returns from each lane is multiplied by the score multiplier
     */
    public static void setScoreMultiplier(int scoreMultiplier) {
        Level.scoreMultiplier = scoreMultiplier;
        doubleScoreFrames = 0;
    }

    /**
     * For every Arraylist in level 3
     * If their objects are deactivated, they will be removed from the ArrayList
     */
    private void removeDeactivate(){
        while(!(enemies.isEmpty()) && !(enemies.get(0).isActive())){
            enemies.remove(0);
        }
        while(!(projectiles.isEmpty()) && !(projectiles.get(0).isActive())){
            projectiles.remove(0);
        }
    }

}
