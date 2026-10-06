import java.util.Arrays;
public class Main {
    static long fibonacci(int n) {
        if (n < 0 || n > 92) throw new IllegalArgumentException("Entero de 0 a 92");
        long[] memo = new long[Math.max(2, n+1)]; Arrays.fill(memo, -1);
        memo[0] = 0; memo[1] = 1;
        return calcular(n, memo);
    }
    static long calcular(int n, long[] memo) {
        if (memo[n] < 0) memo[n] = Math.addExact(calcular(n-1,memo), calcular(n-2,memo));
        return memo[n];
    }
    public static void main(String[] args) {
        for (int n : new int[]{0, 1, 6, 10}) System.out.println(n + " -> " + fibonacci(n));
    }
}
