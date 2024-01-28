import  bagel.*;
public class Text implements Display{

    private final static int WINDOW_WIDTH = 1024;
    private final static int WINDOW_HEIGHT = 768;
    public final static String FONT_FILE = "res/FSO8BITR.TTF";;
    private final Font text;
    private String content;
    private double xPos;
    private double yPos;
    public Text(int size, String content, int xPos, int yPos){
        this.xPos = xPos;
        this.yPos = yPos;
        text = new Font(FONT_FILE, size);
        this.content = content;
    }

    /**
     * Draw the Text into the Window
     */
    public void draw(){
        text.drawString(content, xPos, yPos);
    }

    /**
     * Rewrite the text displayed
     */
    public void newText(String words){
        content = words;
    }

    /**
     * Center align the text
     */
    public void center(){
        this.xPos = WINDOW_WIDTH/2 - text.getWidth(content)/2;
    }
}
