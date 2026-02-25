package Models;

public class GraduateStudent extends Student {

    private static final double COST_PER_CREDIT = 300;
    private static final double RESEARCH_FEE = 500;

    public GraduateStudent(String name, String id,
                           String email, double gpa,
                           String department) {
        super(name, id, email, gpa, department);
    }

    @Override
    public double calculateTuition() {

        int totalCredits = 0;

        for (Course course : getEnrolledCourses().keySet()) {
            totalCredits += course.getCredits();
        }

        return (totalCredits * COST_PER_CREDIT) + RESEARCH_FEE;
    }
}