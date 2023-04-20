import java.util.Stack;
/**
 * <h3>Class Tree</h3>
 * <p></p>
 * The Tree class represents a binary search tree data structure that stores Student objects.
 * Each node of the tree contains a reference to a Student object and pointers to its left and right child nodes.
 * The class provides methods for inserting, deleting and searching nodes in the tree.
 * @see Tree#findNodeByValue(Student)
 * @see Tree#insertNode(Student) 
 * @see Tree#deleteNode(Student)
 * @see Tree#receiveHeir(Node) 
 * @see Tree#printTree() 
 * @see Tree#preOrderTraversal(Node) 
 * @see Tree#traverseInOrder(Node) 
 * @see Tree#traversePostOrder(Node) 
 * @author EgorBusuioc
 * @version 1.0
 */
class Tree {
    private Node rootNode; // the root node of the tree

    public Tree() { // Empty Tree
        rootNode = null;
    }
    /**
     * <h3>Find Node By Value</h3>
     * <p></p>
     * Searches for a node in the tree with the specified Student value.
     *
     * @param value the Student object to search for
     * @return the node containing the value, or null if no such node exists in the tree
     */
    public Node findNodeByValue(Student value) {
        Node currentNode = rootNode;
        while (!currentNode.getValue().equals(value)) {
            if (value.getIDNP() < currentNode.getValue().getIDNP()) {
                currentNode = currentNode.getLeftChild();
            } else {
                currentNode = currentNode.getRightChild();
            }
            if (currentNode == null) {
                return null;
            }
        }
        return currentNode;
    }
    /**
     * <h3>Insert Node</h3>
     * <p></p>
     * Inserts a new node with the specified Student value into the tree.
     * If a node with the same value already exists in the tree, it is not inserted again.
     *
     * @param value the Student object to insert
     */
    public void insertNode(Student value) {
        Node newNode = new Node();
        newNode.setValue(value);
        if (rootNode == null) {
            rootNode = newNode;
        }
        else {
            Node currentNode = rootNode;
            Node parentNode;
            while (true)
            {
                parentNode = currentNode;
                if(value.equals(currentNode.getValue())) {
                    return;
                }
                else  if (value.compareTo(currentNode.getValue()) < 0) {
                    currentNode = currentNode.getLeftChild();
                    if (currentNode == null){
                        parentNode.setLeftChild(newNode);
                        return;
                    }
                }
                else {
                    currentNode = currentNode.getRightChild();
                    if (currentNode == null) {
                        parentNode.setRightChild(newNode);
                        return;
                    }
                }
            }
        }
    }

    /**
     * <h3>Delete Node</h3>
     * <p></p>
     * Deletes the node with the specified Student value from the tree.
     *
     * @param value the Student object to delete
     * @return true if the node was successfully deleted, false if no such node exists in the tree
     */
    public boolean deleteNode(Student value) {
        Node currentNode = rootNode;
        Node parentNode = rootNode;
        boolean isLeftChild = true;

        while (currentNode.getValue().compareTo(value) != 0) {
            parentNode = currentNode;
            if (value.compareTo(currentNode.getValue()) < 0) {
                isLeftChild = true;
                currentNode = currentNode.getLeftChild();
            } else {
                isLeftChild = false;
                currentNode = currentNode.getRightChild();
            }
            if (currentNode == null) {
                return false;
            }
        }

        if (currentNode.getLeftChild() == null && currentNode.getRightChild() == null) {
            if (currentNode == rootNode) { // node is root
                rootNode = null;
            } else if (isLeftChild) {
                parentNode.setLeftChild(null);
            } else {
                parentNode.setRightChild(null);
            }
        } else if (currentNode.getRightChild() == null) {
            if (currentNode == rootNode) {
                rootNode = currentNode.getLeftChild();
            } else if (isLeftChild) {
                parentNode.setLeftChild(currentNode.getLeftChild());
                parentNode.setRightChild(currentNode.getLeftChild());
            }
        } else if (currentNode.getLeftChild() == null) {
            if (currentNode == rootNode) {
                rootNode = currentNode.getRightChild();
            } else if (isLeftChild) {
                parentNode.setLeftChild(currentNode.getRightChild());
            } else {
                parentNode.setRightChild(currentNode.getRightChild());
            }
        } else {
            Node heir = receiveHeir(currentNode);
            if (currentNode == rootNode) {
                rootNode = heir;
            } else if (isLeftChild) {
                parentNode.setLeftChild(heir);
            } else {
                parentNode.setRightChild(heir);
            }
        }

        return true;
    }

