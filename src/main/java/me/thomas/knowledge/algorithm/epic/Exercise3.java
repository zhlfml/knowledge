package me.thomas.knowledge.algorithm.epic;

/**
 * @author zhaoxinsheng
 * @date 2019/02/19 Tuesday
 */
public class Exercise3 {

    public static String walk(int[][] matrix) {
        if (matrix == null) {
            throw new IllegalArgumentException("matrix is null");
        }
        int top = 0,
                down = matrix.length,
                right = matrix[0].length,
                left = 0;
        int i = 0,
                j = 0,
                // the end position
                endi = matrix.length / 2,
                endj = matrix[0].length / 2;
        // current position
        char dir = 'R';
        StringBuilder builder = new StringBuilder(down * right * 11);
        builder.append(matrix[i][j]);
        for (; !(i == endi && j == endj); ) {
            switch (dir) {
                case 'R': // Right
                    if (++j == right - 1) {
                        top++;
                        dir = 'D';
                    }
                    break;
                case 'D': // Down
                    if (++i == down - 1) {
                        right--;
                        dir = 'L';
                    }
                    break;
                case 'L': // Left
                    if (--j == left) {
                        down--;
                        dir = 'U';
                    }
                    break;
                case 'U': // Up
                    if (--i == top) {
                        left++;
                        dir = 'R';
                    }
                    break;
            }
            builder.append(',').append(matrix[i][j]);
        }
        return builder.toString();
    }

}
