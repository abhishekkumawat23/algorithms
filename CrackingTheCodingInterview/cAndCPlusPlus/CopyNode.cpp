/**
 * Write a method that takes a pointer to a Node structure as a parameter,
 * and returns a complete copy of the passed in data structure.
 * The Node data structure contains two pointers to other Nodes.
 * 
 * Important points:
 * 1. For copying we will use recursion.
 * 2. As there are duplicate computation, we will use memoization.
 */
 
typedef map<Node*, Node*> NodeMap;

Node * copy_structure(Node * root) {
    NodeMap nodeMap; // Empty map
    return copy_recusive(root, nodeMap);
}

Node * copy_recusive(Node * current, NodeMap nodeMap) {
    if (current == NULL) {
        return NULL;
    }
    
    // Check is current present in nodeMap.
    NodeMap::iterator i = nodeMap.find(current);
    if (i != nodeMap.end()) {
        return i->second;
    }
    
    // Create node and store in map.
    Node * node = new Node;
    nodeMap[current] = node;
    
    // Set node pointers recursively.
    node->ptr1 = copy_recusive(current.ptr1);
    node->ptr2 = copy_recusive(current.ptr2);
    
    return node;
}
