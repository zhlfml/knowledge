package me.thomas.knowledge.concurrent;

import me.thomas.knowledge.utils.HttpClient;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * VM options: -Durl=http://localhost/grab/g?n=20 -Dperiod=100 -Dduration=10
 *
 * Created by zhaoxs on 2016/1/28 0028.
 */
public class ScheduledExecutorServiceDemo {

    public static void main(String[] args) {
        final String url = System.getProperty("url");
        // 两次开始执行最小间隔时间，单位毫秒
        String period = System.getProperty("period");
        // 定时任务持续时间，单位秒
        String duration = System.getProperty("duration");

        boolean invalid = false;
        if (period == null || period.length() == 0) {
            invalid = true;
        } else if (url == null || url.length() == 0) {
            invalid = true;
        } else if (duration == null || duration.length() == 0) {
            invalid = true;
        } else if (!period.matches("[1-9]\\d*")) {
            invalid = true;
        } else if (!duration.matches("[1-9]\\d*")) {
            invalid = true;
        }
        if (invalid) {
            System.out.println("Usage: java -jar -Durl=<url> -Dperiod=<number> -Dduration=<duration> path/delivery.jar");
            System.exit(-1);
        }

        final long start = System.currentTimeMillis();
        final long durationMillis = Long.parseLong(duration) * 1000;
        final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.scheduleAtFixedRate(new Runnable() {
            public void run() {
                HttpClient.get(url);
                if (System.currentTimeMillis() - start > durationMillis) {
                    executorService.shutdown();
                }
            }
        }, 0, Long.parseLong(period), TimeUnit.MILLISECONDS);
    }
}
