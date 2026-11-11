
package task4;
public class CustomStack {

    private String[] stack;
    private int top;
    private int capacity;

    public CustomStack(int capacity) {
        this.capacity = capacity;
        stack = new String[capacity];
        top = -1;
    }

    public void push(String edit) {
        if (top == capacity - 1) {
            System.out.println("Stack is full!");
            return;
        }

        stack[++top] = edit;
        System.out.println("Edit added: " + edit);
    }

    public String pop() {
        if (top == -1) {
            System.out.println("Stack is empty!");
            return null;
        }

        String removed = stack[top];
        stack[top] = null;
        top--;

        System.out.println("Undo: " + removed);
        return removed;
    }

    public String peek() {
        if (top == -1) {
            return "No edits available";
        }

        return stack[top];
    }

    public void display() {
        System.out.println("\nCurrent Edits:");

        for (int i = top; i >= 0; i--) {
            System.out.println(stack[i]);
        }
    }

    public static void main(String[] args) {

        CustomStack history = new CustomStack(5);

        history.push("Change delivery address");
        history.push("Update quantity");
        history.push("Apply discount");

        history.display();

        history.pop();

        System.out.println("Latest Edit: " + history.peek());

        history.display();
    }
}
