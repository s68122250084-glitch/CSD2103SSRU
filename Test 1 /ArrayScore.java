import java.util.Scanner;

public class ArrayScore {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[] score = new int[5];
        int total = 0;

        for (int i = 0; i < score.length; i++) {
            System.out.print("Enter score " + (i + 1) + ": ");
            score[i] = sc.nextInt();
            total += score[i];
        }

        double average = (double) total / score.length;

        System.out.println("Total score = " + total);
        System.out.println("Average score = " + average);

        sc.close();
    }
}
