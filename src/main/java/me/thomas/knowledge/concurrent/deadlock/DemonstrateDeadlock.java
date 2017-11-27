package me.thomas.knowledge.concurrent.deadlock;

import java.util.Random;

/**
 * @author zhaoxinsheng
 * @date 27/11/2017.
 */
public class DemonstrateDeadlock {

    private static final int NUM_THREADS = 20;
    private static final int NUM_ACCOUNTS = 5;
    private static final int NUM_ITERATIONS = 10_000;

    public static void main(String[] args) {
        final Random rnd = new Random();
        final Account[] accounts = new Account[NUM_ACCOUNTS];

        for (int i = 0; i < NUM_ACCOUNTS; i++) {
            accounts[i] = new Account("Account_" + i, 10_000_000);
        }

        for (int i = 0; i < NUM_THREADS; i++) {
            new Thread(() -> {
                for (int j = 0; j < NUM_ITERATIONS; j++) {
                    int fromAccount = rnd.nextInt(NUM_ACCOUNTS);
                    int toAccount = rnd.nextInt(NUM_ACCOUNTS);
                    accounts[fromAccount].transferMoneyNotThreadSafe(accounts[toAccount], rnd.nextInt(1000));
                }
            }).start();
        }
    }
}
