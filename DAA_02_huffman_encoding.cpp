
#include <iostream>
#include <queue>
#include <map>
using namespace std;

// Create a node
struct Node {
    char ch;
    int freq;
    Node *left, *right;

    Node(char c, int f) {
        ch = c;
        freq = f;
        left = right = NULL;
    }
};

// Generate Huffman codes
void generateCodes(Node* root, string code, map<char, string>& codes) {

    if (root == NULL)
        return;

    // If leaf node, store the code
    if (root->left == NULL && root->right == NULL) {
        codes[root->ch] = (code == "") ? "0" : code;
        return;
    }

    generateCodes(root->left, code + "0", codes);
    generateCodes(root->right, code + "1", codes);
}

int main() {

    string text;
    cout << "Enter text: ";
    getline(cin, text);

    if (text.empty()) {
        cout << "Empty input.";
        return 0;
    }

    // Count frequency of characters
    map<char, int> freq;

    for (char c : text) {
        freq[c]++;
    }

    // Create priority queue
    priority_queue<pair<int, Node*>,
        vector<pair<int, Node*>>,
        greater<pair<int, Node*>>> pq;

    // Insert characters into queue
    for (auto x : freq) {
        pq.push({x.second, new Node(x.first, x.second)});
    }

    // Build Huffman tree
    while (pq.size() > 1) {

        Node* left = pq.top().second;
        pq.pop();

        Node* right = pq.top().second;
        pq.pop();

        Node* parent = new Node('\0',
            left->freq + right->freq);

        parent->left = left;
        parent->right = right;

        pq.push({parent->freq, parent});
    }

    // Generate Huffman codes
    Node* root = pq.top().second;

    map<char, string> codes;
    generateCodes(root, "", codes);

    // Display codes
    cout << "\nHuffman Codes:\n";

    for (auto x : codes) {
        cout << x.first << " : " << x.second << endl;
    }

    // Encode original text
    cout << "\nEncoded text: ";

    for (char c : text) {
        cout << codes[c];
    }

    cout << endl;

    return 0;
}
