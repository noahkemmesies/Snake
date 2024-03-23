import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Snake extends JDialog implements Runnable {
    private JPanel contentPane;
    private JButton restart;
    private JLabel scoreL, highscoreL, info;
    private JButton up, down, left, right;
    private JPanel r0c0, r0c1, r0c2, r0c3, r0c4, r0c5, r0c6, r0c7, r0c8, r0c9;
    private JPanel r1c0, r1c1, r1c2, r1c3, r1c4, r1c5, r1c6, r1c7, r1c8, r1c9;
    private JPanel r2c0, r2c1, r2c2, r2c3, r2c4, r2c5, r2c6, r2c7, r2c8, r2c9;
    private JPanel r3c0, r3c1, r3c2, r3c3, r3c4, r3c5, r3c6, r3c7, r3c8, r3c9;
    private JPanel r4c0, r4c1, r4c2, r4c3, r4c4, r4c5, r4c6, r4c7, r4c8, r4c9;
    private JPanel r5c0, r5c1, r5c2, r5c3, r5c4, r5c5, r5c6, r5c7, r5c8, r5c9;
    private JPanel r6c0, r6c1, r6c2, r6c3, r6c4, r6c5, r6c6, r6c7, r6c8, r6c9;
    private JPanel r7c0, r7c1, r7c2, r7c3, r7c4, r7c5, r7c6, r7c7, r7c8, r7c9;
    private JPanel r8c0, r8c1, r8c2, r8c3, r8c4, r8c5, r8c6, r8c7, r8c8, r8c9;
    private JPanel r9c0, r9c1, r9c2, r9c3, r9c4, r9c5, r9c6, r9c7, r9c8, r9c9;

    private final Color cBoard1 = Color.GRAY, cBoard2 = Color.LIGHT_GRAY, cSnake = Color.BLUE, cSnakeHead = Color.BLUE, cSnakeHeadDead = Color.MAGENTA, cApple = Color.RED;
    private final int height = 10, width = 10;
    private final JPanel[][] board = new JPanel[height][width];
    private final ArrayList<int[]> snake = new ArrayList<>();
    private final int[] apple = new int[2];
    private int[] lastL = new int[2];
    private int score = 0, highscore = 0, nextDirection = 0;

    public Snake() {
        setContentPane(contentPane);
        setModal(true);
        setTitle("Snake von Noah");
        setSize(500, 500);
        setLocationRelativeTo(null);

        // when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                //sicher beenden?
                dispose();
            }
        });

        createEventListener();
        createBoard();
        setVisible(true);
    }

    public void createEventListener() {
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                move(1);
                //System.out.println("UP");
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                move(2);
                //System.out.println("DOWN");
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                move(3);
                //System.out.println("LEFT");
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                move(4);
                //System.out.println("RIGHT");
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        restart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                restart();
            }
        });

        up.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                move(1);
            }
        });

        down.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                move(2);
            }
        });

        left.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                move(3);
            }
        });

        right.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                move(4);
            }
        });
    }

    public void createBoard() {
        board[0][0] = r0c0;
        board[0][1] = r0c1;
        board[0][2] = r0c2;
        board[0][3] = r0c3;
        board[0][4] = r0c4;
        board[0][5] = r0c5;
        board[0][6] = r0c6;
        board[0][7] = r0c7;
        board[0][8] = r0c8;
        board[0][9] = r0c9;

        board[1][0] = r1c0;
        board[1][1] = r1c1;
        board[1][2] = r1c2;
        board[1][3] = r1c3;
        board[1][4] = r1c4;
        board[1][5] = r1c5;
        board[1][6] = r1c6;
        board[1][7] = r1c7;
        board[1][8] = r1c8;
        board[1][9] = r1c9;

        board[2][0] = r2c0;
        board[2][1] = r2c1;
        board[2][2] = r2c2;
        board[2][3] = r2c3;
        board[2][4] = r2c4;
        board[2][5] = r2c5;
        board[2][6] = r2c6;
        board[2][7] = r2c7;
        board[2][8] = r2c8;
        board[2][9] = r2c9;

        board[3][0] = r3c0;
        board[3][1] = r3c1;
        board[3][2] = r3c2;
        board[3][3] = r3c3;
        board[3][4] = r3c4;
        board[3][5] = r3c5;
        board[3][6] = r3c6;
        board[3][7] = r3c7;
        board[3][8] = r3c8;
        board[3][9] = r3c9;

        board[4][0] = r4c0;
        board[4][1] = r4c1;
        board[4][2] = r4c2;
        board[4][3] = r4c3;
        board[4][4] = r4c4;
        board[4][5] = r4c5;
        board[4][6] = r4c6;
        board[4][7] = r4c7;
        board[4][8] = r4c8;
        board[4][9] = r4c9;

        board[5][0] = r5c0;
        board[5][1] = r5c1;
        board[5][2] = r5c2;
        board[5][3] = r5c3;
        board[5][4] = r5c4;
        board[5][5] = r5c5;
        board[5][6] = r5c6;
        board[5][7] = r5c7;
        board[5][8] = r5c8;
        board[5][9] = r5c9;

        board[6][0] = r6c0;
        board[6][1] = r6c1;
        board[6][2] = r6c2;
        board[6][3] = r6c3;
        board[6][4] = r6c4;
        board[6][5] = r6c5;
        board[6][6] = r6c6;
        board[6][7] = r6c7;
        board[6][8] = r6c8;
        board[6][9] = r6c9;

        board[7][0] = r7c0;
        board[7][1] = r7c1;
        board[7][2] = r7c2;
        board[7][3] = r7c3;
        board[7][4] = r7c4;
        board[7][5] = r7c5;
        board[7][6] = r7c6;
        board[7][7] = r7c7;
        board[7][8] = r7c8;
        board[7][9] = r7c9;

        board[8][0] = r8c0;
        board[8][1] = r8c1;
        board[8][2] = r8c2;
        board[8][3] = r8c3;
        board[8][4] = r8c4;
        board[8][5] = r8c5;
        board[8][6] = r8c6;
        board[8][7] = r8c7;
        board[8][8] = r8c8;
        board[8][9] = r8c9;

        board[9][0] = r9c0;
        board[9][1] = r9c1;
        board[9][2] = r9c2;
        board[9][3] = r9c3;
        board[9][4] = r9c4;
        board[9][5] = r9c5;
        board[9][6] = r9c6;
        board[9][7] = r9c7;
        board[9][8] = r9c8;
        board[9][9] = r9c9;

        fillBoard();
    }

    public void fillBoard() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (i%2 == 0 && j%2 == 0 || i%2 == 1 && j%2 == 1) {
                    board[i][j].setBackground(cBoard1);
                } else {
                    board[i][j].setBackground(cBoard2);
                }
            }
        }

        snake.add(new int[]{height/2 -1,  width/2 -1});
        int[] temp = snake.get(0);
        board[temp[0]][temp[1]].setBackground(cSnake);
        placeApple();
    }

    public void move(int direction) { //0 = No movement; 1 = UP; 2 = DOWN; 3 = LEFT; 4 = RIGHT
        if (nextDirection == 0) {
            info.setText("");
        } else if (nextDirection == -1) {
            info.setText("Restart to play again!");
        } else {
            nextDirection = direction;
        }
    }

    public void placeApple() {
        while (true) {
            int randR = (int) (Math.random() * height);
            int randC = (int) (Math.random() * width);

            if (!isSnake(new int[]{randR, randC})) {
                apple[0] = randR;
                apple[1] = randC;
                board[randR][randC].setBackground(cApple);
                break;
            }
        }
    }

    public boolean isSnake(int[] newL) {
        boolean found = false;
        for (int[] l : snake) {
            if (l[0] == newL[0] && l[1] == newL[1]) {
                found = true;
                break;
            }
        }
        return found;
    }

    public boolean isApple(int[] newL) {
        return apple[0] == newL[0] && apple[1] == newL[1];
    }

    public boolean isOnBoard(int[] newL) {
        return newL[0] >= 0 && newL[1] >= 0 && newL[0] < height && newL[1] < width;
    }

    public void endScreen() {
        nextDirection = -1;
        info.setText("Game Over!");
        if (score > highscore) {
            highscore = score;
            highscoreL.setText("Highscore: " + highscore);
        }
    }

    public void restart() {
        nextDirection = 0;
        score = 0;
        fillBoard();
        info.setText("Press an Arrow-Key");
    }

    public void draw() {
        int[] l = snake.get(0);
        board[l[0]][l[1]].setBackground(cSnakeHead);

        if (snake.size() > 1) {
            int[] l2 = snake.get(1);
            board[l2[0]][l2[1]].setBackground(cSnake);
        }

        if (lastL != null) {
            if (lastL[0]%2 == 0 && lastL[1]%2 == 0 || lastL[0]%2 == 1 && lastL[1]%2 == 1) {
                board[lastL[0]][lastL[1]].setBackground(cBoard1);
            } else {
                board[lastL[0]][lastL[1]].setBackground(cBoard2);
            }
        }
    }

    @Override
    public void run() {
        while (true) {
            while (nextDirection == 0 || nextDirection == -1); //can be replaced with if

            int[] nextL = new int[2];
            int[] head = snake.get(0);
            nextL[0] = head[0];
            nextL[1] = head[0];
            switch (nextDirection) {
                case 1 -> nextL[0] -= 1;
                case 2 -> nextL[0] += 1;
                case 3 -> nextL[1] -= 1;
                case 4 -> nextL[1] += 1;
            }
            /*
            if (nextDirection == 1) {
                nextL[0] = head[0] - 1;
                nextL[1] = head[0];
            }
            if (nextDirection == 2) {
                nextL[0] = head[0] + 1;
                nextL[1] = head[0];
            }
            if (nextDirection == 3) {
                nextL[0] = head[0];
                nextL[1] = head[0] - 1;
            }
            if (nextDirection == 4) {
                nextL[0] = head[0];
                nextL[1] = head[0] + 1;
            }*/
            if (isSnake(nextL) || !isOnBoard(nextL)) {
                board[nextL[0]][nextL[1]].setBackground(cSnakeHeadDead);
                endScreen();
                continue;
            } else if (isApple(nextL)) {
                score += 1;
                snake.add(0, nextL);
                this.lastL = null;
                placeApple();
            } else {
                snake.add(0, nextL);
                int[] lastL = snake.get(snake.size() -1);
                this.lastL = lastL;
                snake.remove(snake.size() -1);
            }
            //draw
            draw();

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void main(String[] args) {
        Snake dialog = new Snake();
        dialog.pack();
        new Thread(dialog).start();
        System.exit(0);
    }
}