import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class TreeTest {
    Tree tree;
    @BeforeEach
    public void newMyTree(){
        File fileInput = new File("src/MOCK_DATA.csv");
        tree = new Tree().createTree(fileInput);
    }
    @Test
    void insertNodePlusSize1() {
        int before = tree.getSize();
        tree.insertNode(new Student());
        assertEquals(tree.getSize(),before + 1);
    }

    @Test
    void deleteNodereturn() {
        Student student = Student.createStudentFromString("450,Egor,Busuioc,Mathematics,2001,2021,566521448");
        int before = tree.getSize();
        tree.deleteNode(student);
        int after = tree.getSize();
        assertEquals(before,after + 1);
    }
}s