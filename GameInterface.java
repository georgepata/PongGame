import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class GameInterface extends JPanel {
    private Image image;
    private ImageIcon imageIcon;
    private final int MAX_WIDTH=1000;
    private final int MAX_HEIGHT=(int)(MAX_WIDTH*(0.5555));
    private JButton playButton;
    private ImageIcon buttonImageIcon;
    public GameInterface(){
        this.setLayout(null);
        playButton=new JButton("Play");
        playButton.addActionListener(new actionButton());
        buttonImageIcon=new ImageIcon("playButton.png");
        playButton.setIcon(buttonImageIcon);
        imageIcon=new ImageIcon("pong.png");
        image=imageIcon.getImage();
        this.setPreferredSize(new Dimension(MAX_WIDTH, MAX_HEIGHT));
        this.setBackground(Color.BLACK);
        this.add(playButton);
//        playButton.setBounds((getWidth() - image.getWidth(this)) / 2, (getHeight() - image.getHeight(this)) / 2 +200,
//                50,10);
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
                GameInterface.this.componentResized(e);
            }
        });
    }
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int centerX = (getWidth() - image.getWidth(this)) / 2;
        int centerY = (getHeight() - image.getHeight(this)) / 2 - 100;
        g.drawImage(image, centerX, centerY, this);
    }
    public void componentResized(ComponentEvent e) {
        playButton.setBounds((getWidth() - 50) / 2 - 120, (getHeight() - 10) / 2 + 100, 295, 100);
    }

    private class actionButton implements ActionListener{
        public actionButton(){
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == playButton){
                GameFrame.getInstance().getCardLayout().show(GameFrame.getInstance().getContentPane(), "GamePanel");
                GameFrame.getInstance().getGamePanel().requestFocusInWindow();
            }
        }
    }
}
