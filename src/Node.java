class Node {
    Student student; // ключ узла
    private Node leftChild; // Левый узел потомок
    private Node rightChild; // Правый узел потомок


    public Student getValue() {
        return this.student;
    }

    public void setValue(final Student student) {
        this.student = student;
    }

    public Node getLeftChild() {
        return this.leftChild;
    }

    public void setLeftChild(final Node leftChild) {
        this.leftChild = leftChild;
    }

    public Node getRightChild() {
        return this.rightChild;
    }

    public void setRightChild(final Node rightChild) {
        this.rightChild = rightChild;
    }

    @Override
    public String toString() {
        return "Node{" +
                "student=" + student +
                ", leftChild=" + leftChild +
                ", rightChild=" + rightChild +
                '}';
    }
}