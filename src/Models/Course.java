package Models;

import Exception.CourseFullException;
import Exception.StudentAlreadyEnrolledException;

import java.util.ArrayList;
import java.util.List;

public class Course {

    private final String courseCode;
    private final String courseName;
    private final int credits;
    private final int capacity;
    private final List<Student> roster;

    public Course(String courseCode, String courseName,
                  int credits, int capacity) {
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.credits = credits;
        this.capacity = capacity;
        this.roster = new ArrayList<>();
    }

    public void addStudent(Student student)
            throws CourseFullException, StudentAlreadyEnrolledException {

        if (roster.contains(student)) {
            throw new StudentAlreadyEnrolledException(
                    "Student already enrolled."
            );
        }

        if (roster.size() >= capacity) {
            throw new CourseFullException(
                    "Course is full."
            );
        }

        roster.add(student);
        student.enrollCourse(this);
    }

    public String getCourseName() {
        return courseName;
    }

    public String getCourseCode() {
        return courseCode;
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

    @Override
    public String toString() {
        return courseCode + " - " + courseName +
                " | Credits: " + credits +
                " | Capacity: " + capacity +
                " | Enrolled: " + roster.size();
    }
}