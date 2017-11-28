package me.thomas.knowledge.concurrent.completablefuture;

import java.util.Arrays;
import java.util.List;
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
                .map(shop -> String.format("%s price is %.2f", shop.getName(), shop.getPrice(product)))
                .collect(toList());
    }

    public List<String> findPricesV2(List<Shop> shops, String product) {
        return shops.parallelStream()
                .map(shop -> String.format("%s price is %.2f", shop.getName(), shop.getPrice(product)))
                .collect(toList());
    }

    public List<String> findPricesV3(List<Shop> shops, String product) {
        return shops.stream()
                .map(shop -> shop.getPrice2Async(product))
                .map(CompletableFuture::join)
                .collect(toList());
    }

    public List<String> findPricesV4(List<Shop> shops, String product) {
        List<CompletableFuture<String>> futures = shops.stream()
                .map(shop -> shop.getPrice2Async(product))
                .collect(toList());

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

    public static void main(String[] args) {
        System.out.println(Runtime.getRuntime().availableProcessors());
        List<Shop> shops = Arrays.asList(new Shop("BestPrice"),
                new Shop("LetsSaveBig"),
                new Shop("MyFavoriteShop"),
                new Shop("BuyItAll"));

        ComparePriceWebSite webSite = new ComparePriceWebSite();
        long start = System.nanoTime();
        System.out.println(webSite.findPricesV5(shops, "iphoneX"));
        long duration = (System.nanoTime() - start) / 1_000_000;
        System.out.println("Done in " + duration + "ms");
    }
}
