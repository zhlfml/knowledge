package me.thomas.knowledge.stream;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import me.thomas.knowledge.stream.entity.Dish;

/**
 * @author zhaoxinsheng
 * @date 8/7/16.
 */
public class StreamTutorial {

    public static void main(String[] args) {
        List<Dish> menu = Arrays.asList(
                new Dish("pork", false, 800, Dish.Type.MEAT),
                new Dish("beef", false, 700, Dish.Type.MEAT),
                new Dish("chicken", false, 400, Dish.Type.MEAT),
                new Dish("french fries", true, 530, Dish.Type.OTHER),
                new Dish("rice", true, 350, Dish.Type.OTHER),
                new Dish("season fruit", true, 120, Dish.Type.OTHER),
                new Dish("pizza", true, 550, Dish.Type.OTHER),
                new Dish("prawns", false, 300, Dish.Type.FISH),
                new Dish("salmon", false, 450, Dish.Type.FISH)
        );

        List<String> lowCaloricDishNames = menu.parallelStream()
                .filter(d -> {
                    System.out.println(Thread.currentThread().getName() + " -> " + d.getName());
                    return d.getCalories() < 400;
                })
                .sorted(Comparator.comparing(Dish::getCalories))
                .map(Dish::getName)
                .collect(Collectors.toList());
        lowCaloricDishNames.forEach(System.out::println);
        lowCaloricDishNames.forEach(s -> System.out.println(s));
        lowCaloricDishNames.parallelStream().forEach(s -> System.out.println(s));

        Map<Dish.Type, List<Dish>> dishedByType = menu.parallelStream()
                .collect(Collectors.groupingBy(Dish::getType));
        dishedByType.forEach((type, list) -> {
            System.out.println(type);
            list.forEach(d -> System.out.println(d.getName()));
            System.out.println("---");
        });
    }
}
