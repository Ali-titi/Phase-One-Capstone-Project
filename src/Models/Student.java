package Models;

import java.util.HashMap;
import java.util.Map;

public abstract class Student extends Person {

    private final double gpa;
    private final String department;
    private final Map<Course, Double> enrolledCourses;

    public Student(String name, String id, String email,
                   double gpa, String department) {
        super(name, id, email);
        this.gpa = gpa;
        this.department = department;
        this.enrolledCourses = new HashMap<>();
    }

    public double getGpa() {
        return gpa;
    }

    public String getDepartment() {
        return department;
    }

    public Map<Course, Double> getEnrolledCourses() {
        return enrolledCourses;
    }

    public void enrollCourse(Course course) {
        enrolledCourses.put(course, null);
    }

    @Override
    public String toString() {
        return super.toString() +
                ", GPA: " + gpa +
                ", Department: " + department;
    }
}