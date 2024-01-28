public class Feedback {
    private String text;
    private int score;
    private int radius;


    public Feedback(String text, int score, int radius) {
        this.text = text;
        this.score = score;
        this.radius = radius;
    }

    /**
     * Get the text of Feedback
     */
    public String gettext() {
        return text;
    }

    /**
     * Get the score of Feedback
     */
    public int getScore() {
        return score;
    }

    /**
     * Get the Radius of Feedback
     */
    public int getRadius() {
        return radius;
    }
}
