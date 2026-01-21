package Array.Other.SudokuSolver;

public class SudokuSolver {

    public static void main(String[] args) {
        char[][] board = {  {'5','3','.',   '.','7','.',    '.','.','.'},
                            {'6','.','.',   '1','9','5',    '.','.','.'},
                            {'.','9','8',   '.','.','.',    '.','6','.'},

                            {'8','.','.',   '.','6','.',    '.','.','3'},
                            {'4','.','.',   '8','.','3',    '.','.','1'},
                            {'7','.','.',   '.','2','.',    '.','.','6'},

                            {'.','6','.',   '.','.','.',    '2','8','.'},
                            {'.','.','.',   '4','1','9',    '.','.','5'},
                            {'.','.','.',   '.','8','.',    '.','7','9'}   };
        printBoard(board);
        if (solveBoard(board)){
            System.out.println("Solved successfully!");
            System.out.println();
        } else{
            System.out.println("Unsolvable board :(");
            System.out.println();
        };
        printBoard(board);
    }

    public static final int GRID_SIZE = 9;
    public static final int BOX_SIZE = 3;

    private static boolean isNumberInRow(char[][] board, int number, int row){
        for(int i=0; i<GRID_SIZE; i++){
            char c = board[row][i];
            if (Character.isDigit(c) && Character.getNumericValue(c) == number)
                return true;
        }
        return false;
    }

    private static boolean isNumberInColumn(char[][] board, int number, int column){
        for(int i=0; i<GRID_SIZE; i++){
            char c = board[i][column];
            if (Character.isDigit(c) && Character.getNumericValue(c) == number)
                return true;
        }
        return false;
    }

    private static boolean isNumberInBox(char[][] board, int number, int row, int column){
        int localBoxRow = row - row % BOX_SIZE;
        int localBoxColumn = column - column % BOX_SIZE;

        for(int i=localBoxRow; i<localBoxRow + BOX_SIZE; i++){
           for(int j=localBoxColumn; j<localBoxColumn + BOX_SIZE; j++){
               char c = board[i][column];
               if (Character.isDigit(c) && Character.getNumericValue(c) == number)
                   return true;
           }
        }
        return false;
    }

    private static boolean isValidPlacement(char[][] board, int number, int row, int column){
        return !isNumberInRow(board, number, row) &&
               !isNumberInColumn(board, number, column) &&
               !isNumberInBox(board, number, row, column);
    }

    public static boolean solveBoard(char[][] board){
        for(int row=0; row<GRID_SIZE; row++){
            for(int column=0; column<GRID_SIZE; column++){
                if(board[row][column] == '.'){
                    for(int numberToTry=1; numberToTry<=9; numberToTry++){
                        if(isValidPlacement(board, numberToTry, row, column)){
                            board[row][column] = (char)(numberToTry + '0');

                            if(solveBoard(board)){
                                return true;
                            } else{
                                board[row][column] = '.';
                            }
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    private static void printBoard(char[][] board){
        for(int r=0; r<GRID_SIZE; r++){
            if(r % 3 == 0 && r != 0){
                System.out.println("-----------");
            }
            for(int d=0; d<GRID_SIZE; d++){
                if(d % 3 == 0 && d != 0){
                    System.out.print("|");
                }
                System.out.print(board[r][d]);
            }
            System.out.println();
        }
        System.out.println();
    }

}
