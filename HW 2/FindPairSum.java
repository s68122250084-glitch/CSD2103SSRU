ข้อ 6 การค้นหาคู่จำนวนที่มีผลรวมเท่ากับ k

import java.util.Arrays;

public class FindPairSum {

    // =========================================================================
    // อัลกอริทึมที่ 1: Brute Force
    // เมธอดหลักตามโจทย์: static boolean findPairBruteForce(int[] a, int k)
    // =========================================================================

    // วิธีที่ 1.1: Standard Nested Loops (เช็กทุกคู่ที่เป็นไปได้ i และ j)
    public static boolean findPairBruteForce(int[] a, int k) {
        if (a == null || a.length < 2) return false;

        for (int i = 0; i < a.length - 1; i++) {
            for (int j = i + 1; j < a.length; j++) {
                if (a[i] + a[j] == k) {
                    System.out.println("Pair found: " + a[i] + " and " + a[j]);
                    return true;
                }
            }
        }
        return false;
    }

    // วิธีที่ 1.2: Early Exit Brute Force (หยุดการค้นหาเมื่อผลรวมเกิน k เพราะอาร์เรย์เรียงลำดับแล้ว)
    public static boolean findPairBruteForceOptimized(int[] a, int k) {
        if (a == null || a.length < 2) return false;

        for (int i = 0; i < a.length - 1; i++) {
            for (int j = i + 1; j < a.length; j++) {
                int sum = a[i] + a[j];
                if (sum == k) {
                    System.out.println("Pair found: " + a[i] + " and " + a[j]);
                    return true;
                }
                // เนื่องจากอาร์เรย์เรียงลำดับแล้ว ถ้า sum > k ลูปขวาถัดไปจะยิ่งเกิน ตัดจบได้เลย
                if (sum > k) break; 
            }
        }
        return false;
    }


    // =========================================================================
    // อัลกอริทึมที่ 2: Recursive Two-Pointer
    // เมธอดหลักตามโจทย์: static boolean findPairRecursive(int[] a, int k, int left, int right)
    // =========================================================================

    // วิธีที่ 2.1: Standard Recursive Two-Pointer (ตรงตามคำอธิบายโจทย์)
    public static boolean findPairRecursive(int[] a, int k, int left, int right) {
        // Base Case: เมื่อขอบซ้ายชนหรือข้ามขอบขวา
        if (left >= right) return false;

        int sum = a[left] + a[right];

        if (sum == k) {
            System.out.println("Pair found: " + a[left] + " and " + a[right]);
            return true;
        } else if (sum < k) {
            // ผลรวมน้อยกว่า k -> เพิ่มค่า left
            return findPairRecursive(a, k, left + 1, right);
        } else {
            // ผลรวมมากกว่า k -> ลดค่า right
            return findPairRecursive(a, k, left, right - 1);
        }
    }

    // วิธีที่ 2.2: Tail-Recursive / Helper-wrapper Style (ออกแบบแยก Parameter เพื่อเรียกใส่ง่ายขึ้น)
    public static boolean findPairRecursiveWrapper(int[] a, int k) {
        if (a == null || a.length < 2) return false;
        return findPairRecursive(a, k, 0, a.length - 1);
    }


    // =========================================================================
    // อัลกอริทึมที่ 3: Binary Search
    // เมธอดหลักตามโจทย์: static boolean findPairBinarySearch(int[] a, int k)
    // =========================================================================

    // วิธีที่ 3.1: Custom Binary Search (วนลูปแต่ละตัว แล้วเขียน Binary Search หา k - a[i] ในช่วงที่เหลือ)
    public static boolean findPairBinarySearch(int[] a, int k) {
        if (a == null || a.length < 2) return false;

        for (int i = 0; i < a.length - 1; i++) {
            int target = k - a[i];
            
            // ค้นหา target ในช่วงดัชนี i + 1 ถึงอาร์เรย์ตัวสุดท้าย
            int left = i + 1;
            int right = a.length - 1;

            while (left <= right) {
                int mid = left + (right - left) / 2;
                if (a[mid] == target) {
                    System.out.println("Pair found: " + a[i] + " and " + a[mid]);
                    return true;
                }
                if (a[mid] < target) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        return false;
    }

    // วิธีที่ 3.2: Built-in Binary Search (ใช้ Arrays.binarySearch ของ Java)
    public static boolean findPairJavaBinarySearch(int[] a, int k) {
        if (a == null || a.length < 2) return false;

        for (int i = 0; i < a.length - 1; i++) {
            int target = k - a[i];
            // ค้นหาเฉพาะช่วงถัดไป (fromIndex = i + 1, toIndex = a.length)
            int index = Arrays.binarySearch(a, i + 1, a.length, target);
            
            if (index > 0) {
                System.out.println("Pair found: " + a[i] + " and " + a[index]);
                return true;
            }
        }
        return false;
    }


    // =========================================================================
    // Main Method สำหรับทดสอบโปรแกรม
    // =========================================================================
    public static void main(String[] args) {
        int[] a = {2, 4, 7, 11, 15, 20};
        int k = 18;

        System.out.println("Input Array : " + Arrays.toString(a) + " | Target k = " + k);
        System.out.println("------------------------------------------------------------------");

        System.out.print("1.1 Brute Force            : ");
        findPairBruteForce(a, k);

        System.out.print("1.2 Brute Force Optimized  : ");
        findPairBruteForceOptimized(a, k);

        System.out.print("2.1 Recursive Two-Pointer  : ");
        findPairRecursive(a, k, 0, a.length - 1);

        System.out.print("2.2 Recursive Wrapper      : ");
        findPairRecursiveWrapper(a, k);

        System.out.print("3.1 Custom Binary Search   : ");
        findPairBinarySearch(a, k);

        System.out.print("3.2 Java BinarySearch      : ");
        findPairJavaBinarySearch(a, k);
    }
}
