package project4;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.Serializable;

class Student implements Serializable {
    private static final long serialVersionUID = 1L;


    private String name;
    private int rollNumber;
    private String grade;

    // Constructor
    public Student(String name, int rollNumber, String grade) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.grade = grade;
    }

    // Getters and setters (you can generate them automatically in an IDE)
    public String getName() {
        return name;
    }

    public int getRollNumber() {
        return rollNumber;
    }

    public String getGrade() {
        return grade;
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Roll Number: " + rollNumber + ", Grade: " + grade;
    }
}

class StudentManagementSystem {
    private ArrayList<Student> students;
    private static final String DATA_FILE = "student_data.txt";

    public StudentManagementSystem() {
        students = new ArrayList<>();
        // Load existing data from the file
        readDataFromFile();
    }

    public void addStudent(Student student) {
        students.add(student);
        saveDataToFile();
    }

    public void removeStudent(int rollNumber) {
        students.removeIf(student -> student.getRollNumber() == rollNumber);
        saveDataToFile();
    }

    public Student searchStudent(int rollNumber) {
        for (Student student : students) {
            if (student.getRollNumber() == rollNumber) {
                return student;
            }
        }
        return null;
    }

    public void displayAllStudents() {
        for (Student student : students) {
            System.out.println(student);
        }
    }

    

    @SuppressWarnings("unchecked")
    private void readDataFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(DATA_FILE))) {
            Object obj = ois.readObject();
            if (obj instanceof ArrayList<?>) {
                students = (ArrayList<Student>) obj;
            }
        } catch (IOException | ClassNotFoundException e) {
            // Handle exceptions, or ignore if file doesn't exist yet
        }
    }


        private void saveDataToFile() {
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(DATA_FILE))) {
                oos.writeObject(students);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

public class StudentManagementSystemApp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try (Scanner scanner = new Scanner(System.in)) { 
        StudentManagementSystem sms = new StudentManagementSystem();

        while (true) {
            System.out.println("\nStudent Management System Menu:");
            System.out.println("1. Add Student");
            System.out.println("2. Remove Student");
            System.out.println("3. Search Student");
            System.out.println("4. Display All Students");
            System.out.println("5. Exit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    System.out.print("Enter student name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter roll number: ");
                    int rollNumber = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline character
                    System.out.print("Enter grade: ");
                    String grade = scanner.nextLine();

                    // Input validation
                    if (name.isEmpty() || grade.isEmpty()) {
                        System.out.println("Name and grade cannot be empty. Try again.");
                        continue; // Restart the loop
                    }

                    Student newStudent = new Student(name, rollNumber, grade);
                    sms.addStudent(newStudent);
                    break;

                case 2:
                    System.out.print("Enter the roll number to remove: ");
                    int removeRollNumber = scanner.nextInt();
                    sms.removeStudent(removeRollNumber);
                    break;

                case 3:
                    System.out.print("Enter the roll number to search: ");
                    int searchRollNumber = scanner.nextInt();
                    Student foundStudent = sms.searchStudent(searchRollNumber);
                    if (foundStudent != null) {
                        System.out.println("Student found: " + foundStudent);
                    } else {
                        System.out.println("Student not found.");
                    }
                    break;

                case 4:
                    System.out.println("All Students:");
                    sms.displayAllStudents();
                    break;

                case 5:
                    System.out.println("Exiting the application. Goodbye!");
                    System.exit(0);

                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 5.");
            }
        }
    }
}

}


