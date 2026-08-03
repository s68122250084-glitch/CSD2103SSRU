ข้อ 1 การกลับลำดับสตริง

public class StringReverser {

    // =========================================================================
    // อัลกอริทึมที่ 1: Recursive Algorithm
    // เมธอดหลักตามโจทย์: static String reverseRecursive(String s)
    // =========================================================================

    // วิธีที่ 1.1: Standard Substring Recursive (ตรงตามคำอธิบายโจทย์)
    // นำตัวอักษรตัวสุดท้าย มาต่อกับผลลัพธ์จากการเรียกเมธอดกับสตริงส่วนที่เหลือ
    public static String reverseRecursive(String s) {
        // Base Case: ถ้าสตริงเป็น null หรือความยาว <= 1 คืนค่าเดิมกลับไป
        if (s == null || s.length() <= 1) {
            return s;
        }

        // นำตัวอักษรตัวสุดท้าย + ผลลัพธ์จากการ Recursive สตริงตัวที่เหลือ (0 ถึง length - 2)
        return s.charAt(s.length() - 1) + reverseRecursive(s.substring(0, s.length() - 1));
    }

    // วิธีที่ 1.2: Index-Based Recursive with StringBuilder (ใช้ Helper และ Index ช่วยเพื่อประหยัด Memory)
    public static String reverseRecursiveHelper(String s) {
        if (s == null) return null;
        return recursiveIndexHelper(s, s.length() - 1);
    }

    private static String recursiveIndexHelper(String s, int index) {
        if (index < 0) {
            return "";
        }
        return s.charAt(index) + recursiveIndexHelper(s, index - 1);
    }


    // =========================================================================
    // อัลกอริทึมที่ 2: Iterative Algorithm
    // เมธอดหลักตามโจทย์: static String reverseIterative(String s)
    // =========================================================================

    // วิธีที่ 2.1: Reverse Loop with StringBuilder (วนลูปย้อนหลังจากท้ายไปหน้า)
    public static String reverseIterative(String s) {
        if (s == null) return null;

        StringBuilder sb = new StringBuilder();
        // วนลูปอ่านข้อความจากตำแหน่งสุดท้ายย้อนกลับไปยังตำแหน่งแรก
        for (int i = s.length() - 1; i >= 0; i--) {
            sb.append(s.charAt(i));
        }

        return sb.toString();
    }

    // วิธีที่ 2.2: Two-Pointer Character Array Swap (แปลงเป็น char[] แล้วสลับตำแหน่งหัว-ท้าย)
    public static String reverseIterativeTwoPointer(String s) {
        if (s == null) return null;

        char[] chars = s.toCharArray();
        int left = 0;
        int right = chars.length - 1;

        while (left < right) {
            char temp = chars[left];
            chars[left] = chars[right];
            chars[right] = temp;
            left++;
            right--;
        }

        return new String(chars);
    }


    // =========================================================================
    // Main Method สำหรับทดสอบโปรแกรม
    // =========================================================================
    public static void main(String[] args) {
        String input = "pots&pans";

        System.out.println("Input Original : " + input);
        System.out.println("Expected Output: snap&stop");
        System.out.println("------------------------------------------------------------------");

        // ทดสอบ 1.1
        String res1_1 = reverseRecursive(input);
        System.out.println("1.1 Recursive Substring   : " + res1_1);

        // ทดสอบ 1.2
        String res1_2 = reverseRecursiveHelper(input);
        System.out.println("1.2 Recursive Index-Based : " + res1_2);

        // ทดสอบ 2.1
        String res2_1 = reverseIterative(input);
        System.out.println("2.1 Iterative Reverse Loop: " + res2_1);

        // ทดสอบ 2.2
        String res2_2 = reverseIterativeTwoPointer(input);
        System.out.println("2.2 Iterative Two-Pointer : " + res2_2);
    }
}
