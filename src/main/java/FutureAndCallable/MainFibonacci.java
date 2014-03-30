package FutureAndCallable;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MainFibonacci {

    public static void main(String[] args) {

        ExecutorService executor = Executors.newCachedThreadPool();

        List<FibonacciCallable> l = new LinkedList<>();
        

        int max = 50;

        for(int i = 0; i <= max; ++i) {
            l.add(new FibonacciCallable(i)); 
        }

        List<Future<Integer>> lft = null;
        try {
            lft = executor.invokeAll(l, 1, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("lancés ! ");


        executor.shutdown();
        
    }
}
