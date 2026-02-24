package Services;

import Models.Course;
import Models.Student;
import Exception.CourseFullException;
import Exception.StudentAlreadyEnrolledException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class UniversityManager {

    private List<Student> students;
    private List<Course> courses;

    // Default constructor
    public UniversityManager() {
        this.students = new ArrayList<>();
        this.courses = new ArrayList<>();
    }

    // Constructor used when loading from file
    public UniversityManager(List<Student> students, List<Course> courses) {
        this.students = students;
        this.courses = courses;
    }

    // Register Student
    public void registerStudent(Student student) {
        students.add(student);
    }

    // Create Course
    public void createCourse(Course course) {
        courses.add(course);
    }

    // Find Student by ID
    public Optional<Student> findStudentById(String id) {
        return students.stream()
                .filter(s -> s.getId().equals(id))
                .findFirst();
    }

    // Find Course by Code
    public Optional<Course> findCourseByCode(String code) {
        return courses.stream()
                .filter(c -> c.getCourseCode().equals(code))
                .findFirst();
    }

    // Enroll Student in Course
    public void enrollStudentInCourse(String studentId, String courseCode)
            throws CourseFullException, StudentAlreadyEnrolledException {

        Student student = findStudentById(studentId)
                .orElseThrow(() -> new IllegalArgumentException("Student not found"));

        Course course = findCourseByCode(courseCode)
                .orElseThrow(() -> new IllegalArgumentException("Course not found"));

        if (course.getRoster().contains(student)) {
            throw new StudentAlreadyEnrolledException("Student already enrolled.");
        }

        if (course.getRoster().size() >= course.getCapacity()) {
            throw new CourseFullException("Course is full.");
        }

        course.getRoster().add(student);
        student.enrollCourse(course);
    }

    // View Student Record
    public Student getStudentRecord(String studentId) {
        return findStudentById(studentId)
                .orElseThrow(() -> new IllegalArgumentException("Student not found"));
    }

    // Generate Dean's List
    public List<Student> generateDeansList() {
        return students.stream()
                .filter(s -> s.getGpa() > 3.5)
                .collect(Collectors.toList());
    }

    // Calculate Average GPA
    public double calculateAverageGpa() {
        return students.stream()
                .mapToDouble(Student::getGpa)
                .average()
                .orElse(0.0);
    }

    // Get Students
    public List<Student> getStudents() {
        return students;
    }

    // Get Courses
    public List<Course> getCourses() {
        return courses;
    }
}