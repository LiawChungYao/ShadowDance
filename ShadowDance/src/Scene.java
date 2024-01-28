import java.util.ArrayList;
import bagel.*;
public abstract class Scene {

    ArrayList<Display> displays = new ArrayList<>();

    /**
     * Displays all sprites and text in ArrayList
     */
    protected void displayAll(){
        for (Display object: displays){
            object.draw();
        }
    }

    public void update(Input input){
    }
}
