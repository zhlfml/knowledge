package me.thomas.knowledge.concurrent.completablefuture;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author zhaoxinsheng
 * @date 28/11/2017.
 */
public class Shop {

    private final Random rnd = new Random();

    private String name;

    public Shop(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public double getPrice(String product) {
        delay();
        return rnd.nextDouble() * product.charAt(0) + product.charAt(1);
    }

    public Future<String> getPriceAsync(String product) {
        CompletableFuture<String> future = new CompletableFuture<>();
        new Thread(() -> {
            try {
                double price = getPrice(product);
                future.complete(name + " price is " + price);
            } catch (Exception ex) {
                future.completeExceptionally(ex);
            }
        }).start();
        return future;
    }

    public CompletableFuture<String> getPrice2Async(String product) {
        return CompletableFuture.supplyAsync(() -> name + " price is " + getPrice(product));
    }

    private static void delay() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException ignored) {
        }
    }

    public static void main(String[] args) {
        Shop shop = new Shop("BestShop");
        long start = System.nanoTime();
        Future<String> futurePrice = shop.getPrice2Async("");
        long invocationTime = (System.nanoTime() - start) / 1_000_000;
        System.out.println("Invocation returned after " + invocationTime + "ms");
        try {
            String price = futurePrice.get(2, TimeUnit.SECONDS);
            System.out.printf(price);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } catch (ExecutionException e) {
            throw new RuntimeException(e.getCause());
        } catch (TimeoutException e) {
            throw new RuntimeException(e);
        }
        long retrievalTime = (System.nanoTime() - start) / 1_000_000;
        System.out.println("Price returned after " + retrievalTime + "ms");
    }
}
