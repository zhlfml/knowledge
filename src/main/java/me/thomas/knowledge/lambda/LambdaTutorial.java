package me.thomas.knowledge.lambda;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiPredicate;

/**
 * @author zhaoxinsheng
 * @date 10/08/2017.
 */
public class LambdaTutorial {

    public static void main(String[] args) {
        BiPredicate<String, List<String>> contains = (string, list) -> list.contains(string);
//        BiPredicate<String, List<String>> contains2 = List::contains;
        BiPredicate<List<String>, String> contains3 = List::contains;

        List<Integer> list = Arrays.asList(6, 1, 2, 3, 4, 5);
        list.sort(Comparator.comparingInt(Integer::intValue));
        list.forEach(System.out::print);
    }
}
