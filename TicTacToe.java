// Dennis Amadi 
/**
 * "Code Tic Tac Toe in Java." YouTube, uploaded by Kenny Yip Coding,
 * 16 July 2023, www.youtube.com/watch?v=Nc77ymnm8Ss.
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TicTacToe
{
    // setup the window
    // width and height for the window
    int width = 600;
    int height = 650; // 600 px, 50 for the text panel on top
    
    JFrame frame = new JFrame("Tic Tac Toe");
    JLabel label = new JLabel(); // text panel
    JPanel panel = new JPanel();
    JPanel boardPanel = new JPanel(); // panel for the board
    
    JButton[][] board = new JButton[3][3];
    String playerX = "X";
    String playerO = "O";
    String player = playerX;
    boolean gameOver = false;
    int turns = 0;

    /**
     * Constructor for objects of class TicTacToe
     */
    public TicTacToe()
    {
        // set up the window
        frame.setVisible(true);
        frame.setSize(width, height);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        
        // initialize the text label
        label.setBackground(Color.darkGray);
        label.setForeground(Color.white);
        label.setFont(new Font("Arial", Font.BOLD, 50));
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setText("Tic-Tac-Toe");
        label.setOpaque(true);
        
        // add the label to the window
        panel.setLayout(new BorderLayout());
        panel.add(label);
        frame.add(panel, BorderLayout.NORTH);
        
        // intiialize the board
        boardPanel.setLayout(new GridLayout(3, 3));
        boardPanel.setBackground(Color.darkGray);
        frame.add(boardPanel);
        
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                JButton tile = new JButton();
                board[r][c] = tile;
                boardPanel.add(tile);
                tile.setBackground(Color.darkGray);
                tile.setForeground(Color.black);
                tile.setFont(new Font("Arial", Font.BOLD, 120));
                tile.setFocusable(false);
                // tile.setText(player);
                tile.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if (gameOver) return;
                        JButton tile = (JButton) e.getSource();
                        if (tile.getText() == "") {
                        tile.setText(player);
                        turns++;
                        checkWinner();
                        if (!gameOver) {
                            player = player == playerX ? playerO : playerX; // alternate
                            label.setText(player+"'s turn.");}
                        }
                    }
                });
            }
        }
    }
    
    void checkWinner() {
        // horizontal
        for (int r = 0; r < 3; r++) {
            if (board[r][0].getText() == "") continue;
            if (board[r][0].getText() == board[r][1].getText() &&
            board[r][1].getText() == board[r][2].getText()) {
                for (int i=0;i<3;i++) {
                    setWinner(board[r][i]);
                }
                gameOver = true; // end the game
                return;
            }
        }
        
        //vertical
        for (int c=0;c<3;c++) {
            if (board[0][c].getText() == "") continue;
            if (board[0][c].getText() == board[1][c].getText() &&
            board[1][c].getText() == board[2][c].getText()) {
                for (int i=0;i<3;i++) setWinner(board[i][c]);
                gameOver = true;
                return;
            }
        }
        
        // diagonal
        if (board[0][0].getText() == board[1][1].getText() &&
            board[1][1].getText() == board[2][2].getText() && 
            board[0][0].getText() != "") {
            for (int i=0;i<3;i++) setWinner(board[i][i]);
                gameOver = true;
                return;
        }
        
        // anti-diagonal
        if (board[0][2].getText() == board[1][1].getText() &&
            board[1][1].getText() == board[2][0].getText() &&
            board[0][2].getText() != "") {
                 setWinner(board[0][2]);
                 setWinner(board[1][1]);
                 setWinner(board[2][0]);
                 gameOver = true;
                 return;
        }
        
        // tie
        if (turns == 9) {
            for (int r=0;r<3;r++) {
                for (int c=0;c<3;c++) {
                    setTie(board[r][c]);
                }
            }
            gameOver = true;
        }
    }
    
    void setWinner(JButton tile) { // declare a winner
        tile.setForeground(Color.green);
        tile.setBackground(Color.gray);
        label.setText(player+" is the winner!");
    }
    
    void setTie(JButton tile) {
        tile.setForeground(Color.orange);
        tile.setBackground(Color.gray);
        label.setText("Tie!");
    }
    
    // main program
    public static void main(String args[]) {
        TicTacToe controller = new TicTacToe(); // jumpstarts the whole game
    }
}
