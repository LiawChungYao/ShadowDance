import bagel.DrawOptions;
import bagel.Image;
import bagel.util.Vector2;

import java.util.ArrayList;

public class Projectile {
    private final static String PROJECTILE_FILE_IMAGE = "res/arrow.png"; // Filename of the Image for Projectile
    private final static Image PROJECTILE_IMAGE = new Image(PROJECTILE_FILE_IMAGE); // Image object of the Projectile

    private Sprite image;
    private final static int SPEED = 6; // Add the include the current value in random
    private boolean active = false;
    private double angle = 0;
    private DrawOptions rotation;
    private Vector2 position;

    public Projectile(ArrayList<Enemy> enemies, int guardianX, int guardianY) {

        position = new Vector2(guardianX,guardianY);
        this.image = new Sprite(PROJECTILE_IMAGE, guardianX,guardianY);
        Enemy target = null;
        int distance = 0;
        // If there are enemies
        for(Enemy enemy: enemies){
            if (!(enemy.isActive()) || enemy == null){

            }
            else if (target == null){

                active = true;
                target = enemies.get(0);
                distance = distanceCenter(guardianX,guardianY,target.getImage().getxPos(), target.getImage().getyPos());
            }
            else if(distance >distanceCenter(guardianX,guardianY,enemy.getImage().getxPos(), enemy.getImage().getyPos())){
                target = enemy;
                distance = distanceCenter(guardianX,guardianY,enemy.getImage().getxPos(), enemy.getImage().getyPos());
            }
        }

        if (active){
            // Calculate angle

            rotation = new DrawOptions();
            angle = Math.atan2(target.getImage().getyPos() - guardianY, target.getImage().getxPos() - guardianX);
            rotation.setRotation(angle);
        }
    }

    public void update(){
        if (!isActive()){
            return;
        }
        position = position.add(new Vector2(Math.cos(angle) * SPEED,Math.sin(angle) * SPEED));
        image.setPosition((int) position.x, (int)position.y);
        image.draw(rotation);

        if (image.outOfBounds()){
            deactivate();
        }
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
     * Deactivates the Projectile
     */
    public void deactivate() {
        active = false;
    }

    /**
     * Returns the X position of the Projectile
     */
    public int getPositionX() {
        return (int) position.x;
    }

    /**
     * Returns the Y position of the Projectile
     */
    public int getPositionY() {
        return (int) position.y;
    }

    /**
     * Returns the active/deactive state of the Projectile
     */
    public boolean isActive() {
        return active;
    }
}
