public class BubbleSortCaseStudy {

    public static void main(String[] args) {

        int[] scores = {72, 91, 65, 88, 79};

        int comparison = 0;
        int swap = 0;

        for (int i = 0; i < scores.length - 1; i++) {

            boolean swapped = false;

            for (int j = 0; j < scores.length - i - 1; j++) {

                comparison++;

                if (scores[j] < scores[j + 1]) {

                    int temp = scores[j];
                    scores[j] = scores[j + 1];
                    scores[j + 1] = temp;

                    swap++;
                    swapped = true;
                }
            }

            if (!swapped) {
                break;
            }
        }

        System.out.println("คะแนนเรียงจากมากไปน้อย");

        for (int s : scores) {
            System.out.print(s + " ");
        }

        System.out.println();
        System.out.println("Comparisons = " + comparison);
        System.out.println("Swaps = " + swap);

    }
}
