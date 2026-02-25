import Models.*;
import Services.UniversityManager;
import Util.FileManager;
import Exception.*;

import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        FileManager fileManager = new FileManager();

        List<Student> students = fileManager.loadStudents();
        List<Course> courses = fileManager.loadCourses(students);

        UniversityManager manager =
                new UniversityManager(students, courses);

        boolean running = true;

        while (running) {

            System.out.println("\n===== University Management System =====");
            System.out.println("1. Register Student");
            System.out.println("2. Create Course");
            System.out.println("3. Enroll Student");
            System.out.println("4. View Student Record");
            System.out.println("5. Generate Dean's List");
            System.out.println("6. Save & Exit");
            System.out.print("Choose option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1:
                    System.out.print("Enter Name: ");
                    String name = scanner.nextLine();

                    System.out.print("Enter ID: ");
                    String id = scanner.nextLine();

                    System.out.print("Enter Email: ");
                    String email = scanner.nextLine();

                    System.out.print("Enter GPA: ");
                    double gpa = scanner.nextDouble();
                    scanner.nextLine();

                    System.out.print("Department: ");
                    String dept = scanner.nextLine();

                    System.out.print("Type (1=Undergrad, 2=Graduate): ");
                    int type = scanner.nextInt();
                    scanner.nextLine();

                    Student student;

                    if (type == 1) {
                        student = new UndergraduateStudent(
                                name, id, email, gpa, dept);
                    } else {
                        student = new GraduateStudent(
                                name, id, email, gpa, dept);
                    }

                    manager.registerStudent(student);
                    System.out.println("Student registered.");
                    break;

                case 2:
                    System.out.print("Course Code: ");
                    String code = scanner.nextLine();

                    System.out.print("Course Name: ");
                    String courseName = scanner.nextLine();

                    System.out.print("Credits: ");
                    int credits = scanner.nextInt();

                    System.out.print("Capacity: ");
                    int capacity = scanner.nextInt();
                    scanner.nextLine();

                    Course course = new Course(
                            code, courseName, credits, capacity);

                    manager.createCourse(course);
                    System.out.println("Course created.");
                    break;

                case 3:
                    System.out.print("Student ID: ");
                    String sid = scanner.nextLine();

                    System.out.print("Course Code: ");
                    String courseCode = scanner.nextLine();

                    try {
                        manager.enrollStudentInCourse(sid, courseCode);
                        System.out.println("Enrollment successful.");
                    } catch (CourseFullException |
                             StudentAlreadyEnrolledException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 4:
                    System.out.print("Student ID: ");
                    String recordId = scanner.nextLine();

                    try {
                        Student s = manager.getStudentRecord(recordId);
                        System.out.println(s);
                        System.out.println("Tuition: " +
                                s.calculateTuition());
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 5:
                    List<Student> deansList =
                            manager.generateDeansList();

                    deansList.forEach(System.out::println);
                    break;

                case 6:
                    fileManager.saveStudents(manager.students());
                    fileManager.saveCourses(manager.courses());
                    System.out.println("Data saved.");
                    running = false;
                    break;

                default:
                    System.out.println("Invalid option.");
            }
        }

        scanner.close();
    }
}