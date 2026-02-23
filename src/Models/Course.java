package Models;

import java.util.ArrayList;
import java.util.List;

public class Course {

    private String courseCode;
    private String courseName;
    private int credits;
    private int capacity;
    private List<Student> roster;

    // Constructor
    public Course(String courseCode, String courseName, int credits, int capacity) {
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.credits = credits;
        this.capacity = capacity;
        this.roster = new ArrayList<>();
    }

    // Getters
    public String getCourseCode() {
        return courseCode;
    }

    public String getCourseName() {
        return courseName;
    }

    public int getCredits() {
        return credits;
    }

    public int getCapacity() {
        return capacity;
    }

    public List<Student> getRoster() {
        return roster;
    }

    // Enrollment method
    public void addStudent(Student student) throws Exception {
        if (roster.contains(student)) {
            throw new Exception("Student already enrolled in this course!");
        }
        if (roster.size() >= capacity) {
            throw new Exception("Course is full! Cannot enroll more students.");
        }
        roster.add(student);
        student.enrollCourse(this);
    }

    // to remove student
    public void removeStudent(Student student) {
        roster.remove(student);
        student.getEnrolledCourses().remove(this);
    }

    @Override
    public String toString() {
        return courseCode + " - " + courseName +
                " | Credits: " + credits +
                " | Capacity: " + capacity +
                " | Enrolled: " + roster.size();
    }
}



