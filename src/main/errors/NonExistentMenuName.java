package main.errors;

public class NonExistentMenuName extends Exception {
    public NonExistentMenuName(String message) {
        super(message);
    }
    public String toString() {
        return getMessage();
    }
}
