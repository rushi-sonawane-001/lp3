import java.util.Scanner;

public class Fibonacci {

    static long recSteps = 0;   // counts recursive calls
    static long iterSteps = 0;  // counts loop iterations

    // Non-recursive (iterative): O(n) time, O(1) space
    static long fibIterative(int n) {
        iterSteps = 0;
        if (n <= 1) {
            iterSteps++;
            return n;
        }
        long a = 0, b = 1;
        for (int i = 2; i <= n; i++) {
            long c = a + b;
            a = b;
            b = c;
            iterSteps++;
        }
        return b;
    }

    // Recursive: O(2^n) time, O(n) space (call stack)
    static long fibRecursive(int n) {
        recSteps++;
        if (n <= 1) return n;
        return fibRecursive(n - 1) + fibRecursive(n - 2);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter n: ");
        int n = sc.nextInt();
        sc.close();

        if (n < 0) {
            System.out.println("n must be non-negative");
            return;
        }

        // Iterative
        long t1 = System.nanoTime();
        long itRes = fibIterative(n);
        long t2 = System.nanoTime();

        System.out.println("\n--- Non-Recursive ---");
        System.out.println("F(" + n + ") = " + itRes);
        System.out.println("Steps      = " + iterSteps);
        System.out.println("Time (ns)  = " + (t2 - t1));

        System.out.print("Series     : ");
        long a = 0, b = 1;
        for (int i = 0; i <= n; i++) {
            System.out.print(a + " ");
            long c = a + b;
            a = b;
            b = c;
        }
        System.out.println();

        // Recursive (gets slow past n ~ 45)
        if (n > 45) {
            System.out.println("\nRecursive skipped: n > 45 takes too long (exponential).");
            return;
        }
        recSteps = 0;
        t1 = System.nanoTime();
        long recRes = fibRecursive(n);
        t2 = System.nanoTime();

        System.out.println("\n--- Recursive ---");
        System.out.println("F(" + n + ") = " + recRes);
        System.out.println("Calls      = " + recSteps);
        System.out.println("Time (ns)  = " + (t2 - t1));
    }
}
