/**
 * <h3>Class Node</h3>
 * <p></p>
 * This class represents a node in a binary search tree. Each node stores a student object as its key,
 * and has a left and right child, which are also nodes in the tree.
 * @see Node#getValue()
 * @see Node#setValue(Student)
 * @see Node#getLeftChild()
 * @see Node#getRightChild()
 * @see Node#setLeftChild(Node)
 * @see Node#setRightChild(Node)
 */
class Node {
    Student student; // the key of the node
    private Node leftChild; // the left child of the node
    private Node rightChild;  // the right child of the node
    /**
     * <h3>Get Value</h3>
     * <p></p>
     * This method returns the student object stored in this node.
     * @return the student object stored in this node
     */

    public Student getValue() {
        return this.student;
    }
    /**
     * <h3>Set Value</h3>
     * <p></p>
     * This method sets the student object stored in this node to the specified value.
     * @param student the student object to be stored in this node
     */
    public void setValue(final Student student) {
        this.student = student;
    }
    /**
     * <h3>Get Left Child</h3>
     * <p></p>
     * This method returns the left child of this node.
     * @return the left child of this node
     */
    public Node getLeftChild() {
        return this.leftChild;
    }
    /**
     * <h3>Set Left Child</h3>
     * <p></p>
     * This method sets the left child of this node to the specified node.
     * @param leftChild the node to be set as the left child of this node
     */
    public void setLeftChild(final Node leftChild) {
        this.leftChild = leftChild;
    }
    /**
     * <h3>Get Right Child</h3>
     * This method returns the right child of this node.
     * @return the right child of this node
     */
    public Node getRightChild() {
        return this.rightChild;
    }
    /**
     * <h3>Set Right Child</h3>
     * <p></p>
     * This method sets the right child of this node to the specified node.
     * @param rightChild the node to be set as the right child of this node
     */
    public void setRightChild(final Node rightChild) {
        this.rightChild = rightChild;
    }
    /**
     * <h3>To String</h3>
     * <p></p>
     * This method returns a string representation of this node, including its student key,
     * left child, and right child.
     * @return a string representation of this node
     */
    @Override
    public String toString() {
        return "Node{" +
                "student=" + student +
                ", leftChild=" + leftChild +
                ", rightChild=" + rightChild +
                '}';
    }
}