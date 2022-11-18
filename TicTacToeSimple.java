import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Player {

    static int[][][] winningMoves = {{{0, 0}, {0, 1}, {0, 2}},
                              {{1, 0}, {1, 1}, {1, 2}},
                              {{2, 0}, {2, 1}, {2, 2}},
                              {{0, 0}, {1, 0}, {2, 0}},
                              {{0, 1}, {1, 1}, {2, 1}},
                              {{0, 2}, {1, 2}, {2, 2}},
                              {{0, 0}, {1, 1}, {2, 2}},
                              {{2, 0}, {1, 1}, {0, 2}}};

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        int[][] board = {{0, 0, 0},
                         {0, 0, 0},
                         {0, 0, 0}};
                        

        Random randomMethod = new Random();

        // game loop
        while (true) {
            ArrayList<int[]> winningList = new ArrayList<int[]>();
            ArrayList<int[]> losingList = new ArrayList<int[]>();
            ArrayList<int[]> randomList = new ArrayList<int[]>();

            int opponentRow = in.nextInt();
            int opponentCol = in.nextInt();
            if (opponentRow != -1 && opponentCol != -1) {
                board[opponentRow][opponentCol] = 2;
            }
            // System.err.println("Opponent move:");
            // System.err.println(opponentRow);
            // System.err.println(opponentCol);

            int validActionCount = in.nextInt();
            for (int i = 0; i < validActionCount; i++) {
                int row = in.nextInt();
                int col = in.nextInt();
                // System.err.println("Possible move:");
                // System.err.println(row);
                // System.err.println(col);

                if (isWinning(board, row, col, 1)) {
                    // winning for player
                    winningList.add(new int[]{row, col});
                }
                else if (isWinning(board, row, col, 2)) {
                    // losing for player
                    losingList.add(new int[]{row, col});
                }
                else {
                    // chose random move
                    randomList.add(new int[]{row, col});
                }
            }

            int[] move = new int[2];
            if (!winningList.isEmpty()) {
                move[0] = winningList.get(0)[0];
                move[1] = winningList.get(0)[1];
            }
            else if (!losingList.isEmpty()) {
                move[0] = losingList.get(0)[0];
                move[1] = losingList.get(0)[1];
            }
            else if (board[1][1] == 0) {
                // value center of the board above other non-winning/losing moves
                move[0] = 1;
                move[1] = 1;
            }
            else {
                int randomIndex = randomMethod.nextInt(randomList.size());
                move[0] = randomList.get(randomIndex)[0];
                move[1] = randomList.get(randomIndex)[1];
            }

            board[move[0]][move[1]] = 1;
            System.out.println(move[0] + " " + move[1]);

            // Write an action using System.out.println()
            // To debug: System.err.println("Debug messages...");

            // System.out.println("0 0");
        }
    }

    private static boolean isWinning(int[][] board, int row, int col, int player) {
        board[row][col] = player;
        for (int[][] winningComb: winningMoves) {
            if (board[winningComb[0][0]][winningComb[0][1]] == board[winningComb[1][0]][winningComb[1][1]]
                && board[winningComb[0][0]][winningComb[0][1]] == board[winningComb[2][0]][winningComb[2][1]]
                && board[winningComb[0][0]][winningComb[0][1]] != 0) {
                    board[row][col] = 0;
                    return true;
                }
        }

        board[row][col] = 0;
        return false;
    }

    private static void printBoard(int[][] board) {
        System.err.println(board[0][0] + " " + board[0][1] + " " + board[0][2]);
        System.err.println(board[1][0] + " " + board[1][1] + " " + board[1][2]);
        System.err.println(board[2][0] + " " + board[2][1] + " " + board[2][2]);
    }
}
