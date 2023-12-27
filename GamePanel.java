import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class GamePanel extends JPanel implements Runnable{
    private final int MAX_WIDTH=1000;
    private final int MAX_HEIGHT=(int)(MAX_WIDTH*(0.5555));
    private int PADDLE_WIDTH=25;
    private int PADDLE_HEIGHT=100;
    private int BALL_DIAMETER=20;
    private Paddle paddle1;
    private Paddle paddle2;
    private Ball ball;
    private Score score;
    private Image image;
    private Graphics graphics;
    private Thread gameThread;
    public GamePanel(){
        this.setBackground(Color.BLACK);
        this.setPreferredSize(new Dimension(MAX_WIDTH, MAX_HEIGHT));
        this.setFocusable(true);
        this.requestFocusInWindow(); // Add this line to set focus
        this.addKeyListener(new AL());

        newPaddle();
        newBall();
        score=new Score(MAX_WIDTH, MAX_HEIGHT);

        gameThread=new Thread(this);
        gameThread.start();
    }

    public void newPaddle(){
        paddle1=new Paddle(0, ((MAX_HEIGHT/2)-(PADDLE_HEIGHT/2)), PADDLE_WIDTH, PADDLE_HEIGHT, 1);
        paddle2=new Paddle(MAX_WIDTH-PADDLE_WIDTH, ((MAX_HEIGHT/2)-(PADDLE_HEIGHT/2)), PADDLE_WIDTH, PADDLE_HEIGHT, 2);
    }
    public void newBall(){
        ball=new Ball(((MAX_WIDTH/2)-(BALL_DIAMETER/2)), ((MAX_HEIGHT/2)-(BALL_DIAMETER/2)), BALL_DIAMETER, BALL_DIAMETER);
    }
    public void paint(Graphics g){
        image=createImage(MAX_WIDTH, MAX_HEIGHT);
        graphics=image.getGraphics();
        draw(graphics);
        g.drawImage(image, 0,0, this);
    }
    public void draw(Graphics g){
        paddle1.draw(g);
        paddle2.draw(g);
        score.draw(g);
        ball.draw(g);
    }
    public void move(){
        paddle1.move();
        paddle2.move();
         ball.move();
    }
    public void checkCollision(){
        if(paddle1.y <= 0)
            paddle1. y= 0;
        if (paddle2.y <= 0)
            paddle2.y = 0;
        if((paddle1.y+PADDLE_HEIGHT) > MAX_HEIGHT)
            paddle1.y = MAX_HEIGHT - PADDLE_HEIGHT;
        if ((paddle2.y + PADDLE_HEIGHT) > MAX_HEIGHT)
            paddle2.y=MAX_HEIGHT-PADDLE_HEIGHT;
        if (ball.y <= 0)
            ball.setYDirection(ball.getSpeed());
        if (ball.y >= (MAX_HEIGHT - BALL_DIAMETER))
            ball.setYDirection(-ball.getSpeed());
        if (ball.x <= 0) {
            newBall();
            newPaddle();
            score.setPlayer2(1);
            repaint();
        }
        if ((ball.x + BALL_DIAMETER) >= MAX_WIDTH) {
            newBall();
            newPaddle();
            score.setPlayer1(1);
            repaint();
        }
        if (ball.intersects(paddle1)){
            ball.setXDirection(ball.getSpeed());
        }
        if (ball.intersects(paddle2)){
            ball.setXDirection(-ball.getSpeed());
        }
    }

    @Override
    public void run() {
        long lastTime=System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000/amountOfTicks;
        double delta=0;
        while(true){
            long now = System.nanoTime();
            delta += (now-lastTime)/ns;
            lastTime=now;
            if (delta>=1){
                move();
                checkCollision();
                repaint();
                delta--;
            }
        }
    }
    public class AL extends KeyAdapter{
        public void keyPressed(KeyEvent e){
            paddle1.keyPressed(e);
            paddle2.keyPressed(e);
        }
        public void keyReleased(KeyEvent e){
            paddle1.keyReleased(e);
            paddle2.keyReleased(e);
        }
    }
}
