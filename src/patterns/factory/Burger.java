package patterns.factory;

public class Burger implements Meal {
    public String getName() {
        return "Burger";
    }

    public double getPrice() {
        return 2000;
    }
}
