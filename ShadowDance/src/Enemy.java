import bagel.Image;

import java.util.ArrayList;
import java.util.Random;

public class Enemy {
    private final static String ENEMY_FILE_IMAGE = "res/enemy.png"; // Filename of the Image for Enemy
    private final static Image ENEMY_IMAGE = new Image(ENEMY_FILE_IMAGE); // Image object of the Enemy
    private final static int SPEED = 1; // Movement Speed of the Enemy
    private final static int XPOS_LOWER_BOUND = 100; // Lower Bound of X position
    private final static int XPOS_UPPER_BOUND = 900; // Upper Bound of X position
    private final static int YPOS_LOWER_BOUND = 100; // Lower Bound of Y position
    private final static int YPOS_UPPER_BOUND = 500; // Upper Bound of Y position
    private final static int INCLUSIVE = 1; // Add the include the current value in random
    private final static int COLLISION_NOTE = 104; // Range of Collision
    private final static int COLLISION_PROJECTILE = 62; // Range of Collision

    private boolean active = true;
    private int direction; // Moving Direction of Enemy
    private Sprite image;

    public Enemy() {
        Random random = new Random();

        // Move Direction
        this.direction = (random.nextBoolean() ? 1 : -1);

        // Position
        int y = random.nextInt(YPOS_UPPER_BOUND - YPOS_LOWER_BOUND + INCLUSIVE) + YPOS_LOWER_BOUND;
        int x = random.nextInt(XPOS_UPPER_BOUND - XPOS_LOWER_BOUND + INCLUSIVE) + XPOS_LOWER_BOUND;
        this.image = new Sprite(ENEMY_IMAGE, x,y);
    }


    public void update(ArrayList<Lane> lanes, ArrayList<Projectile> projectiles){
        if(!isActive()){
            return;
        }
        // Display
        image.draw();

        // Position Update
        outOfBounds();
        image.setPosition(image.getxPos() + (SPEED * direction), image.getyPos());

        // Colliding :

        // With Lanes
        for(Lane lane : lanes){
            // Checks if Enemy is in range of a lane
            if (distanceLateral(lane.getImage().getxPos(), image.getxPos()) <= COLLISION_NOTE){

                for(Note note : lane.getActiveNotes()){
                    // Checks if enemy is in range of any note
                    if(distanceCenter(image.getxPos(), image.getyPos(), note.image.getxPos(), note.image.getyPos()) <= COLLISION_NOTE){

                        // Deactivate
                        note.deactivate();
                    }
                }
            }
        }

        // With Projectiles
        for(Projectile projectile: projectiles){
            int distance = distanceCenter(image.getxPos(), image.getyPos(), projectile.getPositionX(), projectile.getPositionY());
            if(distance <= COLLISION_PROJECTILE){
                System.out.println("HIT");
                // Hit!
                projectile.deactivate();
                deactivate();

            }
        }
    }

    /**
     * If Enemy moves out of the maximum or minimum X boundary
     * The enemy will switch directions
     */
    private void outOfBounds(){
        if (image.getxPos() <= XPOS_LOWER_BOUND || image.getxPos() >= XPOS_UPPER_BOUND){
            switchDirection();
        }
    }

    /**
     * Switches the moving direction of the Enemy
     */
    private void switchDirection(){
        direction *= -1;
    }

    /**
     * Calculates the absolute distance between two points
     */
    protected int distanceLateral(int a, int b){
        return Math.abs(a - b);
    }

    /**
     * Calculates the distance between two points on a 2D plane
     */
    protected int distanceCenter(int x1, int y1, int x2, int y2){
        double xDist = distanceLateral(x1,x2);
        double yDist = distanceLateral(y1,y2);
        return (int) Math.sqrt(xDist*xDist + yDist*yDist);
    }

    /**
     * Returns Image
     */
    public Sprite getImage() {
        return image;
    }

    /**
     * Deactivates the Enemy
     */
    private void deactivate() {
        active = false;
    }

    /**
     * Returns the active/deactive state of the Enemy
     */
    public boolean isActive() {
        return active;
    }
}
