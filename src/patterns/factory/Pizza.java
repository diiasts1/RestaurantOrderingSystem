package patterns.factory;

public class Pizza implements Meal {
    public String getName() {
        return "Pizza";
    }

    public double getPrice() {
        return 3000;
    }
}
