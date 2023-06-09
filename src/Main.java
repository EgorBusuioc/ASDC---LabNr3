import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * <h2>Class Main</h2>
 * The Main program implements an application that
 * simply displays data from a "MOCK_DATA.csv" file to the standard output.
 *
 * @see Main#linearSearch
 * @see Main#addStudent
 * @see Node
 * @see Tree
 * @see Student
 * @version 1.0
 * @author EgorBusuioc
 */
public class Main {
    public static void main(String[] args) {

        File file = new File(".\\src\\MOCK_DATA.csv");
        ArrayList<Student> arrayList = Student.input(file);

        Student.deleteStudentByIDNP(new File(".\\src\\MOCK_DATA.csv"), 489519012);
        System.out.printf("Обновленный файл\n\n\n\n\n");
        arrayList = Student.input(file);
        for (Student s : arrayList) {
            System.out.println(s);
        }

        System.out.println("Student was found with IDNP = " + linearSearch(arrayList, 489519012));
        addStudent(arrayList, 450, "Egor", "Busuioc","Mathematics", 2001, 2021, 566521448);
        System.out.println("Student was found with IDNP = " + linearSearch(arrayList, 566521448));
        
        Tree tree = new Tree();
        for(Student student: arrayList){
            tree.insertNode(student);
        }


    }

    /**
     * <h2>Linear Search</h2>
     * <p></p>
     * This method is needed for linear Student search.
     * @param listOfStudents ArrayList of objects of the Student class.
     * @param idnpToBeFound IDNP by which the method searches for student.
     * @return Position of student.
     */
    public static int linearSearch(ArrayList<Student> listOfStudents, long idnpToBeFound){
        int index = 0;
        for (Student student : listOfStudents) {
            if (student.getIDNP() == idnpToBeFound) {
                return index;
            } else index++;
        }
        return -1; // return -1 if the student with given IDNP is not found
    }

    /**
     * <h2>Add Student</h2>
     * <p></p>
     * This method adds an object of the Student class to the desired position, shifting all the others
     * @param arrayList ArrayList of objects of the Student class.
     * @param position Item addition position
     * @param firstName First Name
     * @param lastName Last Name
     * @param faculty Faculty
     * @param birthDate Birth Date
     * @param admissionYear Admission Year
     * @param IDNP IDNP
     */
    public static void addStudent(ArrayList<Student> arrayList, int position, String firstName, String lastName, String faculty, int birthDate, int admissionYear, int IDNP){
        Student newStudent = new Student(firstName, lastName, faculty, birthDate, admissionYear, IDNP);
        arrayList.add(position, newStudent);
        try(FileWriter writer = new FileWriter(".\\src\\MOCK_DATA.csv")) {
            for (int i = 0; i < arrayList.size(); i++) {
                Student student = arrayList.get(i);
                writer.write(student.addToString());
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
