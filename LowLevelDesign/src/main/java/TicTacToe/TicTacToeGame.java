package TicTacToe;
import java.util.*;

/*
Features and thought Process:
O(1) time complexity
O(n) space complexity

Game Board:
    Each cell contains X/0/null
    Status of the Board: Winner 1/Winner2/Draw/Undecided
    Attributes:
        Initialize the Board
        Get the Board()
        Get the winner()
        Get the current player who will have next move()
        Make Move()
        If Player 1 move it is +1, Player 2 move is -1
User:
    ID
    Statistics

Game:
    ID
    User1
    User2
    List<Moves> to undo or check the move history
    undo()
    Initialize the Board

Constrains:
    Make move in O(1)
    Get winner in O(1) time
 */
public class TicTacToeGame {
    private final int[][] board;
    private final int[] rowSum;
    private final int[] colSum;
    private int diagSum;
    private int revDiagSum;

    public TicTacToeGame(final int n) {
        board = new int[n][n];
        rowSum = new int[n];
        colSum = new int[n];
    }

    /**
     * Makes the move ob board and returns the winner if it is winning move
     * @param player either 0 or 1
     * @param row move row index
     * @param col move col index
     * @return +1 if Player1 wins, -1 if Player2 wins, 0 if otherwise
     * @throws IllegalArgumentException if the move is illegal move
     */
    public int move(int player, int row, int col) throws IllegalArgumentException{
        if(row<0 || col<0 || row>=board.length || col>=board[0].length) {
            throw new IllegalArgumentException("Move out of Board");
        }
        else if(board[row][col] != 0) {
            throw new IllegalArgumentException("Square is already occupied");
        }
        else if(player != 0 && player != 1) {
            throw new IllegalArgumentException("Invalid player");
        }
        else {
            int n = board.length;
            player = player==0 ? -1 : +1;
            board[row][col] = player;
            rowSum[row] += player;
            colSum[col] += player;
            if(row == col) {
                diagSum += player;
            }

            if(row == n-1-col) {
                revDiagSum += player;
            }

            if(Math.abs(rowSum[row]) == n || Math.abs(colSum[col]) == n || Math.abs(diagSum) == n ||
            Math.abs(revDiagSum) == n) {
                return player;
            }
            return 0;
        }
    }
}
