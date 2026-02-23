package Models;

public class UndergraduateStudent extends Student{
    private static final double FLAT_RATE = 1500;

    public UndergraduateStudent(String id, String name, String email, double gpa) {
        super(id, name, email, gpa);
    }

    @Override
    public double calculateTuition() {
        return FLAT_RATE;
    }
}
