#include <iostream>
#include <map>
#include <vector>

using namespace std;

struct Node {
    map<string, Node *> m;
};

void printNodes(Node *cur, string depth) {
    for (auto [food, next] : cur->m) {
        cout << depth << food << '\n';
        printNodes(next, depth + "--");
    }
}

int main() {
    int N, K;
    cin >> N;

    Node *root = new Node;
    while (N--) {
        cin >> K;
        Node *cur = root;
        while (K--) {
            string food;
            cin >> food;

            if (cur->m.find(food) == cur->m.end()) cur->m[food] = new Node;
            cur = cur->m[food];
        }
    }

    printNodes(root, "");

    return 0;
}