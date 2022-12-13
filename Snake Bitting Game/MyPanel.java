import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class MyPanel extends JPanel implements KeyListener, ActionListener { //extending the properties of jPanel
        ImageIcon snaketitle = new ImageIcon(getClass().getResource("snaketitle.jpg"));//used to display image on the panel,we have to create icon of that picture
        ImageIcon rightmouth = new ImageIcon(getClass().getResource("rightmouth.png"));
        ImageIcon snakimage = new ImageIcon(getClass().getResource("snakeimage.png"));
        ImageIcon up = new ImageIcon(getClass().getResource("upmouth.png"));
        ImageIcon down = new ImageIcon(getClass().getResource("downmouth.png"));
        ImageIcon left = new ImageIcon(getClass().getResource("leftmouth.png"));
        ImageIcon food = new ImageIcon(getClass().getResource("enemy.png"));

        boolean isUp = false;
        boolean isDown = false;
        boolean isRight = true; //here we are taking it true because we are starting from first position towards right
        boolean isLeft = false;

        //position of enemies using array on x axis and y axis, we will choose the random position of enemy
        int[] xpos={25,50,75,100,125,150,175,200,225,250,275,300,325,350,375,400,425,450,475,500,525,550,575,600,625,650,675,700,725,750,775,800,825,850};
        int[] ypos={75,100,125,150,175,200,225,250,275,300,325,350,375,400,425,450,475,500,525,550,575,600,625};

        //this will help us in getting random value from anything
        Random random = new Random(); //this pointer will help us in getting random position everytime
        //assigning initial position of enemy or food
        int foodX = 150;
        int foodY = 150;


        //creating the 2d array for movement of snake
        int snakeX[] = new int[750];//movement on X axis
        int snakeY[] = new int[750];// movement on Y axis
        int move = 0;//initially moves are 0
        int lengthOfSnake = 3;//initially length of snake would be 3 only
        Timer time;
        boolean Gameover = false;
        int score = 0;//taking score variable

        //to make snake movable we will make constructor
        MyPanel(){
            //so, we will move our snake using keypad so, there is specific function which help us in moving
            //snake using keys
            addKeyListener(this);
            setFocusable(true);
            time = new Timer(150,this);
            time.start();
        }

    @Override
    public void paint(Graphics g) {
        super.paint(g);//here g is kind of pen or pencil
        g.setColor(Color.WHITE);//setting color of pen or pencil white
        g.drawRect(24,10,851,55);//drawing first rectangle
        g.drawRect(24,74,851,576);//drawing Second rectangle
        snaketitle.paintIcon(this,g,25,11);//here painting the icon over the panel(i.e..this) using pencil(g)
        //in first rectangle with dimensions x and y
        g.setColor(Color.BLACK);//setting the colour of pencil black so as to fill second rectangle
        g.fillRect(25,75,850,575);//here filling second rect with black colour

        if(move == 0){ //assigning the positions
            //taking 25 pixel gap because size of picture is 25 pixel of right head
            snakeX[0] = 100;//position of head
            snakeX[1] = 75;//body parts behind the head
            snakeX[2] = 50;

            snakeY[0] = 100;//it will be same because distance from y axis is not changing
            snakeY[1] = 100;//bodyPart location, it will be decreased as it is attaching on back side
            snakeY[2] = 100;
        }

        if(isRight)
            rightmouth.paintIcon(this,g,snakeX[0],snakeY[0]);
        if(isDown)
            down.paintIcon(this,g,snakeX[0],snakeY[0]);
        if(isLeft)
            left.paintIcon(this,g,snakeX[0],snakeY[0]);
        if(isUp)
            up.paintIcon(this,g,snakeX[0],snakeY[0]);
        //printing body parts,starting from 1 because we have fixed our head
        for (int i = 1; i < lengthOfSnake; i++) {
            snakimage.paintIcon(this,g,snakeX[i],snakeY[i]);
        }

        food.paintIcon(this,g,foodX,foodY);//here we have printed food over the screen
        //if game is over then we will run below function
        if(Gameover)
        {
            g.setColor(Color.WHITE);//set color white
            g.setFont(new Font(Font.SANS_SERIF,Font.BOLD,30));
            g.drawString("Game Over",300,300);//we will print game over at 300,300 position

            //we are again using this to display restart over screen
            g.setFont(new Font(Font.SANS_SERIF,Font.PLAIN,10));
            g.drawString("Press the Space Key to Restart",330,360);
        }
        //here we are pasting the score and length of snake on first rectangle
        g.setColor(Color.WHITE);
        g.setFont(new Font("ITALIC",Font.PLAIN,15));
        g.drawString("Score" + score,750,30);
        g.drawString("Length" + lengthOfSnake,750,50);
        g.dispose();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
            //if someone has pressed the space key and Gameover is true then we wil restart our game
        if(e.getKeyCode() == KeyEvent.VK_SPACE && Gameover)
        {
            restart();
        }

        if(e.getKeyCode() == KeyEvent.VK_RIGHT && (!isLeft))//if we have clicked the right key then it should not directly move left
        {
            //if we are clicking right we will make other moves false
            isUp = false;
            isDown = false;
            isLeft = false;
            //and we will make right move true
            isRight = true;
            move++;//I will increase my move
        }
        //if we have clicked the left key
        if(e.getKeyCode() == KeyEvent.VK_LEFT && (!isRight))
        {
            //if we are clicking left we will make other moves false
            isUp = false;
            isDown = false;
            isRight = false;
            //and we will make left move true
            isLeft = true;
            move++;//I will increase my move
        }

        //if we have clicked the up key
        if(e.getKeyCode() == KeyEvent.VK_UP && (!isDown))
        {
            //if we are clicking up we will make other moves false
            isLeft = false;
            isDown = false;
            isRight = false;
            //and we will make left move true
            isUp = true;
            move++;//I will increase my move
        }

        //if we have clicked the down key
        if(e.getKeyCode() == KeyEvent.VK_DOWN && (!isUp))
        {
            //if we are clicking down we will make other moves false
            isLeft = false;
            isUp = false;
            isRight = false;
            //and we will make left move true
            isDown = true;
            move++;//I will increase my move
        }
    }

    //function to restart the game
    private void restart() {
         score = 0;
         Gameover = false;
         move = 0;
         lengthOfSnake = 3;
         isLeft = false;
         isRight = true;
         isUp = false;
         isDown = false;
         time.start();
         newFood();
         repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = lengthOfSnake - 1 ; i > 0 ; i--)
        {
            // so when my snake will move forward, body also start move forwarding, we will restore last position
            //so that we can start from last positions
            snakeX[i] = snakeX[i-1];//here my previous part come to current position
            snakeY[i] = snakeY[i-1];
        }
        //if we have press left key then it will move backward(horizontally)
        if(isLeft)
            snakeX[0] = snakeX[0] - 25; //snake has move backward by 25 pixel backward

        //if we will press right key then it will move forward
        if(isRight){
            snakeX[0] = snakeX[0] + 25; //snake has move forward by 25 pixel forward
        }

        //if we will press up key then it will move upward(vertically)
        if(isUp){
            snakeY[0] = snakeY[0] - 25; //snake has move upward by 25 pixel upward
        }

        //if we will press down key then it will move downward(vertically)
        if(isDown){
            snakeY[0] = snakeY[0] + 25; //snake has move downward by 25 pixel down
        }



        if(snakeX[0] > 850) snakeX[0] = 25;
        if(snakeX[0] < 25) snakeX[0] = 850;
        if(snakeY[0] > 625) snakeY[0] = 75;
        if(snakeY[0] < 75) snakeY[0] = 625;
        CollisionWithFood(); //this function will hit if snake will touch the food
        CollisionWithBody(); //this function will hit if snake will touch its body
        repaint();
    }

    private void CollisionWithBody() {
        for (int i = lengthOfSnake - 1; i > 0; i--) {
            //it means head is eating some body part
            if(snakeX[i] == snakeX[0] && snakeY[i] == snakeY[0])
            {
                time.stop();//time will be stopped
                Gameover = true;
            }
        }
    }

    private void CollisionWithFood() {
            //if this condition gets true it means snake is collided with the food
            if(snakeX[0] == foodX && snakeY[0] == foodY){
                newFood();
                lengthOfSnake++;
                score++;//whenever we hit our enemy we will increase our score as well
                snakeX[lengthOfSnake-1] = snakeX[lengthOfSnake-2];
                snakeY[lengthOfSnake-1] = snakeY[lengthOfSnake-2];

            }
    }

    private void newFood() {
            //assigning the position of new food
        foodX = xpos[random.nextInt(xpos.length-1)];//getting random position from x
        foodY = ypos[random.nextInt(ypos.length-1)];//getting random position from y

        //if it happens like,food collided with snake body then we will not let that happen
        for (int i = lengthOfSnake - 1;i >= 0; i--) {
            if (snakeX[i] == foodX && snakeY[i] == foodY)
            //it means snake and food is overlaping
            {
                newFood();
            }
        }


    }
}
