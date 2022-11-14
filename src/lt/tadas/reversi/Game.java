package lt.tadas.reversi;

import java.util.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;

class Game extends JFrame implements ActionListener {
    JPanel p;
    JButton buttons[];
    Board board;
    int count = 2;

    Game() {
        super("Reversi");
        board = new Board();
        p = new JPanel();
        buttons = new JButton[64];
        setSize(500, 500);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        p.setLayout(new GridLayout(8, 8));
        for (int i = 0; i < 64; i++) {
            buttons[i] = new JButton();
            buttons[i].addActionListener(this);
            p.add(buttons[i]);
        }
        add(p);
        setVisible(true);
    }

    void update(Board board) {

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board.grid[i][j] == 1) {
                    if (buttons[i * 8 + j].getBackground() != Color.WHITE) {
                        buttons[i * 8 + j].setBackground(Color.WHITE);
                        buttons[i * 8 + j].setOpaque(true);
                    }
                } else if (board.grid[i][j] == -1) {
                    if (buttons[i * 8 + j].getBackground() != Color.BLACK) {
                        buttons[i * 8 + j].setBackground(Color.BLACK);
                        buttons[i * 8 + j].setOpaque(true);
                    }
                } else {
                    if (buttons[i * 8 + j].getBackground() != Color.GREEN) {
                        buttons[i * 8 + j].setBackground(Color.GREEN);
                        buttons[i * 8 + j].setOpaque(true);
                    }
                }
            }
        }
    }

    public void actionPerformed(ActionEvent e) {
        if(count%2==0)
        {
            System.out.println("White turn:");
        }
        else
        {
            System.out.println("Black turn: ");
        }
            JButton o = (JButton) e.getSource();
            ArrayList<Integer> possibleMoves = board.findPossibleMoves(-1);
            if (possibleMoves.size() > 0) {
                for (int i = 0; i < 64; i++) {
                    if (buttons[i] == o) {
                        try {
                            board.move(-1, i / 8, i % 8);
                        } catch (Exception exception) {
                            JOptionPane.showMessageDialog(null, exception.getMessage());
                            return;
                        }
                        update(board);
                        break;
                    }
                }
            }
        else
        {
            board.displayResult();
            board=new Board();
            update(board);
            return;
        }
        count += 1;
        }

    public static void main(String args[])throws Exception
    {
        Game game=new Game();
        game.update(game.board);
    }
}