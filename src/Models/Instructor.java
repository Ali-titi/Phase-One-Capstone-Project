package Models;

public class Instructor extends Person {

    private final String department;

    public Instructor(String name, String id,
                      String email, String department) {
        super(name, id, email);
        this.department = department;
    }

    @Override
    public double calculateTuition() {
        return 0;
    }

    public String getDepartment() {
        return department;
    }
}