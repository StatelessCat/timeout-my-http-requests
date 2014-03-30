package FutureAndCallable;

import java.util.concurrent.Callable;

public class FibonacciCallable implements Callable<Integer> {
    
    private int n;
    
	public FibonacciCallable(int n){
		this.n = n;
	}
    
    private int fibo(int n) {
        if(n == 0) {
            return 0;
        }
        else if (n == 1) {
            return 1;
        }
        else {
            return fibo(n-1) + fibo(n-2);
        }
    }
    
    @Override
    public Integer call() throws Exception {
        return fibo(n);
    }
}
