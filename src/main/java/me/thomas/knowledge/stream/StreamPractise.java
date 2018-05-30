package me.thomas.knowledge.stream;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import me.thomas.knowledge.stream.entity.Trader;
import me.thomas.knowledge.stream.entity.Transaction;

/**
 * @author zhaoxinsheng
 * @date 23/08/2017.
 */
public class StreamPractise {

    public static void main(String[] args) {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");

        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 700),
                new Transaction(mario, 2012, 710),
                new Transaction(alan, 2012, 950)
        );

        System.out.println("Question 1");
        transactions.stream()
                .filter(transaction -> transaction.getYear() == 2011)
                .sorted(Comparator.comparing(Transaction::getValue))
                .forEach(System.out::println);

        System.out.println("Question 2");
        transactions.stream()
                .map(Transaction::getTrader)
                .map(Trader::getCity)
                .distinct()
                .forEach(System.out::println);

        System.out.println("Question 3");
        transactions.stream()
                .map(Transaction::getTrader)
                .filter(trader -> "Cambridge".equalsIgnoreCase(trader.getCity()))
                .distinct()
                .sorted(Comparator.comparing(Trader::getName))
                .forEach(System.out::println);

        System.out.println("Question 4");
        String result = transactions.stream()
                .map(transaction -> transaction.getTrader().getName())
                .distinct()
                .sorted(String::compareTo)
                .collect(Collectors.joining());
        System.out.println(result);

        System.out.println("Question 5");
        boolean match = transactions.stream()
                .map(Transaction::getTrader)
                .anyMatch(trader -> "Milan".equalsIgnoreCase(trader.getCity()));
        System.out.println(match);

        System.out.println("Question 6");
        transactions.stream()
                .filter(transaction -> "Cambridge".equalsIgnoreCase(transaction.getTrader().getCity()))
                .map(Transaction::getValue)
                .forEach(System.out::println);

        System.out.println("Question 7");
        OptionalInt max = transactions.stream()
                .mapToInt(Transaction::getValue)
                .max();
        System.out.println(max.isPresent() ? max.getAsInt() : 0);

        System.out.println("Question 8");
        Optional<Transaction> minTransaction =  transactions.stream()
                //.min(Comparator.comparingInt(Transaction::getValue));
                .reduce((a, b) -> a.getValue() < b.getValue() ? a : b);
        System.out.println(minTransaction.isPresent() ? minTransaction: null);
    }

}
