package me.thomas.knowledge.algorithm;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Scanner;

/**
 * @author zhaoxinsheng
 * @date 2020/05/24 Sunday
 */
public class GuessNumber {

    static int HIGH = 10000;

    public static void main(String[] args) throws UnsupportedEncodingException {

        String str = "何";

//        System.out.println(Arrays.toString("str".getBytes("ISO-8859-1")));
//        System.out.println(Arrays.toString(str.getBytes("ISO-8859-1")));
        System.out.println(Arrays.toString(str.getBytes(StandardCharsets.UTF_8)));
        System.out.println(Arrays.toString(str.getBytes("GBK")));

        int low = 0, high = HIGH;
        Scanner scanner = new Scanner(System.in);
        String input;
        do {
            int guess = (low + high) / 2;
            System.out.println(guess + "?");
            input = scanner.next();
            if ("=".equals(input)) {
                low = 0;
                high = HIGH;
            } else if ("d".equals(input)) {
                high = guess;
            } else {
                low = guess;
            }
        } while (true);
    }
}
