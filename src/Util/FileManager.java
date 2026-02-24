package Util;

import Models.Course;
import Models.Student;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileManager {

    private static final String STUDENT_FILE = "src/Texts/students.txt";
    private static final String COURSE_FILE = "src/Texts/courses.txt";

    // Save Students
    public void saveStudents(List<Student> students) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(STUDENT_FILE))) {

            for (Student student : students) {
                writer.write(student.getId() + "," +
                        student.getName() + "," +
                        student.getEmail() + "," +
                        student.getGpa());
                writer.newLine();
            }

        } catch (IOException e) {
            System.out.println("Error saving students: " + e.getMessage());
        }
    }

    // Load Students
    public List<Student> loadStudents() {
        List<Student> students = new ArrayList<>();

        File file = new File(STUDENT_FILE);
        if (!file.exists()) {
            return students;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(STUDENT_FILE))) {

            String line;
            while ((line = reader.readLine()) != null) {

                String[] parts = line.split(",");

                String id = parts[0];
                String name = parts[1];
                String email = parts[2];
                double gpa = Double.parseDouble(parts[3]);

                students.add(new Student(id, name, email, gpa));
            }

        } catch (IOException e) {
            System.out.println("Error loading students: " + e.getMessage());
        }

        return students;
    }

    // Save Courses
    public void saveCourses(List<Course> courses) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(COURSE_FILE))) {

            for (Course course : courses) {

                StringBuilder enrolledIds = new StringBuilder();

                List<Student> roster = course.getRoster();

                for (int i = 0; i < roster.size(); i++) {
                    enrolledIds.append(roster.get(i).getId());

                    if (i < roster.size() - 1) {
                        enrolledIds.append("|");
                    }
                }

                writer.write(course.getCourseCode() + "," +
                        course.getCourseName() + "," +
                        course.getCredits() + "," +
                        course.getCapacity() + "," +
                        enrolledIds.toString());

                writer.newLine();
            }

        } catch (IOException e) {
            System.out.println("Error saving courses: " + e.getMessage());
        }
    }

    // Load Courses
    public List<Course> loadCourses(List<Student> students) {

        List<Course> courses = new ArrayList<>();

        File file = new File(COURSE_FILE);
        if (!file.exists()) {
            return courses;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(COURSE_FILE))) {

            String line;
            while ((line = reader.readLine()) != null) {

                String[] parts = line.split(",");

                String code = parts[0];
                String name = parts[1];
                int credits = Integer.parseInt(parts[2]);
                int capacity = Integer.parseInt(parts[3]);

                Course course = new Course(code, name, credits, capacity);

                // Add enrolled students
                if (parts.length > 4 && !parts[4].isEmpty()) {

                    String[] ids = parts[4].split("\\|");

                    for (String id : ids) {

                        Student student = findStudentById(students, id);

                        if (student != null) {
                            course.getRoster().add(student);
                            student.enrollCourse(course); // keep relation consistent
                        }
                    }
                }

                courses.add(course);
            }

        } catch (IOException e) {
            System.out.println("Error loading courses: " + e.getMessage());
        }

        return courses;
    }

    // Find student by ID
    private Student findStudentById(List<Student> students, String id) {
        for (Student s : students) {
            if (s.getId().equals(id)) {
                return s;
            }
        }
        return null;
    }
}