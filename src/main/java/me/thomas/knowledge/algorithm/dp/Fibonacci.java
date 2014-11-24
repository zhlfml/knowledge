package me.thomas.knowledge.algorithm.dp;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by thomas on 6/6/14.
 */
public class Fibonacci {

    private Map<Long, Long> map = new HashMap<Long, Long>();

    public long fib(long n) {
        if (n == 0 || n == 1) {
            return 1;
        } else {
            return fib(n - 1) + fib(n - 2);
        }
    }

    public long fib2(long n) {
        long result = 0;
        if (n == 0 || n == 1) {
            result = 1;
        } else {
            if (map.containsKey(n)) {
                return map.get(n);
            } else {
                result = fib2(n - 1) + fib2(n - 2);
            }
        }
        map.put(n, result);

        return result;
    }

    public static void main(String[] args) {
        Fibonacci f = new Fibonacci();
        long result = f.fib2(43);
        System.out.println(result);
    }
}
