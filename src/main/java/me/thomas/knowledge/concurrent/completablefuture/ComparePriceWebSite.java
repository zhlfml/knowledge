package me.thomas.knowledge.concurrent.completablefuture;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * 比价网
 *
 * @author zhaoxinsheng
 * @date 28/11/2017.
 */
public class ComparePriceWebSite {

    public List<String> findPricesV1(List<Shop> shops, String product) {
        return shops.stream()
                .map(shop -> String.format("%s price is %.2f", shop.getName(), shop.getPrice(product)))
                .collect(toList());
    }

    public static void main(String[] args) {
        List<Shop> shops = Arrays.asList(new Shop("BestPrice"),
                new Shop("LetsSaveBig"),
                new Shop("MyFavoriteShop"),
                new Shop("BuyItAll"));

        ComparePriceWebSite webSite = new ComparePriceWebSite();
        long start = System.nanoTime();
        System.out.println(webSite.findPricesV1(shops, "iphoneX"));
        long duration = (System.nanoTime() - start) / 1_000_000;
        System.out.println("Done in " + duration + "ms");
    }
}
