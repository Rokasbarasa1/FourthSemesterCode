public class main {
    public static void main(String[] args) {
        for (int i = 4; i < 12; i++) {
            System.out.println("N queens for a "+ i + " by " + i + ": " + nQueens(i));
            System.out.println("");
        }
    }
    
    static int nQueens(int chessBoardSize){
        int[][] chessBoard = new int[chessBoardSize][chessBoardSize];
        int waysToPlace = 0;
        
        for (int i = 0; i < chessBoardSize; i++) {
            Boolean placed = 
        }
    }
    
    public int placeQueens(int[][] chessBoard, int row){
        int completed = 0;
        for (int i = 0; i < chessBoard[0].length; i++) {
            if(chessBoard[row][i] == 1){
                continue;
            }else {
                try{
                    int test = chessBoard[row + 1][0];
                    int[][] copy = new                 //Check if other row of array exists
                            //Copy array by value so that you do not interfere in others
                            //Place queen on this spot in copied array
                            //Generate trace of turns/Mark all squares as 1 that it can walk on. On the new array

                            completed = completed + placeQueens(chessBoard,row + 1);
                }catch (NullPointerException e){
                    break;
                }

            }
        }
    }
    
    
}
