
#include <iostream>
#include <ctime>
using namespace std;

// Fibonacci using Recursion
int fibRecursive(int n) {
    if (n <= 1)
        return n;

    return fibRecursive(n - 1) + fibRecursive(n - 2);
}

// Fibonacci using Iteration
int fibIterative(int n) {
    int a = 0, b = 1, c;

    if (n == 0)
        return 0;

    for (int i = 2; i <= n; i++) {
        c = a + b;
        a = b;
        b = c;
    }

    return b;
}

int main() {
    int n;

    cout << "Enter a number: ";
    cin >> n;

    clock_t start, end;

    // Recursive calculation
    start = clock();
    int r = fibRecursive(n);
    end = clock();

    double recursiveTime = double(end - start) / CLOCKS_PER_SEC;

    // Iterative calculation
    start = clock();
    int i = fibIterative(n);
    end = clock();

    double iterativeTime = double(end - start) / CLOCKS_PER_SEC;

    cout << "\nRecursive Fibonacci: " << r;
    cout << "\nIterative Fibonacci: " << i;

    cout << "\nRecursive Time: " << recursiveTime << " seconds";
    cout << "\nIterative Time: " << iterativeTime << " seconds";

    return 0;
}
