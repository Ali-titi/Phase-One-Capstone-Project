package Services;

import Models.Course;
import Models.Student;
import Exception.CourseFullException;
import Exception.StudentAlreadyEnrolledException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public record UniversityManager(List<Student> students, List<Course> courses) {

    // Register student
    public void registerStudent(Student student) {
        students.add(student);
    }

    // Create course
    public void createCourse(Course course) {
        courses.add(course);
    }

    // Find student
    public Optional<Student> findStudentById(String id) {
        return students.stream()
                .filter(s -> s.getId().equals(id))
                .findFirst();
    }

    // Find course
    public Optional<Course> findCourseByCode(String code) {
        return courses.stream()
                .filter(c -> c.getCourseCode().equals(code))
                .findFirst();
    }

    // Enroll student
    public void enrollStudentInCourse(String studentId, String courseCode)
            throws CourseFullException, StudentAlreadyEnrolledException {

        Student student = findStudentById(studentId)
                .orElseThrow(() ->
                        new IllegalArgumentException("Student not found"));

        Course course = findCourseByCode(courseCode)
                .orElseThrow(() ->
                        new IllegalArgumentException("Course not found"));

        course.addStudent(student);
    }

    // View student record
    public Student getStudentRecord(String studentId) {
        return findStudentById(studentId)
                .orElseThrow(() ->
                        new IllegalArgumentException("Student not found"));
    }

    // Dean's List
    public List<Student> generateDeansList() {
        return students.stream()
                .filter(s -> s.getGpa() > 3.5)
                .collect(Collectors.toList());
    }
}