package me.thomas.knowledge.algorithm.epic;

/**
 * @author zhaoxinsheng
 * @date 2019/02/19 Tuesday
 */
public class Exercise3 {

    public static String traverse(int[][] matrix) {
        if (matrix == null) {
            throw new IllegalArgumentException("matrix is null");
        }
        // boundary
        int top = 0,
                left = 0,
                down = matrix.length - 1,
                right = matrix[0].length - 1;
        // current position
        int i = 0,
                j = -1;
        // traverse direction
        char direction = 'R';
        int arrived = 0,
                total = (down + 1) * (right + 1);
        StringBuilder builder = new StringBuilder(total * 11);
        while (arrived < total) {
            switch (direction) {
                case 'R': // Right
                    if (++j == right) {
                        top++;
                        direction = 'D';
                    }
                    break;
                case 'D': // Down
                    if (++i == down) {
                        right--;
                        direction = 'L';
                    }
                    break;
                case 'L': // Left
                    if (--j == left) {
                        down--;
                        direction = 'U';
                    }
                    break;
                case 'U': // Up
                    if (--i == top) {
                        left++;
                        direction = 'R';
                    }
                    break;
            }
            if (arrived++ > 0) {
                builder.append(',');
            }
            builder.append(matrix[i][j]);
        }
        return builder.toString();
    }

}
