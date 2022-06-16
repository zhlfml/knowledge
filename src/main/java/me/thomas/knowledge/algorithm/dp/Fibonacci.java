package me.thomas.knowledge.algorithm.dp;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by thomas on 6/6/14.
 */
public class Fibonacci {
    // 使用ConcurrentHashMap提高并发性能和安全性
    private Map<Long, Long> map = new ConcurrentHashMap<>();

    private static final Fibonacci instance = new Fibonacci();

    private Fibonacci() {
    }

    public static Fibonacci getInstance() {
        return instance;
    }

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
        map.putIfAbsent(n, result);

        return result;
    }

    public long fib3(long n) {
        // write code here
        int f0 = 1;
        int f1 = 1;
        int f2 = 0;

        for(int i = 2; i < n; i++){
            f2 = f0 + f1;
            f0 = f1;
            f1 = f2;
        }
        return f2;
    }

    private void clearCache() {
        map.clear();
    }

    public static void main(String[] args) {
        long startTime = System.nanoTime();
        for (int i = 0; i < 100_000_000; i++) {
            Fibonacci fibonacci = Fibonacci.getInstance();
            fibonacci.fib2(89);
            fibonacci.clearCache();
        }
        System.out.println((System.nanoTime() - startTime) / 1000_000);

        long startTime2 = System.nanoTime();
        for (int i = 0; i < 100_000_000; i++) {
            Fibonacci.getInstance().fib3(89);
        }
        System.out.println((System.nanoTime() - startTime2) / 1000_000);

    }
}
