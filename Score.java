import java.awt.*;

public class Score extends Rectangle {
    private static int MAX_WIDTH;
    private static int MAX_HEIGHT;
    private int player1;
    private int player2;
    public Score(int MAX_WIDTH, int MAX_HEIGHT){
        this.MAX_WIDTH = MAX_WIDTH;
        this.MAX_HEIGHT = MAX_HEIGHT;
        player1=0;
        player2=0;
    }
    public void draw(Graphics g){
        g.setColor(Color.WHITE);
        g.setFont(new Font("Times New Roman", 1, 20));

        g.drawLine(MAX_WIDTH/2, 0, MAX_WIDTH/2, MAX_HEIGHT);
        g.drawString(Integer.toString(player1/10)+Integer.toString(player1%10), (MAX_WIDTH/2)-50, 20);
        g.drawString(Integer.toString(player2/10)+Integer.toString(player2%10), (MAX_WIDTH/2)+30, 20);
    }
    public void setPlayer1(int value){
        player1+=value;
    }
    public void setPlayer2(int value){
        player2+=value;
    }
}
