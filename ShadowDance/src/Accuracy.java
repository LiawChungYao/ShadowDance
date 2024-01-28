import bagel.*;

import java.util.ArrayList;

/**
 * Class for dealing with accuracy of pressing the notes
 */
public class Accuracy {
    public static final int PERFECT_SCORE = 10;
    public static final int GOOD_SCORE = 5;
    public static final int BAD_SCORE = -1;
    public static final int MISS_SCORE = -5;
    public static final int NOT_SCORED = 0;
    public static final String PERFECT_TEXT = "PERFECT";
    public static final String GOOD_TEXT = "GOOD";
    public static final String BAD_TEXT = "BAD";
    public static final String MISS_TEXT= "MISS";
    private static final int PERFECT_RADIUS = 15;
    private static final int GOOD_RADIUS = 50;
    private static final int BAD_RADIUS = 100;
    private static final int MISS_RADIUS = 200;
    public static final Feedback PERFECT= new Feedback(PERFECT_TEXT, PERFECT_SCORE, PERFECT_RADIUS);
    public static final Feedback GOOD = new Feedback(GOOD_TEXT, GOOD_SCORE, GOOD_RADIUS);
    public static final Feedback BAD = new Feedback(BAD_TEXT, BAD_SCORE, BAD_RADIUS);
    public static final Feedback MISS =  new Feedback(MISS_TEXT, MISS_SCORE, MISS_RADIUS);
    private static final Font ACCURACY_FONT = new Font("res/FSO8BITR.TTF", 40);
    private static final int RENDER_FRAMES = 30;
    private static String currAccuracy = null;
    private static int frameCount = 0;
    private static ArrayList<Feedback> feedbacks = new ArrayList<>();
    public Accuracy() {
        feedbacks.add(PERFECT);
        feedbacks.add(GOOD);
        feedbacks.add(BAD);
        feedbacks.add(MISS);
    }

    public static void setAccuracy(String accuracy) {
        currAccuracy = accuracy;
        frameCount = 0;
    }

    public static void print(Feedback feedback){
        setAccuracy(feedback.gettext());
    }

    public static Feedback getFeedback(int distance){
        for(Feedback feedback: feedbacks){
            if (distance <= feedback.getRadius()){
                return feedback;
            }
        }
        return null;
    }

    public static int evaluateScore(Feedback feedback) {
        if (feedback == null){
            return NOT_SCORED;
        }
        print(feedback);
        return feedback.getScore();

    }

    public void update() {
        frameCount++;
        if (currAccuracy != null && frameCount < RENDER_FRAMES) {
            ACCURACY_FONT.drawString(currAccuracy,
                    Window.getWidth()/2 - ACCURACY_FONT.getWidth(currAccuracy)/2,
                    Window.getHeight()/2);
        }
    }

    public void end(){
        frameCount = RENDER_FRAMES;
    }


}
