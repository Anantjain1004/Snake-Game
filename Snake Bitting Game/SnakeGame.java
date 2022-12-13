import javax.swing.*;
import java.awt.*;

public class SnakeGame {
    JFrame frame;//creating frame
    SnakeGame()//creating constructor
    {
        frame = new JFrame("Snake game"); //initialising the constructor or creating object
        frame.setBounds(10,10,905,700);
        MyPanel panel = new MyPanel();//initialising the panel object
        panel.setBackground(Color.gray);//setting the background color
        frame.add(panel);//adding panel to the frame
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);//if anyone click close option then to stop the game and exit from the window
        frame.setVisible(true);//making frame visible
    }
    public static void main(String[] args) {
        SnakeGame sg = new SnakeGame();
    }
}
