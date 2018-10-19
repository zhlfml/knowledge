package me.thomas.knowledge.concurrent.completablefuture;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import static java.util.stream.Collectors.toList;

/**
 * 比价网
 *
 * @author zhaoxinsheng
 * @date 28/11/2017.
 */
public class ComparePriceWebSite {

    private final Executor executor = Executors.newFixedThreadPool(100, r -> {
        Thread thread = new Thread(r);
        thread.setDaemon(true);
        return thread;
    });

    public List<String> findPricesV1(List<Shop> shops, String product) {
        return shops.stream()
                .map(shop -> String.format("%s price is %.2f", shop.getName(), shop.getPriceV2(product)))
                .collect(toList());
    }

    /**
     * 经过测试，发现在4个CPUs的情况下，输出结果如下：
     * 1 Start : 1539912492661
     * 12: 1539912492674
     * 10: 1539912492674
     * 11: 1539912492674
     * 1: 1539912492674
     * [BestPrice price is 123.17, LetsSaveBig price is 135.87, MyFavoriteShop price is 138.29, BuyItAll price is 172.53]
     * 1 Done : 1539912493709
     * Done in 1048ms
     *
     * 分析消耗只有1秒多点的原因是：并行流情况下，线程id=1的主线程很快被释放出来参与了计算
     */
    public List<String> findPricesV2(List<Shop> shops, String product) {
        return shops.parallelStream()
                .map(shop -> String.format("%s price is %.2f", shop.getName(), shop.getPriceV2(product)))
                .collect(toList());
    }

    public List<String> findPricesV3(List<Shop> shops, String product) {
        return shops.stream()
                .map(shop -> shop.getPrice2Async(product))
                .map(CompletableFuture::join)
                .collect(toList());
    }

    /**
     * 经过测试，发现在4个CPUs的情况下，输出结果如下：
     * 1 Start : 1539912221838
     * 10: 1539912221863
     * 11: 1539912221864
     * 12: 1539912221864
     * Time past: 25
     * 11: 1539912222895
     * [BestPrice:168.75:DIAMOND, LetsSaveBig:175.36:NONE, MyFavoriteShop:166.78:NONE, BuyItAll:192.70:GOLD]
     * 1 Done : 1539912223899
     * Done in 2061ms
     *
     * 分析消耗超过2秒的原因是：线程id=11的线程使用了两次，也就是说串型流情况下，只有三个线程供CompletableFuture使用，主线程不能参与计算
     * 小知识：ForkJoinPool.commonPoolSize 默认情况下等于Runtime.getRuntime().availableProcessors() - 1. 见ForkJoinPool.makeCommonPool()
     */
    public List<String> findPricesV4(List<Shop> shops, String product) {
        long start = System.nanoTime();
        List<CompletableFuture<String>> futures = shops.stream()
                .map(shop -> shop.getPrice2Async(product))
                .collect(toList());
        System.out.println("Time past: " + (System.nanoTime() - start) / 1_000_000);
        return futures.stream()
                .map(CompletableFuture::join)
                .collect(toList());
    }

    public List<String> findPricesV5(List<Shop> shops, String product) {
        List<CompletableFuture<String>> futures = shops.stream()
                .map(shop -> shop.getPrice3Async(product, executor))
                .collect(toList());

        return futures.stream()
                .map(CompletableFuture::join)
                .collect(toList());
    }

    public List<String> findPricesV6(List<Shop> shops, String product) {
        List<CompletableFuture<String>> futures = shops.stream()
                .map(shop -> shop.getPrice3Async(product, executor))
                .map(future -> future.thenApply(Quote::parse))
                .map(future -> future.thenCompose(quote -> CompletableFuture.supplyAsync(() -> Discount.applyDiscount(quote), executor)))
                .collect(toList());

        return futures.stream()
                .map(CompletableFuture::join)
                .collect(toList());
    }

    /**
     * 并发执行示意图，Combine内的Future会和上一个Future同时进行
     * --------------------------------------------------
     * - getPrice3 |  getRandomInt |                    -
     * ----------------------------|     getExchange    -
     * -      getDiscount          |                    -
     * --------------------------------------------------
     */
    public List<String> findPricesV7(List<Shop> shops, String product) {
        List<CompletableFuture<String>> futures = shops.stream()
                .map(shop -> shop.getPrice3Async(product, executor))
                .map(future -> future.thenCombine(CompletableFuture.supplyAsync(() -> {Delay.delay(1); return new Random().nextInt();}, executor), (shop, name) -> name + "_hack_" + shop))
                .map(future -> future.thenApply(Quote::parse))
                .map(future -> future.thenCompose(quote -> CompletableFuture.supplyAsync(() -> Discount.applyDiscount(quote), executor)))
                .map(future -> future.thenCombine(CompletableFuture.supplyAsync(Exchange::rate), (price , rate) -> price + "*" + rate))
                .collect(toList());

        return futures.stream()
                .map(CompletableFuture::join)
                .collect(toList());
    }

    public static void main(String[] args) {
        List<Shop> shops = Arrays.asList(
                new Shop("BestPrice"),
                new Shop("LetsSaveBig"),
                new Shop("MyFavoriteShop"),
                new Shop("BuyItAll"));

        ComparePriceWebSite webSite = new ComparePriceWebSite();
        long start = System.nanoTime();
        System.out.println(Thread.currentThread().getId() + " Start : " + System.currentTimeMillis());
        System.out.println(webSite.findPricesV2(shops, "iphoneX"));
        System.out.println(Thread.currentThread().getId() + " Done : " + System.currentTimeMillis());
        long duration = (System.nanoTime() - start) / 1_000_000;
        System.out.println("Done in " + duration + "ms");
    }
}
