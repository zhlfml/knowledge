package me.thomas.knowledge.algorithm.epic;

/**
 * @author zhaoxinsheng
 * @date 2019/02/19 Tuesday
 */
public class Exercise4 {

    private static char[] dicts = new char[]{
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
            'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't',
            'u', 'v', 'w', 'x', 'y', 'z'
    };

    public static String itoa(int value, int base) {
        if (base < 2 || base > 36) {
            throw new IllegalArgumentException("base range must in [2, 36]");
        }
        boolean negative = false;
        if (value < 0) {
            negative = true;
            value = 0 - value;
        }
        int i = 0;
        char[] result = new char[33];
        do {
            result[i++] = dicts[value % base];
            value = value / base;
        } while (value > 0);
        if (negative) {
            result[i++] = '-';
        }
        return new String(reverse(result, i));
    }

    private static char[] reverse(char[] chars, int length) {
        char[] result = new char[length];
        for (int i = length - 1, j = 0; i >= 0; i--) {
            result[j++] = chars[i];
        }
        return result;
    }
}
