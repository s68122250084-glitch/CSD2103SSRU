import java.util.Stack;

public class StackCaseStudy {

    public static void main(String[] args) {

        Stack<String> stack = new Stack<>();

        stack.push("Type Data");
        stack.push("Type Structure");
        stack.push("Delete Structure");
        stack.push("Type Algorithm");
        stack.push("Type Java");

        System.out.println("Initial Stack");
        System.out.println(stack);

        System.out.println("\nUndo 2 Commands");

        for (int i = 0; i < 2; i++) {
            if (!stack.isEmpty()) {
                System.out.println("Undo : " + stack.pop());
            } else {
                System.out.println("Stack is empty.");
            }
        }

        System.out.println("\nStack After Undo");
        System.out.println(stack);

        System.out.println("\nExplanation");
        System.out.println("Stack uses LIFO (Last In First Out).");
        System.out.println("The last command entered is undone first.");

        System.out.println("\nTime Complexity");
        System.out.println("push() = O(1)");
        System.out.println("pop() = O(1)");
        System.out.println("peek() = O(1)");
    }
}
