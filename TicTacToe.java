import java.util.Scanner;

public class TicTacToe
{
    private static final int ROWS = 3;
    private static final int COLS = 3;
    private static String board[][] = new String[ROWS][COLS];

    public static void main(String[] args)
    {
        // Create a Scanner
        // Do (one game each time through)
        //     Clear the board, set the player to X and the move counter to 0
        //     Do (one move each time through)
        //         Show the board
        //         Do
        //             Get the row and col for the move (1 - 3) and subtract 1 to get the array indices
        //         While the move is not valid (the cell is not empty)
        //         Record the move on the board and add 1 to the move counter
        //         If there have been at least 5 moves, check for a win
        //         If there is no win and there have been at least 7 moves, check for a tie
        //         If the game is not over, toggle the player (X becomes O, O becomes X)
        //     While the game is not over
        //     Ask the players if they want to play again
        // While they want to play again

        Scanner in = new Scanner(System.in);
        String player = "X";
        int moveCount = 0;
        int row = 0;
        int col = 0;
        boolean gameOver = false;
        boolean playAgain = false;

        do
        {
            clearBoard();
            player = "X";   // X always moves first
            moveCount = 0;
            gameOver = false;

            do
            {
                display();
                System.out.println("\nPlayer " + player + ", it is your move.");

                // Loop until the player picks an empty cell
                do
                {
                    row = SafeInput.getRangedInt(in, "Enter the row for your move", 1, ROWS) - 1;
                    col = SafeInput.getRangedInt(in, "Enter the column for your move", 1, COLS) - 1;

                    if (!isValidMove(row, col))
                    {
                        System.out.println("That cell is already taken. Pick an empty cell.");
                    }
                } while (!isValidMove(row, col));

                board[row][col] = player;
                moveCount++;

                // A win is not possible until the 5th move, and a tie is not possible until the 7th
                if (moveCount >= 5 && isWin(player))
                {
                    display();
                    System.out.println("\nPlayer " + player + " wins!");
                    gameOver = true;
                }
                else if (moveCount >= 7 && isTie())
                {
                    display();
                    System.out.println("\nIt is a tie! Nobody can win this game.");
                    gameOver = true;
                }
                else
                {
                    // Toggle the player
                    if (player.equals("X"))
                    {
                        player = "O";
                    }
                    else
                    {
                        player = "X";
                    }
                }
            } while (!gameOver);

            playAgain = SafeInput.getYNConfirm(in, "Do you want to play again");
        } while (playAgain);

        System.out.println("\nThanks for playing Tic Tac Toe!");
    }

    /**
     * Sets all the board elements to a space.
     */
    private static void clearBoard()
    {
        for (int row = 0; row < ROWS; row++)
        {
            for (int col = 0; col < COLS; col++)
            {
                board[row][col] = " ";
            }
        }
    }

    /**
     * Shows the Tic Tac Toe board with row and column numbers.
     */
    private static void display()
    {
        System.out.println();
        System.out.println("    1   2   3");
        for (int row = 0; row < ROWS; row++)
        {
            System.out.print((row + 1) + "  ");
            for (int col = 0; col < COLS; col++)
            {
                System.out.print(" " + board[row][col] + " ");
                if (col < COLS - 1)
                {
                    System.out.print("|");
                }
            }
            System.out.println();
            if (row < ROWS - 1)
            {
                System.out.println("   ---+---+---");
            }
        }
    }

    /**
     * Returns true if the cell at the given row and col is empty, which makes it a legal move.
     */
    private static boolean isValidMove(int row, int col)
    {
        return board[row][col].equals(" ");
    }

    /**
     * Checks to see if the given player (X or O) has a win on the board.
     */
    private static boolean isWin(String player)
    {
        return isColWin(player) || isRowWin(player) || isDiagnalWin(player);
    }

    /**
     * Checks for a column win for the given player.
     */
    private static boolean isColWin(String player)
    {
        for (int col = 0; col < COLS; col++)
        {
            if (board[0][col].equals(player) && board[1][col].equals(player) && board[2][col].equals(player))
            {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks for a row win for the given player.
     */
    private static boolean isRowWin(String player)
    {
        for (int row = 0; row < ROWS; row++)
        {
            if (board[row][0].equals(player) && board[row][1].equals(player) && board[row][2].equals(player))
            {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks for a diagonal win for the given player.
     */
    private static boolean isDiagnalWin(String player)
    {
        if (board[0][0].equals(player) && board[1][1].equals(player) && board[2][2].equals(player))
        {
            return true;
        }
        if (board[0][2].equals(player) && board[1][1].equals(player) && board[2][0].equals(player))
        {
            return true;
        }
        return false;
    }

    /**
     * Checks for a tie: the board is full, or every one of the 8 win vectors
     * (3 rows, 3 cols, 2 diagonals) has both an X and an O in it so nobody can win.
     */
    private static boolean isTie()
    {
        boolean boardFull = true;
        for (int row = 0; row < ROWS; row++)
        {
            for (int col = 0; col < COLS; col++)
            {
                if (board[row][col].equals(" "))
                {
                    boardFull = false;
                }
            }
        }
        if (boardFull)
        {
            return true;
        }

        // Check each row and each col to see if it is blocked
        for (int i = 0; i < ROWS; i++)
        {
            if (!isBlocked(board[i][0], board[i][1], board[i][2]))
            {
                return false;
            }
            if (!isBlocked(board[0][i], board[1][i], board[2][i]))
            {
                return false;
            }
        }

        // Check the two diagonals
        if (!isBlocked(board[0][0], board[1][1], board[2][2]))
        {
            return false;
        }
        if (!isBlocked(board[0][2], board[1][1], board[2][0]))
        {
            return false;
        }

        return true;
    }

    /**
     * Returns true if the three cells of a win vector hold both an X and an O,
     * which means neither player can win with that vector.
     */
    private static boolean isBlocked(String a, String b, String c)
    {
        boolean hasX = a.equals("X") || b.equals("X") || c.equals("X");
        boolean hasO = a.equals("O") || b.equals("O") || c.equals("O");
        return hasX && hasO;
    }
}
