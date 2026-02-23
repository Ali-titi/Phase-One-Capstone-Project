package Models;

import java.util.HashMap;
import java.util.Map;

public class Student extends Person {

    private double gpa;
    private Map<Course, Double> enrolledCourses;

    public Student(String id, String name, String email, double gpa) {
        super(id, name, email);
        this.gpa = gpa;
        this.enrolledCourses = new HashMap<>();
    }

    public double getGpa() {
        return gpa;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

    public Map<Course, Double> getEnrolledCourses() {
        return enrolledCourses;
    }

    public void enrollCourse(Course course) {
        enrolledCourses.put(course, null);
    }

    public void assignGrade(Course course, double grade) {
        if (enrolledCourses.containsKey(course)) {
            enrolledCourses.put(course, grade);
        }
    }

    public double calculateTuition(){
        return 0;
    }

    @Override
    public String toString() {
        return super.toString() + ", GPA: " + gpa;
    }
}
