#include <string>
#include <vector>
#include <algorithm>
#include <tuple>

using namespace std;

struct Node {
    int x, y ,n;
    Node* left = nullptr;
    Node* right = nullptr;
};

void insert(Node* parent, Node* child) {
    if (child->x < parent->x) {
        if (parent->left == nullptr) parent->left = child;
        else insert(parent->left, child);
    } else {
        if (parent->right == nullptr) parent->right = child;
        else insert(parent->right, child);
    }
}

void preorder(Node* node, vector<int>& answer) {
    if (node == nullptr) return;
    answer.push_back(node->n + 1); 
    preorder(node->left, answer);
    preorder(node->right, answer);
}

void postorder(Node* node, vector<int>& answer) {
    if (node == nullptr) return;
    postorder(node->left, answer);
    postorder(node->right, answer);
    answer.push_back(node->n + 1);
}

vector<vector<int>> solution(vector<vector<int>> nodeinfo) {  
    vector<Node*> nodes;
    for (int i = 0; i < nodeinfo.size(); i++) {
        nodes.push_back(new Node{nodeinfo[i][0], nodeinfo[i][1], i});
    }
    
    sort(nodes.begin(), nodes.end(), [](Node* a, Node* b) {
        if (a->y == b->y) return a->x < b->x;
        return a->y > b->y;
    });
    
    Node* root = nodes[0];
    for (int i = 1; i < nodes.size(); i++) {
        insert(root, nodes[i]);
    }
        
    vector<int> pre, post;
    preorder(root, pre);
    postorder(root, post);

    return {pre, post};
}