package me.thomas.knowledge.concurrent;

import me.thomas.knowledge.algorithm.dp.Fibonacci;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.*;

/**
 * 该程序的目的是验证invokeAll()返回的结果是否和请求参数按序对应.
 *
 * 从程序执行情况来看,invokeAll()返回的结果和请求参数按序对应.
 * 另外还发现invokeAll()是一个阻塞方法,等到所有的任务执行完或者超时才会返回.
 *
 * @author zhaoxinsheng
 * @date 6/15/16.
 */
public class InvokeAllTutorial {

    private final ExecutorService executorService;

    public InvokeAllTutorial(ExecutorService executorService) {
        this.executorService = executorService;
    }

    public List<Long> getVeryLongCalculateResults(List<Integer> numbers, long time, TimeUnit unit) throws InterruptedException {
        List<CalculateTask> tasks = new ArrayList<>(numbers.size());
        for (Integer number : numbers) {
            tasks.add(new CalculateTask(number));
        }

        System.out.println("invokeAll at " + System.currentTimeMillis());
        List<Future<Long>> futures = executorService.invokeAll(tasks, time, unit);

        System.out.println("collect results at " + System.currentTimeMillis());
        List<Long> results = new ArrayList<>(numbers.size());
        Iterator<CalculateTask> iterator = tasks.iterator();
        for (Future<Long> future : futures) {
            CalculateTask task = iterator.next();
            try {
                System.out.println(task.getNumber() + " want to put in results at " + System.currentTimeMillis());
                results.add(future.get());
                System.out.println(task.getNumber() + " success put in results at " + System.currentTimeMillis());
            } catch (ExecutionException e) {
                results.add(task.getFailureResult());
                System.out.println(task.getNumber() + " success put in results at " + System.currentTimeMillis());
            } catch (CancellationException e) {
                results.add(task.getTimeoutResult());
                System.out.println(task.getNumber() + " success put in results at " + System.currentTimeMillis());
            }
        }

        return results;
    }

    private class CalculateTask implements Callable<Long> {

        private final int number;
        private final Fibonacci fibonacci = Fibonacci.getInstance();

        public CalculateTask(int number) {
            this.number = number;
        }

        public int getNumber() {
            return number;
        }

        @Override
        public Long call() throws Exception {
            return fibonacci.fib(number);
        }

        public Long getFailureResult() {
            System.out.println("getFailureResult(" + this.number + ")");
            return fibonacci.fib2(number);
        }

        public Long getTimeoutResult() {
            System.out.println("getTimeoutResult(" + this.number + ")");
            return fibonacci.fib2(number);
        }
    }

    public static void main(String[] args) {
        List<Integer> numbers = new ArrayList<>();
        for (int i = 47; i > 40; i--) {
            numbers.add(i);
        }

        ExecutorService executorService = Executors.newFixedThreadPool(5);
        try {
            new InvokeAllTutorial(executorService).getVeryLongCalculateResults(numbers, 13, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            executorService.shutdown();
        }
    }
}
