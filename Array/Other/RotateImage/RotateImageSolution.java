package Array.Other.RotateImage;

import java.util.Arrays;

public class RotateImageSolution {

    public static void rotate(int[][] matrix) {

        if(matrix.length == 0 || matrix.length != matrix[0].length) return;

        for(int layer=0; layer<matrix.length/2; layer++){
            int first = layer;
            int last = matrix.length - 1 - layer;
            for(int i=first; i<last; i++){
                int offset = i - first;
                int temp = matrix[first][i];

                matrix[first][i] = matrix[last-offset][first];
                matrix[last-offset][first] = matrix[last][last-offset];
                matrix[last][last-offset] = matrix[i][last];
                matrix[i][last] = temp;
            }
        }
    }

    public static void main(String[] args) {

        int[][] matrix1 = {{1,2,3},{4,5,6},{7,8,9}};
        rotate(matrix1);
        int[][] rotatedMatrix1 = {{7,4,1},{8,5,2},{9,6,3}};
        System.out.println(Arrays.deepEquals(matrix1, rotatedMatrix1));

        int[][] matrix2 = {{5,1,9,11},{2,4,8,10},{13,3,6,7},{15,14,12,16}};
        rotate(matrix2);
        int[][] rotatedMatrix2 = {{15,13,2,5},{14,3,4,1},{12,6,8,9},{16,7,10,11}};
        System.out.println(Arrays.deepEquals(matrix2, rotatedMatrix2));
    }

}
