package Models;

public abstract class Person {

    private final String name;
    private final String id;
    private final String email;

    public Person(String name, String id, String email) {
        this.name = name;
        this.id = id;
        this.email = email;
    }

    // Abstract behavior (forces subclasses to implement)
    public abstract double calculateTuition();

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "Name: " + name +
                ", ID: " + id +
                ", Email: " + email;
    }
}