package algorithms.dp;

public class Fibonacci {
    private int num = 0;

    public int fib(int n) {
        num++;

        if (n == 0)
            return 0;

        if (n == 1)
            return 1;

        return fib(n - 1) + fib(n - 2);
    }

    public int getNum() {
        return num;
    }

    public static void main(String[] args) {

        int n = 42;

        Fibonacci fib = new Fibonacci();
        long startTime = System.currentTimeMillis();
        int res = fib.fib(n);
        long endTime = System.currentTimeMillis();

        System.out.println("fib(" + n + ") = " + res);
        System.out.println("time : " + (endTime - startTime) + " ms");
        System.out.println("run function fib() " + fib.getNum() + " times.");
    }
}
