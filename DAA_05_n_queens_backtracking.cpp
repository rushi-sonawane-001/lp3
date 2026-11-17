
#include <iostream>
#include <cmath>
using namespace std;

int n, solutions = 0;
int board[15][15] = {0};

// Check whether queen can be placed safely
bool safe(int row, int col) {

    // Check same column
    for (int i = 0; i < row; i++) {
        if (board[i][col] == 1)
            return false;
    }

    // Check upper left diagonal
    for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
        if (board[i][j] == 1)
            return false;
    }

    // Check upper right diagonal
    for (int i = row - 1, j = col + 1; i >= 0 && j < n; i--, j++) {
        if (board[i][j] == 1)
            return false;
    }

    return true;
}

// Place queens using backtracking
void solve(int row) {

    // All queens are placed
    if (row == n) {
        solutions++;

        cout << "Solution " << solutions << ":\n";

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 1)
                    cout << "Q ";
                else
                    cout << ". ";
            }
            cout << endl;
        }

        cout << endl;
        return;
    }

    // Try every column
    for (int col = 0; col < n; col++) {

        if (safe(row, col)) {

            board[row][col] = 1;

            solve(row + 1);

            // Backtrack
            board[row][col] = 0;
        }
    }
}

int main() {

    cout << "Enter N (1 to 15): ";
    cin >> n;

    if (n < 1 || n > 15) {
        cout << "Invalid N.\n";
        return 1;
    }

    solve(0);

    cout << "Total solutions = " << solutions << endl;

    return 0;
}
