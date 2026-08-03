ข้อ 2 การตรวจสอบ Palindrome

Reverse and Compare
ชื่อเมธอดหลัก: static boolean isPalindromeByReverse(String s)
วิธีที่ 1.1
  
public static boolean isPalindromeByReverse(String s) {
    if (s == null) return false;
    String reversed = new StringBuilder(s).reverse().toString();
    return s.equals(reversed);
}

วิธีที่ 1.2
ใช้ Loop วนถอยหลังต่อสตริงแบบ Iterative

public static boolean isPalindromeByReverseLoop(String s) {
    if (s == null) return false;
    StringBuilder sb = new StringBuilder();
    for (int i = s.length() - 1; i >= 0; i--) {
        sb.append(s.charAt(i));
    }
    return s.equals(sb.toString());
}

Recursive Two-Pointer
วิธีที่ 2.1: ใช้ Helper Method ควบคุมดัชนี left และ right (ประสิทธิภาพสูง / ไม่ตัดสตริง)

 public static boolean isPalindromeRecursive(String s) {
    if (s == null) return false;
    return checkHelper(s, 0, s.length() - 1);
}

private static boolean checkHelper(String s, int left, int right) {
    // Base Case: เมื่อตัวชี้ขยับมาชนกันหรือข้ามกัน แสดงว่าเป็น Palindrome
    if (left >= right) return true;

    // หากตัวอักษรซ้าย-ขวา ไม่ตรงกัน แสดงว่าไม่ใช่ Palindrome
    if (s.charAt(left) != s.charAt(right)) return false;

    // Recursive Step: ขยับขอบซ้ายไปขวา (+1) และขอบขวาไปซ้าย (-1)
    return checkHelper(s, left + 1, right - 1);
} 

วิธีที่ 2.2: ใช้ substring() ตัดหัว-ท้ายสตริง 

  public static boolean isPalindromeRecursiveSubstring(String s) {
    if (s == null) return false;
    // Base Case: สตริงยาว 0 หรือ 1 เป็น Palindrome
    if (s.length() <= 1) return true;

    // เช็กตัวแรกกับตัวสุดท้าย
    if (s.charAt(0) != s.charAt(s.length() - 1)) return false;

    // Recursive Step: ตัดตัวแรกและตัวสุดท้ายออก แล้วส่งส่วนกลางเข้าไปเช็กซ้ำ
    return isPalindromeRecursiveSubstring(s.substring(1, s.length() - 1));
}
