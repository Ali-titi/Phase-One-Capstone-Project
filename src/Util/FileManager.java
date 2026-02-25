package Util;

import Models.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileManager {

    // File paths
    private static final String STUDENT_FILE = "src/Texts/students.txt";
    private static final String COURSE_FILE = "src/Texts/courses.txt";

    // SAVE STUDENTS
    public void saveStudents(List<Student> students) {

        try (BufferedWriter writer =
                     new BufferedWriter(new FileWriter(STUDENT_FILE))) {

            for (Student student : students) {

                // Detect student type
                String type;
                if (student instanceof UndergraduateStudent) {
                    type = "UNDERGRAD";
                } else {
                    type = "GRAD";
                }

                // Save: type,name,id,email,gpa,department
                writer.write(type + "," +
                        student.getName() + "," +
                        student.getId() + "," +
                        student.getEmail() + "," +
                        student.getGpa() + "," +
                        student.getDepartment());

                writer.newLine();
            }

        } catch (IOException e) {
            System.out.println("Error saving students: "
                    + e.getMessage());
        }
    }
    // LOAD STUDENTS
    public List<Student> loadStudents() {

        List<Student> students = new ArrayList<>();

        File file = new File(STUDENT_FILE);

        // If file does not exist return empty list
        if (!file.exists()) {
            return students;
        }

        try (BufferedReader reader =
                     new BufferedReader(new FileReader(STUDENT_FILE))) {

            String line;

            while ((line = reader.readLine()) != null) {

                String[] parts = line.split(",");

                // Prevent crash if line format is wrong
                if (parts.length < 6) {
                    System.out.println("Invalid line skipped: " + line);
                    continue;
                }
                String type = parts[0];
                String name = parts[1];
                String id = parts[2];
                String email = parts[3];
                double gpa = Double.parseDouble(parts[4]);
                String department = parts[5];

                Student student;

                // Create correct object type
                if (type.equals("UNDERGRAD")) {
                    student = new UndergraduateStudent(
                            id, name, email, gpa, department);
                } else {
                    student = new GraduateStudent(
                            id, name, email, gpa, department);
                }

                students.add(student);
            }

        } catch (IOException e) {
            System.out.println("Error loading students: "
                    + e.getMessage());
        }

        return students;
    }
    // SAVE COURSES
    public void saveCourses(List<Course> courses) {

        try (BufferedWriter writer =
                     new BufferedWriter(new FileWriter(COURSE_FILE))) {

            for (Course course : courses) {

                StringBuilder enrolledIds = new StringBuilder();

                List<Student> roster = course.getRoster();

                // Save enrolled student IDs separated by |
                for (int i = 0; i < roster.size(); i++) {

                    enrolledIds.append(roster.get(i).getId());

                    if (i < roster.size() - 1) {
                        enrolledIds.append("|");
                    }
                }

                // Save: code,name,credits,capacity,studentIds
                writer.write(course.getCourseCode() + "," +
                        course.getCourseName() + "," +
                        course.getCredits() + "," +
                        course.getCapacity() + "," +
                        enrolledIds);

                writer.newLine();
            }

        } catch (IOException e) {
            System.out.println("Error saving courses: "
                    + e.getMessage());
        }
    }
    // LOAD COURSES
    public List<Course> loadCourses(List<Student> students) {

        List<Course> courses = new ArrayList<>();

        File file = new File(COURSE_FILE);

        if (!file.exists()) {
            return courses;
        }

        try (BufferedReader reader =
                     new BufferedReader(new FileReader(COURSE_FILE))) {

            String line;

            while ((line = reader.readLine()) != null) {

                String[] parts = line.split(",");

                // Prevent crash
                if (parts.length < 4) {
                    continue;
                }

                String code = parts[0];
                String name = parts[1];
                int credits = Integer.parseInt(parts[2]);
                int capacity = Integer.parseInt(parts[3]);

                Course course =
                        new Course(code, name, credits, capacity);

                // Load enrolled students
                if (parts.length > 4 && !parts[4].isEmpty()) {

                    String[] ids = parts[4].split("\\|");

                    for (String id : ids) {

                        Student student =
                                findStudentById(students, id);

                        if (student != null) {
                            try {
                                course.addStudent(student);
                            } catch (Exception ignored) {}
                        }
                    }
                }

                courses.add(course);
            }

        } catch (IOException e) {
            System.out.println("Error loading courses: "
                    + e.getMessage());
        }

        return courses;
    }
    // FIND STUDENT BY ID
    private Student findStudentById(List<Student> students,
                                    String id) {

        for (Student s : students) {
            if (s.getId().equals(id)) {
                return s;
            }
        }
        return null;
    }
}