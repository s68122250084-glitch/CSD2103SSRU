ข้อ 5 การแบ่งอาร์เรย์ตามค่า k

import java.util.Arrays;

public class PartitionByK {

    // Helper Method สำหรับการสลับตำแหน่งสมาชิกใน Array
    private static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    // =========================================================================
    // อัลกอริทึมที่ 1: Recursive Partition
    // เมธอดหลักตามโจทย์: static void partitionRecursive(int[] a, int k, int left, int right)
    // =========================================================================

    // วิธีที่ 1.1: Standard Two-Pointer Recursive (เข้าหากันจาก ซ้าย-ขวา)
    public static void partitionRecursive(int[] a, int k, int left, int right) {
        // Base Case: เมื่อตัวชี้ข้ามกันหรือชนกัน
        if (left >= right) return;

        if (a[left] <= k) {
            // ฝั่งซ้าย <= k ถูกต้องแล้ว -> ขยับขอบซ้ายไปทางขวา
            partitionRecursive(a, k, left + 1, right);
        } else if (a[right] > k) {
            // ฝั่งขวา > k ถูกต้องแล้ว -> ขยับขอบขวาไปทางซ้าย
            partitionRecursive(a, k, left, right - 1);
        } else {
            // ฝั่งซ้าย > k และ ฝั่งขวา <= k -> สลับค่า แล้วขยับทั้งสองฝั่ง
            swap(a, left, right);
            partitionRecursive(a, k, left + 1, right - 1);
        }
    }

    // วิธีที่ 1.2: Lomuto Style Recursive (วิ่งไปทางเดียวกันจากซ้ายไปขวา)
    public static void partitionRecursiveLomuto(int[] a, int k, int currentIndex, int boundaryIndex) {
        if (currentIndex >= a.length) return;

        if (a[currentIndex] <= k) {
            swap(a, currentIndex, boundaryIndex);
            partitionRecursiveLomuto(a, k, currentIndex + 1, boundaryIndex + 1);
        } else {
            partitionRecursiveLomuto(a, k, currentIndex + 1, boundaryIndex);
        }
    }


    // =========================================================================
    // อัลกอริทึมที่ 2: Iterative Partition
    // เมธอดหลักตามโจทย์: static void partitionIterative(int[] a, int k)
    // =========================================================================

    // วิธีที่ 2.1: Hoare Partition Style (ใช้ Two-Pointer วนลูปเข้าหากัน)
    public static void partitionIterative(int[] a, int k) {
        if (a == null || a.length <= 1) return;

        int left = 0;
        int right = a.length - 1;

        while (left < right) {
            while (left < right && a[left] <= k) {
                left++;
            }
            while (left < right && a[right] > k) {
                right--;
            }
            if (left < right) {
                swap(a, left, right);
                left++;
                right--;
            }
        }
    }

    // วิธีที่ 2.2: Lomuto Partition Style (ใช้ Pointer วนลูปไปทางเดียวกัน)
    public static void partitionIterativeLomuto(int[] a, int k) {
        if (a == null || a.length <= 1) return;

        int boundary = 0; // ตัวชี้เขตแบ่งสำหรับกลุ่ม <= k
        for (int i = 0; i < a.length; i++) {
            if (a[i] <= k) {
                swap(a, i, boundary);
                boundary++;
            }
        }
    }


    // =========================================================================
    // อัลกอริทึมที่ 3: Sorting-Based Algorithm
    // เมธอดหลักตามโจทย์: static void partitionBySorting(int[] a, int k)
    // =========================================================================

    // วิธีที่ 3.1: Sort ทั้งหมดก่อน แล้วใช้ Binary Search หาจุดแบ่งของ k
    public static void partitionBySorting(int[] a, int k) {
        if (a == null || a.length <= 1) return;

        // 1. เรียงลำดับข้อมูลทั้งหมดก่อน O(N log N)
        Arrays.sort(a);

        // 2. ค้นหาตำแหน่งสุดท้ายที่มีค่าน้อยกว่าหรือเท่ากับ k ด้วย Binary Search
        int left = 0, right = a.length - 1;
        int lastIndex = -1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (a[mid] <= k) {
                lastIndex = mid; // เจอตัว <= k เก็บตำแหน่งไว้แล้วขยับไปหาทางขวาต่อ
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        // แสดงผลตำแหน่งสุดท้ายที่มีค่า <= k เพื่อยืนยันจุดแบ่ง
        System.out.println("   [Sorting + BinarySearch] ดัชนีสุดท้ายที่มีค่า <= " + k + " คือ index: " + lastIndex);
    }

    // วิธีที่ 3.2: Custom Comparator Sort (เรียงลำดับโดยใช้เงื่อนไขแบ่งกลุ่ม <= k มาก่อน > k)
    public static void partitionByCustomSort(int[] a, int k) {
        if (a == null || a.length <= 1) return;

        // แปลงเป็น Integer[] ชั่วคราวเพื่อใช้ Custom Comparator
        Integer[] temp = Arrays.stream(a).boxed().toArray(Integer[]::new);

        Arrays.sort(temp, (x, y) -> {
            boolean xLessOrEqual = x <= k;
            boolean yLessOrEqual = y <= k;

            if (xLessOrEqual && !yLessOrEqual) return -1; // x <= k ให้มาก่อน
            if (!xLessOrEqual && yLessOrEqual) return 1;  // y <= k ให้มาก่อน
            return Integer.compare(x, y);                 // ถ้ากลุ่มเดียวกัน ให้เรียงตามปกติ
        });

        // คัดลอกค่ากลับเข้าใน a
        for (int i = 0; i < a.length; i++) {
            a[i] = temp[i];
        }
    }


    // =========================================================================
    // Main Method สำหรับทดสอบโปรแกรม
    // =========================================================================
    public static void main(String[] args) {
        int[] input = {12, 4, 7, 15, 3, 10, 8};
        int k = 8;

        System.out.println("Input Original : " + Arrays.toString(input) + " | k = " + k);
        System.out.println("------------------------------------------------------------------");

        // ทดสอบ 1.1
        int[] arr1_1 = input.clone();
        partitionRecursive(arr1_1, k, 0, arr1_1.length - 1);
        System.out.println("1.1 Recursive Two-Pointer   : " + Arrays.toString(arr1_1));

        // ทดสอบ 1.2
        int[] arr1_2 = input.clone();
        partitionRecursiveLomuto(arr1_2, k, 0, 0);
        System.out.println("1.2 Recursive Lomuto Style  : " + Arrays.toString(arr1_2));

        // ทดสอบ 2.1
        int[] arr2_1 = input.clone();
        partitionIterative(arr2_1, k);
        System.out.println("2.1 Iterative Two-Pointer   : " + Arrays.toString(arr2_1));

        // ทดสอบ 2.2
        int[] arr2_2 = input.clone();
        partitionIterativeLomuto(arr2_2, k);
        System.out.println("2.2 Iterative Lomuto Style  : " + Arrays.toString(arr2_2));

        // ทดสอบ 3.1
        int[] arr3_1 = input.clone();
        System.out.print("3.1 ");
        partitionBySorting(arr3_1, k);
        System.out.println("    ผลลัพธ์การจัดเรียง       : " + Arrays.toString(arr3_1));

        // ทดสอบ 3.2
        int[] arr3_2 = input.clone();
        partitionByCustomSort(arr3_2, k);
        System.out.println("3.2 Custom Comparator Sort  : " + Arrays.toString(arr3_2));
    }
}
