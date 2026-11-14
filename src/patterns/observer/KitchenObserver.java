package patterns.observer;
public class KitchenObserver implements OrderObserver {
    public void update(int orderId, OrderStatus status) {
        System.out.println("Kitchen: order #" + orderId + " is " + status);
    }
}
