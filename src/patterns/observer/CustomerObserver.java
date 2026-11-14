package patterns.observer;
public class CustomerObserver implements OrderObserver {
    public void update(int orderId, OrderStatus status) {
        System.out.println("Customer: order #" + orderId + " is " + status);
    }
}
