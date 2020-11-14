public class main {

    public static void main(String[] args) {
        int[] block1 = {5,3,-1,6,-1,-1,-1,9,8};
        int[] block2 = {-1,7,-1,1,9,5,-1,-1,-1};
        int[] block3 = {-1,-1,-1,-1,-1,-1,-1,6,-1,};
        int[] block4 = {8,-1,-1,4,-1,-1,7,-1,-1};
        int[] block5 = {-1,6,-1,8,-1,3,-1,2,-1,};
        int[] block6 = {-1,-1,3,-1,-1,1,-1,-1,6};
        int[] block7 = {-1,6,-1,-1,-1,-1,-1,-1,-1,};
        int[] block8 = {-1,-1,-1,4,1,9,-1,8};
        int[] block9 = {2,8,-1,-1,-1,5,-1,7,9};
        int[][] sudokuBlock = {block1,block2,block3,block4,block5,block6,block7,block8,block9};
        sudokuSolver(sudokuBlock);
    }

    private static void sudokuSolver(int[][] sudokuBlock) {
        for (int i = 0; i < sudokuBlock.length; i++) {
            //for (int j = 0; j < ; j++) {
                
            //}
        }
    }

}
