import java.awt.*;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Ball extends Rectangle {
    private Random random;
    private int xVelocity;
    private int yVelocity;
    private int speed=2;
    private Timer timer;
    public Ball(int x, int y, int BALL_WIDTH, int BALL_HEIGHT){
        super(x,y,BALL_WIDTH,BALL_HEIGHT);
        random=new Random();
        int randomForXDirection = random.nextInt(2)-1;
        int randomForYDirection = random.nextInt(2)-1;
        if (randomForXDirection<0)
            setXDirection(-speed);
        else if (randomForXDirection==0)
            setXDirection(speed);
        if (randomForYDirection<0)
            setYDirection(-speed);
        else if (randomForYDirection==0)
            setYDirection(speed);
        timer=new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (speed < 10) {
                    speed += 2;
                    setXDirection(xVelocity > 0 ? speed : -speed);
                    setYDirection(yVelocity > 0 ? speed : -speed);
                }
            }
        }, 1000, 30000);
    }
    public void setXDirection(int xDirection){
        this.xVelocity=xDirection;
    }
    public void setYDirection(int yDirection){
        this.yVelocity=yDirection;
    }
    public void move(){
        x += xVelocity;
        y += yVelocity;
    }
    public void draw(Graphics g){
        g.setColor(Color.YELLOW);
        g.fillOval(x,y,width,height);
    }
    public int getSpeed(){
        return speed;
    }
}
