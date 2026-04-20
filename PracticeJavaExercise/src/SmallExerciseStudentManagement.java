import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

class Student implements Serializable {
    int id;
    String name;

    public Student(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public String toString() {
        return "ID: " + id + ", Name: " + name;
    }
}


public class SmallExerciseStudentManagement {

    static ArrayList<Student> students = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);
    static final String FILE_NAME = "students.dat";

    public static void main(String[] args) {
        loadFromFile();

        while (true) {
            System.out.println("\n===== Student Management System =====");
            System.out.println("1. Add Student");
            System.out.println("2. View Students");
            System.out.println("3. Search Student");
            System.out.println("4. Update Student");
            System.out.println("5. Delete Student");
            System.out.println("6. Save & Exit");
            System.out.print("Choose option: ");

            int choice = sc.nextInt();

            switch (choice) {
                case 1: addStudent(); break;
                case 2: viewStudents(); break;
                case 3: searchStudent(); break;
                case 4: updateStudent(); break;
                case 5: deleteStudent(); break;
                case 6: saveToFile(); System.out.println("Saved. Bye!"); return;
                default: System.out.println("Invalid choice!");
            }
        }
    }

    public static void addStudent() {
        System.out.print("Enter ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter Name: ");
        String name = sc.nextLine();

        students.add(new Student(id, name));
        System.out.println("Student added!");
    }

    public static void viewStudents() {
        if (students.isEmpty()) {
            System.out.println("No students found.");
            return;
        }
        for (Student s : students) {
            System.out.println(s);
        }
    }

    public static void searchStudent() {
        sc.nextLine();
        System.out.print("Enter name to search: ");
        String keyword = sc.nextLine().toLowerCase();

        boolean found = false;
        for (Student s : students) {
            if (s.name.toLowerCase().contains(keyword)) {
                System.out.println(s);
                found = true;
            }
        }
        if (!found) System.out.println("No match found.");
    }

    public static void updateStudent() {
        System.out.print("Enter ID to update: ");
        int id = sc.nextInt();
        sc.nextLine();

        for (Student s : students) {
            if (s.id == id) {
                System.out.print("Enter new name: ");
                s.name = sc.nextLine();
                System.out.println("Updated!");
                return;
            }
        }
        System.out.println("Student not found.");
    }

    public static void deleteStudent() {
        System.out.print("Enter ID to delete: ");
        int id = sc.nextInt();

        students.removeIf(s -> s.id == id);
        System.out.println("Deleted if existed.");
    }

    public static void saveToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(students);
        } catch (IOException e) {
            System.out.println("Error saving file.");
        }
    }

    public static void loadFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            students = (ArrayList<Student>) ois.readObject();
        } catch (Exception e) {
            System.out.println("No previous data found.");
        }
    }
}