    /**
     * <h3>Recieve Hair</h3>
     * <p></p>
     * Returns the node in the tree that has the next greater value than the specified Student object.
     * If no such node exists, returns null.
     *
     * @param node the Student object to find the successor of
     * @return the node with the next greater value, or null if no such node exists in the tree
     */
    private Node receiveHeir(Node node) {
        Node parentNode = node;
        Node heirNode = node;
        Node currentNode = node.getRightChild();
        while (currentNode != null)
        {
            parentNode = heirNode;
            heirNode = currentNode;
            currentNode = currentNode.getLeftChild();
        }
        if (heirNode != node.getRightChild())
        {
            parentNode.setLeftChild(heirNode.getRightChild());
            heirNode.setRightChild(node.getRightChild());
        }
        return heirNode;
    }

    /**
     * <h3>Print Tree</h3>
     * <p></p>
     * This method prints tree
     */
    public void printTree() {
        Stack globalStack = new Stack();
        globalStack.push(rootNode);
        int gaps = 32;
        boolean isRowEmpty = false;
        String separator = "-----";
        System.out.println(separator);
        while (isRowEmpty == false) {
            Stack localStack = new Stack();
            isRowEmpty = true;

            for (int j = 0; j < gaps; j++)
                System.out.print(' ');
            while (globalStack.isEmpty() == false) {
                Node temp = (Node) globalStack.pop();
                if (temp != null) {
                    System.out.print(temp.getValue());
                    localStack.push(temp.getLeftChild());
                    localStack.push(temp.getRightChild());
                    if (temp.getLeftChild() != null ||
                            temp.getRightChild() != null)
                        isRowEmpty = false;
                }
                else {
                    System.out.print("__");
                    localStack.push(null);
                    localStack.push(null);
                }
                for (int j = 0; j < gaps * 2 - 2; j++)
                    System.out.print(' ');
            }
            System.out.println();
            gaps /= 2;
            while (localStack.isEmpty() == false)
                globalStack.push(localStack.pop());
        }
        System.out.println(separator);
    }
    /**
     * <h3>Pre Order Traversal</h3>
     * <p></p>
     * Traverses the binary tree in pre-order, starting from the given node.
     * @param node The node to start the traversal from.
     */
    public void preOrderTraversal(Node node) {
        if (node != null) {
            System.out.print(node.getValue() + " ");
            preOrderTraversal(node.getLeftChild());
            preOrderTraversal(node.getRightChild());
        }
    }
    /**
     * <h3>Traverse Post Order</h3>
     * <p></p>
     * Traverses the binary tree in post-order, starting from the given node.
     * @param node The node to start the traversal from.
     */
    public void traversePostOrder(Node node) {
        if (node != null) {
            traversePostOrder(node.getLeftChild());
            traversePostOrder(node.getRightChild());
            System.out.print(node.getValue() + " ");
        }
    }
    /**
     * <h3>Traverse In Order</h3>
     * <p></p>
     * Traverses the binary tree in in-order, starting from the given node.
     * @param node The node to start the traversal from.
     */
    public void traverseInOrder(Node node) {
        if (node != null) {
            traverseInOrder(node.getLeftChild());
            System.out.print(node.getValue() + " ");
            traverseInOrder(node.getRightChild());
        }
    }
}