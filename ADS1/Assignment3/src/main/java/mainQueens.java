import java.util.Arrays;
import java.util.Collections;

public class mainQueens {
    public static void main(String[] args) {
        //Just a test for the occupied spots
        int[][] board = new int[5][5];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                board[i][j] = 0;
            }
        }
        doPosition(board,2,2);
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.print(board[i][j]+ " ");
            }
            System.out.print("\n");
        }
        //Actual assignment
        for (int i = 4; i < 13; i++) {
            System.out.println("N queens for a "+ i + " by " + i + " board: " + nQueens(i)+ "\n");
        }
    }
    
    static int nQueens(int chessBoardSize){
        int[][] chessBoard = new int[chessBoardSize][chessBoardSize];
        for (int i = 0; i < chessBoardSize; i++) {
            for (int j = 0; j < chessBoardSize; j++) {
                chessBoard[i][j] = 0;
            }
        }
        return placeQueens(chessBoard, 0);
    }

    static int placeQueens(int[][] chessBoard, int row){
        int completed = 0;
        for (int i = 0; i < chessBoard[row].length; i++) {
            if(chessBoard[row][i] == 0){
                int[][] copyChessBoard = getCopyOfArrayOfArrays(chessBoard);
                doPosition(copyChessBoard, row, i);
                if(tryRow(copyChessBoard, row+1)){
                    completed = completed +  placeQueens(copyChessBoard, row+1);
                } else{
                    completed++;
                    break;
                }
            }
        }
        return completed;
    }

    private static int[][] getCopyOfArrayOfArrays(int[][] chessBoard) {
        int[][] copyChessBoard = new int[chessBoard.length][chessBoard.length];
        for (int j = 0; j < chessBoard.length; j++) {
            for (int k = 0; k < chessBoard.length; k++) {
                copyChessBoard[j][k] = chessBoard[j][k];
            }
        }
        return copyChessBoard;
    }

    private static boolean tryRow(int[][] chessBoard, int row) {
        //Tries to move to another row but cant if it does not exist
        try {
            int array = chessBoard[row][0];
            return true;
        }catch (ArrayIndexOutOfBoundsException e){
            return false;
        }
    }

    private static void doPosition(int[][] chessBoard, int row, int collumn) {
        //Setting the postion to the queen. 2 represents the queen
        chessBoard[row][collumn]= 2;
        //Setting danger zones caused by the queen.
        //1 represents a danger zone where another queen can't be placed

        for (int i = 0; i < chessBoard.length; i++) {
            //North to South
            if(chessBoard[i][collumn] != chessBoard[row][collumn])
                chessBoard[i][collumn] = 1;
            //West to East
            if(chessBoard[row][i] != chessBoard[row][collumn])
                chessBoard[row][i] = 1;
        }
        //South-east to South-West
        int tempCollumnLeft = collumn;
        int tempCollumnRight = collumn;
        int tempRow = row;
        for (int i = row; i < chessBoard.length; i++) {
            try {
                chessBoard[tempRow+1][tempCollumnLeft-1] = 1;
            }catch (ArrayIndexOutOfBoundsException ignored){}
            try {
                chessBoard[tempRow+1][tempCollumnRight+1] = 1;
            }catch (ArrayIndexOutOfBoundsException ignored){}
            tempCollumnLeft--;
            tempCollumnRight++;
            tempRow++;
        }

        //North-east to North-West
        tempRow = row;
        tempCollumnLeft = collumn;
        tempCollumnRight = collumn;
        for (int i = row; i > 0; i--) {
            try {
                chessBoard[tempRow-1][tempCollumnLeft-1] = 1;
            }catch (ArrayIndexOutOfBoundsException ignored){}
            try {
                chessBoard[tempRow-1][tempCollumnRight+1] = 1;
            }catch (ArrayIndexOutOfBoundsException ignored){}
            tempRow--;
            tempCollumnRight++;
            tempCollumnLeft--;
        }

    }
}
