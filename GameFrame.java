import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {
    private GamePanel gamePanel;
    private GameInterface gameInterface;
    private CardLayout cardLayout;
    public static GameFrame instance;
    public GameFrame(){
        instance=this;
        gamePanel=new GamePanel();
        gameInterface=new GameInterface();
        this.add(gameInterface);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
        this.setBackground(Color.BLACK);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        cardLayoutSetUp();
    }
    public void cardLayoutSetUp(){
        cardLayout=new CardLayout();
        this.getContentPane().setLayout(cardLayout);
        this.getContentPane().add(gameInterface, "GameInterface");
        this.getContentPane().add(gamePanel, "GamePanel");
        cardLayout.show(this.getContentPane(), "GameInterface");
    }
    public static GameFrame getInstance(){
        return instance;
    }
    public CardLayout getCardLayout(){
        return this.cardLayout;
    }
    public JPanel getGamePanel(){
        return this.gamePanel;
    }
}
