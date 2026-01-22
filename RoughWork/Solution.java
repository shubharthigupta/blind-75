class Solution {

    public static final int GRID_SIZE = 9;
    public static final int BOX_SIZE = 3;

    public void solveSudoku(char[][] board) {
        if(solveAndCheck(board))
            System.out.println("Sudoku solved !");
        else
            System.out.println("Unsolvable Sudoku :(");
    }

    public boolean solveAndCheck(char[][] board){
        for(int i=0; i<GRID_SIZE; i++){
            for(int j=0; j<GRID_SIZE; j++){
                if(board[i][j] == '.'){
                    for(int numberToTry=1; numberToTry<=9; numberToTry++){
                        if(isValidPlacement(board, numberToTry, i, j)){
                            board[i][j] = (char)(i + '0');
                            if(solveAndCheck(board))
                                return true;
                            else
                                board[i][j] = '.';
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isNumberInRow(char[][] board, int number, int row){
        for(int i=0; i<GRID_SIZE; i++){
            if(Character.getNumericValue(board[row][i]) == number)
                return true;
        }
        return false;
    }

    public boolean isNumberInColumn(char[][] board, int number, int column){
        for(int i=0; i<GRID_SIZE; i++){
            if(Character.getNumericValue(board[i][column]) == number)
                return true;
        }
        return false;
    }

    public boolean isNumberInBox(char[][] board, int number, int row, int column){
        int localBoxRow = row - (row % BOX_SIZE);
        int localBoxColumn = column - (column % BOX_SIZE);

        for(int i=localBoxRow; i<(localBoxRow + 3); i++){
            for(int j=localBoxColumn; j<(localBoxColumn + 3); j++){
                if(Character.getNumericValue(board[i][column]) == number)
                    return true;
            }
        }
        return false;
    }

    public boolean isValidPlacement(char[][] board, int number, int row, int column){
        return !isNumberInRow(board, number, row)
                && !isNumberInColumn(board, number,column)
                && !isNumberInBox(board, number, row, column);
    }
}