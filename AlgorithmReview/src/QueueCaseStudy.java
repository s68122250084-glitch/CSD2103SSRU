import java.util.LinkedList;
import java.util.Queue;

public class QueueCaseStudy {

    public static void main(String[] args) {

        Queue<String> queue = new LinkedList<>();

        queue.add("P001");
        queue.add("P002");
        queue.add("P003");
        queue.add("P004");
        queue.add("P005");

        System.out.println("Initial Queue");
        System.out.println(queue);

        System.out.println("\nPatients Served");

        for (int i = 0; i < 2; i++) {
            if (!queue.isEmpty()) {
                System.out.println(queue.remove());
            } else {
                System.out.println("Queue is empty.");
            }
        }

        queue.add("P006");
        queue.add("P007");

        System.out.println("\nNext Patient : " + queue.peek());
        System.out.println("Patients Waiting : " + queue.size());

        System.out.println("\nQueue Status");
        System.out.println(queue);

        System.out.println("\nExplanation");
        System.out.println("Queue uses FIFO (First In First Out).");
        System.out.println("The first patient who arrives is served first.");

        System.out.println("\nTime Complexity");
        System.out.println("add() = O(1)");
        System.out.println("remove() = O(1)");
        System.out.println("peek() = O(1)");
    }
}
