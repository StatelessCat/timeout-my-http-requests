package FutureAndCallable;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Main {

    public static void main(String[] args) {

        ExecutorService executor = Executors.newCachedThreadPool();

        List<FibonacciCallable> l = new LinkedList<>();
        List<FutureTask<Integer>> lft = new LinkedList<>();

        int max = 45;

        for(int i = 0; i <= max; ++i) {
            l.add(new FibonacciCallable(i)); 
        }

        for(int i = 0; i <= max; ++i) {
            lft.add(new FutureTask<>(l.get(i)));
        }

        for(int i = 0; i <= max; ++i) {
            executor.execute(lft.get(i));
            System.out.println(i + ": lancé");
        }

        for(int i = 0; i <= max; ++i) {
            try {
                System.out.println(i + ":" + lft.get(i).get(15, TimeUnit.SECONDS));
            } catch (InterruptedException | ExecutionException | TimeoutException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        executor.shutdown();
        
    }
}
