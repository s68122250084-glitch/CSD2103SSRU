ข้อ 4 การจัดกลุ่มจำนวนคู่และจำนวนคี่

import java.util.Arrays;

public class RearrangeEvenOdd {

    // Helper Method สำหรับการสลับค่าใน Array
    private static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    // =========================================================================
    // อัลกอริทึมที่ 1: Recursive Two-Pointer
    // เมธอดหลักตามโจทย์: static void rearrangeRecursive(int[] a, int left, int right)
    // =========================================================================

    // วิธีที่ 1.1: Standard Recursive Two-Pointer (ตรงตามคำอธิบายโจทย์)
    public static void rearrangeRecursive(int[] a, int left, int right) {
        // Base Case: เมื่อตัวชี้ข้ามกันหรือชนกัน
        if (left >= right) return;

        if (a[left] % 2 == 0) {
            // ดัชนีซ้ายเป็นเลขคู่ -> ขยับ left ไปทางขวา
            rearrangeRecursive(a, left + 1, right);
        } else if (a[right] % 2 != 0) {
            // ดัชนีขวาเป็นเลขคี่ -> ขยับ right ไปทางซ้าย
            rearrangeRecursive(a, left, right - 1);
        } else {
            // ดัชนีซ้ายเป็นคี่ และ ดัชนีขวาเป็นคู่ -> สลับค่า แล้วขยับทั้งสองฝั่ง
            swap(a, left, right);
            rearrangeRecursive(a, left + 1, right - 1);
        }
    }

    // วิธีที่ 1.2: Recursive Single-Step Swap (ขยับทีละ 1 ขั้น)
    public static void rearrangeRecursiveAlt(int[] a, int left, int right) {
        if (left >= right) return;

        if (a[left] % 2 != 0 && a[right] % 2 == 0) {
            swap(a, left, right);
        }
        
        int nextLeft = (a[left] % 2 == 0) ? left + 1 : left;
        int nextRight = (a[right] % 2 != 0) ? right - 1 : right;

        rearrangeRecursiveAlt(a, nextLeft, nextRight);
    }


    // =========================================================================
    // อัลกอริทึมที่ 2: Iterative Two-Pointer
    // เมธอดหลักตามโจทย์: static void rearrangeTwoPointer(int[] a)
    // =========================================================================

    // วิธีที่ 2.1: Classic Opposite Two-Pointer (ใช้ while-loop ขยับเข้าหากัน)
    public static void rearrangeTwoPointer(int[] a) {
        if (a == null || a.length <= 1) return;

        int left = 0;
        int right = a.length - 1;

        while (left < right) {
            while (left < right && a[left] % 2 == 0) {
                left++;
            }
            while (left < right && a[right] % 2 != 0) {
                right--;
            }
            if (left < right) {
                swap(a, left, right);
                left++;
                right--;
            }
        }
    }

    // วิธีที่ 2.2: Lomuto Partition Style / Reader-Writer Pointer (วิ่งไปทางเดียวกัน)
    public static void rearrangeTwoPointerLomuto(int[] a) {
        if (a == null || a.length <= 1) return;

        int evenIndex = 0; // ตำแหน่งไว้รอใส่เลขคู่
        for (int i = 0; i < a.length; i++) {
            if (a[i] % 2 == 0) {
                swap(a, i, evenIndex);
                evenIndex++;
            }
        }
    }


    // =========================================================================
    // อัลกอริทึมที่ 3: Extra Array
    // เมธอดหลักตามโจทย์: static int[] rearrangeExtraArray(int[] a)
    // =========================================================================

    // วิธีที่ 3.1: Two-Pointer filling (เติมจาก หัว และ ท้าย ของ Array ใหม่พร้อมกัน)
    public static int[] rearrangeExtraArray(int[] a) {
        if (a == null) return null;

        int[] result = new int[a.length];
        int left = 0;
        int right = a.length - 1;

        for (int val : a) {
            if (val % 2 == 0) {
                result[left++] = val; // เลขคู่ใส่จากซ้ายไปขวา
            } else {
                result[right--] = val; // เลขคี่ใส่จากขวาไปซ้าย
            }
        }
        return result;
    }

    // วิธีที่ 3.2: Two-Pass Filling (วนลูป 2 รอบ: ใส่คู่รอบแรก ใส่คี่รอบสอง)
    public static int[] rearrangeExtraArrayTwoPass(int[] a) {
        if (a == null) return null;

        int[] result = new int[a.length];
        int index = 0;

        // รอบที่ 1: เติมเฉพาะเลขคู่
        for (int val : a) {
            if (val % 2 == 0) {
                result[index++] = val;
            }
        }
        // รอบที่ 2: เติมเฉพาะเลขคี่
        for (int val : a) {
            if (val % 2 != 0) {
                result[index++] = val;
            }
        }
        return result;
    }


    // =========================================================================
    // Main Method สำหรับทดสอบ
    // =========================================================================
    public static void main(String[] args) {
        int[] input = {7, 2, 9, 4, 1, 6, 3, 8};

        System.out.println("Input Original: " + Arrays.toString(input));
        System.out.println("------------------------------------------------------------------");

        // ทดสอบ 1.1
        int[] arr1_1 = input.clone();
        rearrangeRecursive(arr1_1, 0, arr1_1.length - 1);
        System.out.println("1.1 Recursive Two-Pointer        : " + Arrays.toString(arr1_1));

        // ทดสอบ 1.2
        int[] arr1_2 = input.clone();
        rearrangeRecursiveAlt(arr1_2, 0, arr1_2.length - 1);
        System.out.println("1.2 Recursive Single-Step        : " + Arrays.toString(arr1_2));

        // ทดสอบ 2.1
        int[] arr2_1 = input.clone();
        rearrangeTwoPointer(arr2_1);
        System.out.println("2.1 Iterative Opposite Pointer   : " + Arrays.toString(arr2_1));

        // ทดสอบ 2.2
        int[] arr2_2 = input.clone();
        rearrangeTwoPointerLomuto(arr2_2);
        System.out.println("2.2 Iterative Same-Direction     : " + Arrays.toString(arr2_2));

        // ทดสอบ 3.1
        int[] res3_1 = rearrangeExtraArray(input);
        System.out.println("3.1 Extra Array (Head-Tail Fill) : " + Arrays.toString(res3_1));

        // ทดสอบ 3.2
        int[] res3_2 = rearrangeExtraArrayTwoPass(input);
        System.out.println("3.2 Extra Array (Two-Pass Fill)  : " + Arrays.toString(res3_2));
    }
}
