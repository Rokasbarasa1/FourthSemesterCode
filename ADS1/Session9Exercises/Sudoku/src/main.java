import java.util.ArrayList;
import java.util.List;

public class main {
    static List<Byte> numbersYouUse;
    static int call = 0;

    public static void main(String[] args) {
        numbersYouUse = new ArrayList<>(9);
        numbersYouUse.add((byte)1);
        numbersYouUse.add((byte)2);
        numbersYouUse.add((byte)3);
        numbersYouUse.add((byte)4);
        numbersYouUse.add((byte)5);
        numbersYouUse.add((byte)6);
        numbersYouUse.add((byte)7);
        numbersYouUse.add((byte)8);
        numbersYouUse.add((byte)9);
/*
        byte[] block1 = {5,3,-1,6,-1,-1,-1,9,8};
        byte[] block2 = {-1,7,-1,1,9,5,-1,-1,-1};
        byte[] block3 = {-1,-1,-1,-1,-1,-1,-1,6,-1,};
        byte[] block4 = {8,-1,-1,4,-1,-1,7,-1,-1};
        byte[] block5 = {-1,6,-1,8,-1,3,-1,2,-1,};
        byte[] block6 = {-1,-1,3,-1,-1,1,-1,-1,6};
        byte[] block7 = {-1,6,-1,-1,-1,-1,-1,-1,-1,};
        byte[] block8 = {-1,-1,-1,4,1,9,-1,8,-1};
        byte[] block9 = {2,8,-1,-1,-1,5,-1,7,9};

*/
        byte[] block1 = {9,8,4,-1,-1,-1,-1,-1,-1};
        byte[] block2 = {-1,-1,-1,5,-1,-1,-1,-1,-1};
        byte[] block3 = {5,-1,1,-1,-1,7,-1,-1,9};
        byte[] block4 = {-1,-1,-1,-1,2,-1,5,6,-1};
        byte[] block5 = {-1,1,-1,7,-1,3,-1,-1,-1};
        byte[] block6 = {-1,-1,-1,1,-1,-1,-1,-1,-1};
        byte[] block7 = {8,-1,-1,-1,-1,-1,1,-1,-1};
        byte[] block8 = {-1,-1,-1,-1,9,-1,2,8,-1};
        byte[] block9 = {4,9,6,-1,-1,-1,-1,-1,-1};

        byte[][] sudokuBlock = {block1,block2,block3,block4,block5,block6,block7,block8,block9};

        long startTime = System.nanoTime();
        byte result = sudokuSolver(sudokuBlock, (byte)0, (byte)0);
        long elapsedTime = System.nanoTime() - startTime;

        System.out.println("Finding the result took: " + (elapsedTime/1000000) + "ms");
        System.out.println("Possible solutions: " + result);
        System.out.println("Amount of recursions it took: " + call);
    }

    private static byte sudokuSolver(byte[][] sudokuBlock, byte currentBlock, byte currentNumberBox) {
        call++;
        byte total = 0;
        if(sudokuBlock[currentBlock][currentNumberBox] == -1){
            List<Byte> cantUse = getNotAllowedNumbers(sudokuBlock,currentBlock, currentNumberBox);
            if(cantUse.size() == 9)
                return 0;
            for (int i = 0; i < numbersYouUse.size(); i++) {
                if(!cantUse.contains(numbersYouUse.get(i))){
                    byte[][] copyOfSudokuBlock = deepCopySudokuBoard(sudokuBlock);
                    copyOfSudokuBlock[currentBlock][currentNumberBox] = numbersYouUse.get(i);
                    byte tempCurrentBlock = currentBlock;
                    byte tempCurrentNumberBox = currentNumberBox;
                    tempCurrentNumberBox++;
                    if(tempCurrentNumberBox > 8 ){
                        tempCurrentBlock++;
                        tempCurrentNumberBox = 0;
                    }
                    if(tempCurrentBlock == 9){
                        printSudoku(copyOfSudokuBlock);
                        total++;
                        break;
                    }else {
                        total += sudokuSolver(copyOfSudokuBlock, tempCurrentBlock, tempCurrentNumberBox);
                        if(total == 1)
                            break;
                    }
                }
            }
        }else{
            currentNumberBox++;
            if(currentNumberBox > 8 ){
                currentBlock++;
                currentNumberBox = 0;
            }
            if(currentBlock == 9){
                printSudoku(sudokuBlock);
                total++;
            }else {
                total += sudokuSolver(sudokuBlock, currentBlock, currentNumberBox);
            }
        }
        return total;
    }

    private static void printSudoku(byte[][] sudokuBlock) {
        int gridNow = 0;
        int gridNumberNow = 0;
        List<Byte> numbers = new ArrayList<>(81);

        for (int i = 0; i < 81; i++) {
            numbers.add(sudokuBlock[gridNow][gridNumberNow]);

            gridNumberNow++;
            if(gridNumberNow-1 == 2){
                if(gridNow == 2 || gridNow == 5 || gridNow == 8){
                    gridNumberNow = 3;
                    gridNow -= 2;
                }else{
                    gridNow++;
                    gridNumberNow = 0;
                }
            }
            else if(gridNumberNow-1 == 5){
                if(gridNow == 2 || gridNow == 5 || gridNow == 8){
                    gridNumberNow = 6;
                    gridNow -= 2;
                }else{
                    gridNow++;
                    gridNumberNow = 3;
                }
            }
            else if(gridNumberNow-1 == 8){
                if(gridNow == 2 || gridNow == 5 || gridNow == 8){
                    gridNumberNow = 0;
                    gridNow++;
                }else{
                    gridNow++;
                    gridNumberNow = 6;
                }
            }
        }

        int collumnIndex = 0;
        int cubeIndex = 0;
        for (int i = 0; i < 81; i++) {
            collumnIndex++;
            if(collumnIndex == 28 )
                System.out.println("\n");
            if(collumnIndex == 9){
                if(numbers.get(i) != -1)
                    System.out.format(" %2d\n", numbers.get(i));
                else
                    System.out.print("   \n");
                collumnIndex = 0;
                cubeIndex++;
                if(cubeIndex == 3 || cubeIndex == 6)
                    System.out.println(" ");
            }else {
                if(numbers.get(i) != -1)
                    System.out.format(" %2d ", numbers.get(i));
                else
                    System.out.print("    ");
            }
            if((collumnIndex % 8) == 3 || (collumnIndex % 8) == 6)
                System.out.print("   ");
        }

        System.out.println(" ");
    }

