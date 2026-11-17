
#include <iostream>
#include <algorithm>
#include <iomanip>
using namespace std;

int main() {
    int n;
    double capacity;

    cout << "Number of items: ";
    cin >> n;

    double value[100], weight[100], ratio[100];

    // Input item values and weights
    for (int i = 0; i < n; i++) {
        cout << "Value and weight of item " << i + 1 << ": ";
        cin >> value[i] >> weight[i];

        if (weight[i] <= 0) {
            cout << "Weight must be positive.\n";
            return 1;
        }

        ratio[i] = value[i] / weight[i];
    }

    cout << "Knapsack capacity: ";
    cin >> capacity;

    // Sort items by value/weight ratio
    for (int i = 0; i < n; i++) {
        for (int j = i + 1; j < n; j++) {

            if (ratio[i] < ratio[j]) {
                swap(ratio[i], ratio[j]);
                swap(value[i], value[j]);
                swap(weight[i], weight[j]);
            }
        }
    }

    double total = 0;

    // Select items using Greedy method
    for (int i = 0; i < n; i++) {

        if (capacity <= 0)
            break;

        double take = min(capacity, weight[i]);

        total = total + take * ratio[i];

        capacity = capacity - take;

        cout << "Taken weight: " << take
             << ", value: " << take * ratio[i] << endl;
    }

    cout << fixed << setprecision(2);
    cout << "Maximum value = " << total << endl;

    return 0;
}
