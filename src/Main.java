//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import Models.Course;
import Models.UndergraduateStudent;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 1. Get Student info from user
        System.out.println("Enter student ID:");
        String id = sc.nextLine();

        System.out.println("Enter student name:");
        String name = sc.nextLine();

        System.out.println("Enter student email:");
        String email = sc.nextLine();

        System.out.println("Enter GPA:");
        double gpa = sc.nextDouble();
        sc.nextLine();

        UndergraduateStudent student = new UndergraduateStudent(id, name, email, gpa);

        // 2. Get Course info from user
        System.out.println("Enter course code:");
        String courseCode = sc.nextLine();

        System.out.println("Enter course name:");
        String courseName = sc.nextLine();

        System.out.println("Enter credits:");
        int credits = sc.nextInt();

        System.out.println("Enter capacity:");
        int capacity = sc.nextInt();

        Course course = new Course(courseCode, courseName, credits, capacity);

        // 3. Enroll student
        try {
            course.addStudent(student);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        // 4. Print results
        System.out.println("Student: " + student);
        System.out.println("Course: " + course);

        sc.close();
    }
}