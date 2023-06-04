import java.util.*;

public class TaquinGame {

    public static int[][] board;
    private int size;
    private int emptyX;
    private int emptyY;
    private int numMoves=0;

    public TaquinGame(int size) {
        this.size = size;
        board = new int[size][size];
        int counter = 1;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                board[i][j] = counter;
                counter++;
            }
        }
        board[size - 1][size - 1] = 0;
        emptyX = size - 1;
        emptyY = size - 1;
    }

    public void shuffle() {
        Random rand = new Random();
        String[] directions = {"up", "down", "left", "right"};
        for (int i = 0; i < 1000; i++) {
            int randNum = rand.nextInt(4);
            String direction = directions[randNum];
            move(direction);
        }
        setNumMoves();
    }
    public boolean isValidMove(int row, int col) {
        if (board[row][col] == 0) return false;  // empty tile cannot be moved
        if (row > 0 && board[row-1][col] == 0) return true;  // check above
        if (row < size-1 && board[row+1][col] == 0) return true;  // check below
        if (col > 0 && board[row][col-1] == 0) return true;  // check left
        if (col < size-1 && board[row][col+1] == 0) return true;  // check right
        return false;
    }
    public void moveTile(int row, int col) {
        // find the empty tile
        int emptyRow = -1, emptyCol = -1;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (board[i][j] == 0) {
                    emptyRow = i;
                    emptyCol = j;
                    break;
                }
            }
            if (emptyRow != -1) break;
        }

        // swap the tiles
        int temp = board[row][col];
        board[row][col] = board[emptyRow][emptyCol];
        board[emptyRow][emptyCol] = temp;
        numMoves++;
    
    }
    public int getTileValue(int row, int col) {
        return board[row][col];
    }
    public int[][] getBoard() {
        // Return a deep copy of the board to prevent external modification
        int[][] copy = new int[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                copy[i][j] = board[i][j];
            }
        }
        return copy;
    }

    public void move(String direction) {
       
        switch (direction) {
            case "up": // Move up
                if (emptyY > 0) {
                    board[emptyY][emptyX] = board[emptyY - 1][emptyX];
                    board[emptyY - 1][emptyX] = 0;
                    emptyY--;
                    numMoves++;
                }
                break;
            case "down": // Move down
                if (emptyY < size - 1) {
                    board[emptyY][emptyX] = board[emptyY + 1][emptyX];
                    board[emptyY + 1][emptyX] = 0;
                    emptyY++;
                    numMoves++;
                }
                break;
            case "left": // Move left
                if (emptyX > 0) {
                    board[emptyY][emptyX] = board[emptyY][emptyX - 1];
                    board[emptyY][emptyX - 1] = 0;
                    emptyX--;
                    numMoves++;
                }
                break;
            case "right": // Move right
                if (emptyX < size - 1) {
                    board[emptyY][emptyX] = board[emptyY][emptyX + 1];
                    board[emptyY][emptyX + 1] = 0;
                    emptyX++;
                    numMoves++;
                }
                break;
            default:
                break;
        }
    }
    public int getNumMoves() {
        return numMoves;
    }
    public void setNumMoves() {
        numMoves=0;
    }
    public boolean isSolved() {
        int counter = 1;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (board[i][j] != counter) {
                    return false;
                }
                counter++;
            }
        }
        return true;
    }

    public void printBoard() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (board[i][j] == 0) {
                    System.out.print("  ");
                } else {
                    System.out.print(board[i][j] + " ");
                }
            }
            System.out.println();
        }
    }
    
}

