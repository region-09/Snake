import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Main extends JFrame {
    Field field = new Field();
    DrawPanel panel = new DrawPanel();
    int n = 1;
    public Main() {
        JButton b = new JButton("Start / Restart");
        panel.setBackground(Color.black);
        panel.setVisible(true);
        panel.setLayout(null);
        b.setBounds(50, 30, 150, 50);
        b.setFocusable(false);
        b.setVisible(true);
        panel.add(b);
        b.addActionListener(e -> {
            field.start = 0;
            field.start();
            n = 1;
            repaint();
        });
        addKeyListener(new DrawPanelKeyListener());             //also, we should add DrawPanelKeylistener() class.
        add(panel);                                             //adds panel to JFrame!!!
    }
    public void run() {
        if (field.start == 0) {
            switch (n) {
                case 0:
                    field.moveUp();
                    break;
                case 1:
                    field.moveRight();
                    break;
                case 2:
                    field.moveDown();
                    break;
                case 3:
                    field.moveLeft();
                    break;
                default:
                    break;
            }
        }
    }
    public static void main(String[] args) {
        Main app = new Main();                                  // here codes are creating my JFrame components, characteristics.
        app.setSize(900,700);
        app.setLocationRelativeTo(null);
        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        app.setVisible(true);
    }
    class DrawPanel extends JPanel{
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(Color.blue);
            g.setFont(g.getFont().deriveFont(50f));
            String sc = Integer.toString(Field.score - 1);
            g.drawString("Score: " + sc, 900, 50);
            g.drawString("Snake 1.1", 600, 50);
            for (int j = 0; j < 40; j++) {
                for (int i = 0; i < 20; i++) {
                    if (field.arr[i][j] == 1) {
                        g.setColor(Color.darkGray);
                        g.fillRect((35 * j) + 60, (35 * i) + 90, 35, 35);
                        g.setColor(Color.BLACK);
                        g.drawRect((35 * j) + 60, (35 * i) + 90, 35, 35);
                    }
                    if (field.arr[i][j] == 2) {
                        g.setColor(Color.red);
                        g.fillRect((35 * j) + 60, (35 * i) + 90, 35, 35);
                    }
                    if (field.arr[i][j] == 3) {
                        g.setColor(Color.white);
                        g.fillRect((35 * j) + 60, (35 * i) + 90, 35, 35);
                    }
                    if (field.arr[i][j] == 9) {
                        g.setColor(Color.BLUE);
                        g.fillRect((35 * j) + 60, (35 * i) + 90, 35, 35);
                    }
                }
            }
            run();
            repaint();
        }
    }
    class DrawPanelKeyListener extends KeyAdapter{
        public void keyPressed(KeyEvent e) {
            if (field.start == 1) {
                JOptionPane.showMessageDialog(Main.this, "Lost Press Start button to start again!");
            }
            int KeyCode = e.getKeyCode();
            switch (KeyCode) {
                case KeyEvent.VK_UP:
                    if (field.dir != 2) {
                        n = 0;
                    }else {
                        n = field.dir;
                    }
                    break;
                case KeyEvent.VK_DOWN:
                    if (field.dir != 0) {
                        n = 2;
                    }else {
                        n = field.dir;
                    }
                    break;
                case KeyEvent.VK_LEFT:
                    if (field.dir != 1) {
                        n = 3;
                    }else {
                        n = field.dir;
                    }
                    break;
                case KeyEvent.VK_RIGHT:
                    if (field.dir != 3) {
                        n = 1;
                    }else {
                        n = field.dir;
                    }
                    break;
                default:
                    break;
            }
            repaint();
        }
    }
}
