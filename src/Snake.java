import javax.swing.*;
import java.awt.event.*;

public class Snake extends JDialog {
    private JPanel contentPane;
    private JButton restart;
    private JButton buttonCancel;
    private JLabel score;
    private JLabel highscore;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JPanel r1c1;
    private JPanel r2c1;
    private JPanel r3c1;

    public Snake() {
        setContentPane(contentPane);
        setModal(true);
        setTitle("Snake von Noah");
        setSize(500, 500);
        setLocationRelativeTo(null);
        setVisible(true);

        // when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                //sicher beenden?
                dispose();
            }
        });

        createEventListener();
    }

    public void createEventListener() {
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("UP");
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("DOWN");
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("LINKS");
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("RIGHT");
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        restart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }



    public static void main(String[] args) {
        Snake dialog = new Snake();
        dialog.pack();
        System.exit(0);
    }
}
