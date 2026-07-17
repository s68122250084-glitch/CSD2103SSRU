import java.util.Scanner;

public class PassFail {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter midterm score: ");
        int mid = sc.nextInt();

        System.out.print("Enter final score: ");
        int fin = sc.nextInt();

        int total = mid + fin;

        System.out.println("Total score = " + total);

        if (total >= 50) {
            System.out.println("Pass");
        } else {
            System.out.println("Fail");
        }

        sc.close();
    }
}