    private static byte[][] deepCopySudokuBoard(byte[][] sudokuBlock) {
        byte[] block1 = new byte[9];
        byte[] block2 = new byte[9];
        byte[] block3 = new byte[9];
        byte[] block4 = new byte[9];
        byte[] block5 = new byte[9];
        byte[] block6 = new byte[9];
        byte[] block7 = new byte[9];
        byte[] block8 = new byte[9];
        byte[] block9 = new byte[9];

        System.arraycopy(sudokuBlock[0], 0, block1, 0, 9);
        System.arraycopy(sudokuBlock[1], 0, block2, 0, 9);
        System.arraycopy(sudokuBlock[2], 0, block3, 0, 9);
        System.arraycopy(sudokuBlock[3], 0, block4, 0, 9);
        System.arraycopy(sudokuBlock[4], 0, block5, 0, 9);
        System.arraycopy(sudokuBlock[5], 0, block6, 0, 9);
        System.arraycopy(sudokuBlock[6], 0, block7, 0, 9);
        System.arraycopy(sudokuBlock[7], 0, block8, 0, 9);
        System.arraycopy(sudokuBlock[8], 0, block9, 0, 9);

        return new byte[][]{block1,block2,block3,block4,block5,block6,block7,block8,block9};
    }

    private static List<Byte> getNotAllowedNumbers(byte[][] sudokuBlock, byte gridNow, byte gridNumberNow){
        List<Byte> numbersYouCantUse = new ArrayList<>(9);
        byte HGridT = gridNow;
        byte HGElementT = gridNumberNow;
        byte VGridT = gridNow;
        byte VGElementT = gridNumberNow;
        //Check west to east plain
        //Set grid box to start of row

        if( gridNow > 0 && gridNow <=2 )
            HGridT = 0;
        else if( gridNow > 3 && gridNow <=5 )
            HGridT = 3;
        else if( gridNow > 6 && gridNow <=8 )
            HGridT = 6;

        //Set thee cursor number inside the grid box to starting of row
        if( HGElementT > 0 && HGElementT <=2 )
            HGElementT = 0;
        else if( HGElementT > 3 && HGElementT <=5 )
            HGElementT = 3;
        else if( HGElementT > 6 && HGElementT <=8 )
            HGElementT = 6;

        //Check north to south plain
        //Set grid box to top of row
        if( VGridT == 3 || VGridT == 6 )
            VGridT = 0;
        else if( VGridT == 4 || VGridT == 7 )
            VGridT = 1;
        else if( VGridT == 5 || VGridT == 8 )
            VGridT = 2;

        //Set thee cursor number inside the grid box to top of row
        if( VGElementT == 3 || VGElementT == 6 )
            VGElementT = 0;
        else if( VGElementT == 4 || VGElementT == 7 )
            VGElementT = 1;
        else if( VGElementT == 5 || VGElementT == 8 )
            VGElementT = 2;

        for (int j = 0; j < 9; j++) {
            if(numbersYouCantUse.size() == 9)
                break;
            if(sudokuBlock[gridNow][gridNumberNow] != sudokuBlock[HGridT][HGElementT]
                    && !numbersYouCantUse.contains(sudokuBlock[HGridT][HGElementT]) && sudokuBlock[HGridT][HGElementT] != -1){
                numbersYouCantUse.add(sudokuBlock[HGridT][HGElementT]);
            }
            HGElementT++;
            if(HGElementT > 8 || HGElementT == 3 || HGElementT == 6){
                HGElementT--;
                HGridT++;
                if( HGElementT > 0 && HGElementT <=2 )
                    HGElementT = 0;
                else if( HGElementT > 3 && HGElementT <=5 )
                    HGElementT = 3;
                else if( HGElementT > 6 && HGElementT <=8 )
                    HGElementT = 6;
            }

            if(sudokuBlock[gridNow][gridNumberNow] != sudokuBlock[VGridT][VGElementT]
                    && !numbersYouCantUse.contains(sudokuBlock[VGridT][VGElementT]) && sudokuBlock[VGridT][VGElementT] != -1){
                numbersYouCantUse.add(sudokuBlock[VGridT][VGElementT]);
            }
            VGElementT += 3;
            if(VGElementT > 8){
                VGElementT -= 3;
                VGridT += 3;
                if( VGElementT == 3 || VGElementT == 6 )
                    VGElementT = 0;
                else if( VGElementT == 4 || VGElementT == 7 )
                    VGElementT = 1;
                else if( VGElementT == 5 || VGElementT == 8 )
                    VGElementT = 2;
            }
        }

        //Finds elements from the block the number is in. Places them as not available
        for (int i = 0; i < sudokuBlock[gridNow].length; i++) {
            if(numbersYouCantUse.size() == 9)
                break;
            if(sudokuBlock[gridNow][i] != -1 && !numbersYouCantUse.contains(sudokuBlock[gridNow][i]))
                numbersYouCantUse.add(sudokuBlock[gridNow][i]);
        }
        return numbersYouCantUse;
    }
}
