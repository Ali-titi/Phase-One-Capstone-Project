package Models;

public class UndergraduateStudent extends Student {

    private static final double FLAT_RATE = 1500;

    public UndergraduateStudent(String name, String id,
                                String email, double gpa,
                                String department) {
        super(name, id, email, gpa, department);
    }

    @Override
    public double calculateTuition() {
        return FLAT_RATE;
    }
}