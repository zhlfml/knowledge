package me.thomas.knowledge.concurrent;

import me.thomas.knowledge.algorithm.dp.Fibonacci;

import java.util.concurrent.*;

/**
 * 该程序的目的是为了验证提交给CompletionService的耗时计算,
 * 是否先计算完成的先被completionService.take()取出?
 *
 * 从程序的输出结果来看,的确时这样,原因是先计算出来的结果,先被放进了BlockingQueue中.
 *
 * @author zhaoxinsheng
 * @date 6/14/16.
 */
public class CompletionServiceTutorial {

    private final ExecutorService executorService;
    private Fibonacci fibonacci = Fibonacci.getInstance();
//    private CompletionService<Long> completionService;

    // 如果一个final字段在定义处没有被赋值,那么必须在构造函数中赋值,以确保在使用前被初始化.
    public CompletionServiceTutorial(ExecutorService executorService) {
        this.executorService = executorService;
//        this.completionService = new ExecutorCompletionService<>(executorService);
    }


    public void veryLongTimeCalculate() {
        // CompletionService不能共享,只能定义成局部变量.否则会造成线程A产生的结果被线程B取走的情况.
        CompletionService<Long> completionService = new ExecutorCompletionService<>(executorService);
        try {
            for (int i = 47; i > 40; i--) {
                int number = i;
    //            completionService.submit(new Callable<Long>() {
    //                @Override
    //                public Long call() throws Exception {
    //                    return fibonacci.fib(number);
    //                }
    //            });

                // 函数式接口可以使用lambda表达式 (注:只有唯一一个抽象方法的接口叫做函数式接口)
                completionService.submit(() -> fibonacci.fib(number));
            }
        } finally {
            // 不在接受新任务,但是线程池会等到池中现有的任务处理完之后才会退出.
//            executorService.shutdown();
        }

        try {
            if ("pool-1-thread-1".equals(Thread.currentThread().getName())) {
                for (int i = 54; i > 40; i--) {
                    Future<Long> f = completionService.take();
                    Long result = f.get();
                    System.out.println(Thread.currentThread().getName() + " -> " + result);
                }
            }
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
        } catch (ExecutionException ee) {
            throw new RuntimeException(ee.getCause());
        }
    }

    public static void main(String[] args) {
        ExecutorService es1 = Executors.newFixedThreadPool(2);
        ExecutorService es = Executors.newFixedThreadPool(7);
        CompletionServiceTutorial tutorial = new CompletionServiceTutorial(es);
        // 开启两个线程,用来测试是否能将CompletionService定义成类变量 -- 结论不可以, 但是ExecutorService对象可以.
        es1.submit(() -> tutorial.veryLongTimeCalculate());
        es1.submit(() -> tutorial.veryLongTimeCalculate());
    }

}
