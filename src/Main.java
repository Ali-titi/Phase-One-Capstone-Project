import Models.Student;
import Models.Course;
import Util.FileManager;

import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        // Create scanner for user input
        Scanner scanner = new Scanner(System.in);

        // Create file manager
        FileManager fileManager = new FileManager();

        // Load students and courses from files
        List<Student> students = fileManager.loadStudents();
        List<Course> courses = fileManager.loadCourses(students);

        boolean running = true;

        // Main menu loop
        while (running) {

            System.out.println("\n===== University Management System =====");
            System.out.println("1. Add Student");
            System.out.println("2. Add Course");
            System.out.println("3. Enroll Student in Course");
            System.out.println("4. View Students");
            System.out.println("5. View Courses");
            System.out.println("6. Save & Exit");
            System.out.print("Choose option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // clear buffer

            switch (choice) {

                // Add new student
                case 1:
                    System.out.print("Enter ID: ");
                    String id = scanner.nextLine();

                    System.out.print("Enter Name: ");
                    String name = scanner.nextLine();

                    System.out.print("Enter Email: ");
                    String email = scanner.nextLine();

                    System.out.print("Enter GPA: ");
                    double gpa = scanner.nextDouble();
                    scanner.nextLine();

                    Student student = new Student(id, name, email, gpa);
                    students.add(student);

                    System.out.println("Student added successfully.");
                    break;

                // Add new course
                case 2:
                    System.out.print("Enter Course Code: ");
                    String code = scanner.nextLine();

                    System.out.print("Enter Course Name: ");
                    String courseName = scanner.nextLine();

                    System.out.print("Enter Credits: ");
                    int credits = scanner.nextInt();

                    System.out.print("Enter Capacity: ");
                    int capacity = scanner.nextInt();
                    scanner.nextLine();

                    Course course = new Course(code, courseName, credits, capacity);
                    courses.add(course);

                    System.out.println("Course added successfully.");
                    break;

                // Enroll student in course
                case 3:
                    System.out.print("Enter Student ID: ");
                    String studentId = scanner.nextLine();

                    System.out.print("Enter Course Code: ");
                    String courseCode = scanner.nextLine();

                    Student foundStudent = findStudentById(students, studentId);
                    Course foundCourse = findCourseByCode(courses, courseCode);

                    if (foundStudent != null && foundCourse != null) {

                        foundStudent.enrollCourse(foundCourse);
                        foundCourse.getRoster().add(foundStudent);

                        System.out.println("Student enrolled successfully.");
                    } else {
                        System.out.println("Student or Course not found.");
                    }

                    break;

                // View all students
                case 4:
                    for (Student s : students) {
                        System.out.println(s);
                    }
                    break;

                // View all courses
                case 5:
                    for (Course c : courses) {
                        System.out.println(c);
                    }
                    break;

                // Save and exit
                case 6:
                    fileManager.saveStudents(students);
                    fileManager.saveCourses(courses);

                    System.out.println("Data saved. Exiting system.");
                    running = false;
                    break;

                default:
                    System.out.println("Invalid option.");
            }
        }

        scanner.close();
    }

    // Find student by ID
    private static Student findStudentById(List<Student> students, String id) {
        for (Student s : students) {
            if (s.getId().equals(id)) {
                return s;
            }
        }
        return null;
    }

    // Find course by code
    private static Course findCourseByCode(List<Course> courses, String code) {
        for (Course c : courses) {
            if (c.getCourseCode().equals(code)) {
                return c;
            }
        }
        return null;
    }
}