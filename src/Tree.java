import java.util.Stack;

class Tree {
    private Node rootNode; // корневой узел

    public Tree() { // Пустое дерево
        rootNode = null;
    }

    public Node findNodeByValue(Student value) { // поиск узла по значению
        Node currentNode = rootNode; // начинаем поиск с корневого узла
        while (!currentNode.getValue().equals(value)) { // поиск покуда не будет найден элемент или не будут перебраны все
            if (value.getIDNP() < currentNode.getValue().getIDNP()) { // движение влево?
                currentNode = currentNode.getLeftChild();
            } else { //движение вправо
                currentNode = currentNode.getRightChild();
            }
            if (currentNode == null) { // если потомка нет,
                return null; // возвращаем null
            }
        }
        return currentNode; // возвращаем найденный элемент
    }
    public void insertNode(Student value) { // метод вставки нового элемента
        Node newNode = new Node(); // создание нового узла
        newNode.setValue(value); // вставка данных
        if (rootNode == null) { // если корневой узел не существует
            rootNode = newNode;// то новый элемент и есть корневой узел
        }
        else { // корневой узел занят
            Node currentNode = rootNode; // начинаем с корневого узла
            Node parentNode;
            while (true) // мы имеем внутренний выход из цикла
            {
                parentNode = currentNode;
                if(value.equals(currentNode.getValue())) {   // если такой элемент в дереве уже есть, не сохраняем его
                    return;    // просто выходим из метода
                }
                else  if (value.compareTo(currentNode.getValue()) < 0) {   // движение влево?
                    currentNode = currentNode.getLeftChild();
                    if (currentNode == null){ // если был достигнут конец цепочки,
                        parentNode.setLeftChild(newNode); //  то вставить слева и выйти из метода
                        return;
                    }
                }
                else { // Или направо?
                    currentNode = currentNode.getRightChild();
                    if (currentNode == null) { // если был достигнут конец цепочки,
                        parentNode.setRightChild(newNode);  //то вставить справа
                        return; // и выйти
                    }
                }
            }
        }
    }


    public boolean deleteNode(Student value) {
        Node currentNode = rootNode;
        Node parentNode = rootNode;
        boolean isLeftChild = true;

        while (currentNode.getValue().compareTo(value) != 0) { // begin search for node to delete
            parentNode = currentNode;
            if (value.compareTo(currentNode.getValue()) < 0) { // determine if to go left or right
                isLeftChild = true;
                currentNode = currentNode.getLeftChild();
            } else {
                isLeftChild = false;
                currentNode = currentNode.getRightChild();
            }
            if (currentNode == null) {
                return false; // node not found
            }
        }

        if (currentNode.getLeftChild() == null && currentNode.getRightChild() == null) { // node has no children
            if (currentNode == rootNode) { // node is root
                rootNode = null;
            } else if (isLeftChild) {
                parentNode.setLeftChild(null); // node is left child of parent
            } else {
                parentNode.setRightChild(null); // node is right child of parent
            }
        } else if (currentNode.getRightChild() == null) { // node has only left child
            if (currentNode == rootNode) { // node is root
                rootNode = currentNode.getLeftChild();
            } else if (isLeftChild) {
                parentNode.setLeftChild(currentNode.getLeftChild()); // node is left child of parent
            } else {
                parentNode.setRightChild(currentNode.getLeftChild()); // node is right child of parent
            }
        } else if (currentNode.getLeftChild() == null) { // node has only right child
            if (currentNode == rootNode) { // node is root
                rootNode = currentNode.getRightChild();
            } else if (isLeftChild) {
                parentNode.setLeftChild(currentNode.getRightChild()); // node is left child of parent
            } else {
                parentNode.setRightChild(currentNode.getRightChild()); // node is right child of parent
            }
        } else { // node has two children
            Node heir = receiveHeir(currentNode); // find heir to replace node
            if (currentNode == rootNode) { // node is root
                rootNode = heir;
            } else if (isLeftChild) {
                parentNode.setLeftChild(heir); // node is left child of parent
            } else {
                parentNode.setRightChild(heir); // node is right child of parent
            }
        }

        return true; // node successfully deleted
    }

    // метод возвращает узел со следующим значением после передаваемого аргументом.
    // для этого он сначала переходим к правому потомку, а затем
    // отслеживаем цепочку левых потомков этого узла.
    private Node receiveHeir(Node node) {
        Node parentNode = node;
        Node heirNode = node;
        Node currentNode = node.getRightChild(); // Переход к правому потомку
        while (currentNode != null) // Пока остаются левые потомки
        {
            parentNode = heirNode;// потомка задаём как текущий узел
            heirNode = currentNode;
            currentNode = currentNode.getLeftChild(); // переход к левому потомку
        }
        // Если преемник не является
        if (heirNode != node.getRightChild()) // правым потомком,
        { // создать связи между узлами
            parentNode.setLeftChild(heirNode.getRightChild());
            heirNode.setRightChild(node.getRightChild());
        }
        return heirNode;// возвращаем приемника
    }

    public void printTree() { // метод для вывода дерева в консоль
        Stack globalStack = new Stack(); // общий стек для значений дерева
        globalStack.push(rootNode);
        int gaps = 32; // начальное значение расстояния между элементами
        boolean isRowEmpty = false;
        String separator = "-----------------------------------------------------------------";
        System.out.println(separator);// черта для указания начала нового дерева
        while (isRowEmpty == false) {
            Stack localStack = new Stack(); // локальный стек для задания потомков элемента
            isRowEmpty = true;

            for (int j = 0; j < gaps; j++)
                System.out.print(' ');
            while (globalStack.isEmpty() == false) { // покуда в общем стеке есть элементы
                Node temp = (Node) globalStack.pop(); // берем следующий, при этом удаляя его из стека
                if (temp != null) {
                    System.out.print(temp.getValue()); // выводим его значение в консоли
                    localStack.push(temp.getLeftChild()); // соохраняем в локальный стек, наследники текущего элемента
                    localStack.push(temp.getRightChild());
                    if (temp.getLeftChild() != null ||
                            temp.getRightChild() != null)
                        isRowEmpty = false;
                }
                else {
                    System.out.print("__");// - если элемент пустой
                    localStack.push(null);
                    localStack.push(null);
                }
                for (int j = 0; j < gaps * 2 - 2; j++)
                    System.out.print(' ');
            }
            System.out.println();
            gaps /= 2;// при переходе на следующий уровень расстояние между элементами каждый раз уменьшается
            while (localStack.isEmpty() == false)
                globalStack.push(localStack.pop()); // перемещаем все элементы из локального стека в глобальный
        }
        System.out.println(separator);// подводим черту
    }
    public void preOrderTraversal(Node node) {
        if (node != null) {
            System.out.print(node.getValue() + " ");
            preOrderTraversal(node.getLeftChild());
            preOrderTraversal(node.getRightChild());
        }
    }
    public void traversePostOrder(Node node) {
        if (node != null) {
            traversePostOrder(node.getLeftChild());
            traversePostOrder(node.getRightChild());
            System.out.print(node.getValue() + " ");
        }
    }
    public void traverseInOrder(Node node) {
        if (node != null) {
            traverseInOrder(node.getLeftChild());
            System.out.print(node.getValue() + " ");
            traverseInOrder(node.getRightChild());
        }
    }
}