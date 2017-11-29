package me.thomas.knowledge.concurrent.completablefuture;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.stream.Stream;

/**
 * 只取最先响应的价格
 *
 * @author zhaoxinsheng
 * @date 29/11/2017.
 */
public class FirstPriceWebSite {

    private final Executor executor = Executors.newFixedThreadPool(100, r -> {
        Thread thread = new Thread(r);
        thread.setDaemon(true); // 设置为true至关重要，anyOf()的实现靠它
        return thread;
    });

    public Stream<CompletableFuture<String>> findPriceStream(List<Shop> shops, String product) {
        return shops.stream()
                .map(shop -> CompletableFuture.supplyAsync(() -> { Delay.delayRandom(0); return shop.getPrice(product);}, executor))
                .map(future -> future.thenApply(Quote::parse))
                .map(future -> future.thenCompose(quote -> CompletableFuture.supplyAsync(() -> Discount.applyDiscount(quote), executor)));
    }

    public static void main(String[] args) {
        List<Shop> shops = Arrays.asList(
                new Shop("BestPrice"),
                new Shop("LetsSaveBig"),
                new Shop("MyFavoriteShop"),
                new Shop("BuyItAll"));
        FirstPriceWebSite webSite = new FirstPriceWebSite();
        long start = System.nanoTime();
        CompletableFuture[] completableFutures = webSite.findPriceStream(shops, "iphone6s")
                .map(future -> future.thenAccept(s -> System.out.println(s + " (done in " + (System.nanoTime() - start) / 1_000_000 + "ms)")))
                .toArray(CompletableFuture[]::new);
        CompletableFuture.allOf(completableFutures).join();
        long duration = (System.nanoTime() - start) / 1_000_000;
        System.out.println("Done in " + duration + "ms");
        //Delay.delay(5);
    }
}
