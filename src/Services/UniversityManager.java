package Services;

import Models.Course;
import Models.Student;
import Exception.CourseFullException;
import Exception.StudentAlreadyEnrolledException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UniversityManager {

    private List<Student> students;
    private List<Course> courses;

    public UniversityManager() {
        this.students = new ArrayList<>();
        this.courses = new ArrayList<>();
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
    private Optional<Student> findStudentById(String id) {
        return students.stream()
                .filter(s -> s.getId().equals(id))
                .findFirst();
    }

    // Find Course by Code
    private Optional<Course> findCourseByCode(String code) {
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

        // Check duplicate enrollment
        if (course.getRoster().contains(student)) {
            throw new StudentAlreadyEnrolledException("Student already enrolled in this course.");
        }

        // Check capacity
        if (course.getRoster().size() >= course.getCapacity()) {
            throw new CourseFullException("Course is full.");
        }

        // Perform enrollment
        course.getRoster().add(student);
        student.enrollCourse(course);
    }

    // Calculate Average GPA
    public double calculateAverageGpa() {
        return students.stream()
                .mapToDouble(Student::getGpa)
                .average()
                .orElse(0.0);
    }

    // Find Top Performing Student
    public Student getTopStudent() {
        return students.stream()
                .max((s1, s2) -> Double.compare(s1.getGpa(), s2.getGpa()))
                .orElse(null);
    }

    public List<Student> getStudents() {
        return students;
    }

    public List<Course> getCourses() {
        return courses;
    }
}
