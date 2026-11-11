
package task4;
public class CustomQueue {

    private String[] queue;
    private int front, rear, size, capacity;

    public CustomQueue(int capacity) {
        this.capacity = capacity;
        queue = new String[capacity];
        front = 0;
        rear = -1;
        size = 0;
    }

    // Add an order
    public void enqueue(String order) {
        if (size == capacity) {
            System.out.println("Queue is full!");
            return;
        }

        rear = (rear + 1) % capacity;
        queue[rear] = order;
        size++;

        System.out.println("Order added: " + order);
    }

    // Remove the first order
    public String dequeue() {
        if (size == 0) {
            System.out.println("Queue is empty!");
            return null;
        }

        String removed = queue[front];
        queue[front] = null;

        front = (front + 1) % capacity;
        size--;

        System.out.println("Order dispatched: " + removed);
        return removed;
    }

    // View the first order
    public String peek() {
        if (size == 0) {
            return "No pending orders";
        }

        return queue[front];
    }

    // Display all pending orders
    public void display() {
        if (size == 0) {
            System.out.println("No pending orders!");
            return;
        }

        System.out.println("\nPending Orders:");

        for (int i = 0; i < size; i++) {
            int index = (front + i) % capacity;
            System.out.println(queue[index]);
        }
    }

    public static void main(String[] args) {

        CustomQueue warehouse = new CustomQueue(5);

        System.out.println("===== WAREHOUSE ORDER QUEUE =====");

        warehouse.enqueue("Order 101");
        warehouse.enqueue("Order 102");
        warehouse.enqueue("Order 103");
        warehouse.enqueue("Order 104");

        warehouse.display();

        System.out.println("\n===== ORDER DISPATCH =====");

        warehouse.dequeue();
        warehouse.dequeue();

        System.out.println("Next Order: " + warehouse.peek());

        warehouse.display();
    }
}
