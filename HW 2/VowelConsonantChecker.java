ข้อ 3 การเปรียบเทียบจำนวนสระและพยัญชนะ

public class VowelConsonantChecker {

    // Helper Method เช็กว่าเป็นสระหรือไม่
    private static boolean isVowel(char ch) {
        ch = Character.toLowerCase(ch);
        return ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u';
    }

    // ==========================================
    // หมวดที่ 1: Recursive Counting
    // ==========================================

    // วิธีที่ 1.1: Recursive แบบใช้ Helper Method สะสมค่าตัวนับผ่าน Index (แนะนำ/ตรงโจทย์ที่สุด)
    public static boolean hasMoreVowelsRecursive(String s) {
        if (s == null) return false;
        return countHelper(s, 0, 0, 0);
    }

    private static boolean countHelper(String s, int index, int vowels, int consonants) {
        // Base Case: เมื่ออ่านจนครบทุกตัวอักษร ให้เปรียบเทียบค่า
        if (index >= s.length()) {
            return vowels > consonants;
        }

        char ch = s.charAt(index);
        
        // เช็กว่าเป็นตัวอักษรภาษาอังกฤษหรือไม่
        if (Character.isLetter(ch)) {
            if (isVowel(ch)) {
                vowels++;
            } else {
                consonants++;
            }
        }

        // Recursive Step: ส่งค่าผลรวมนับสะสมไปยังการเรียกครั้งถัดไป
        return countHelper(s, index + 1, vowels, consonants);
    }


    // วิธีที่ 1.2: Recursive แบบส่งผลต่าง (Diff = Vowels - Consonants) ผ่าน Return Value
    public static boolean hasMoreVowelsRecursiveDiff(String s) {
        if (s == null) return false;
        return countDiff(s, 0) > 0;
    }

    private static int countDiff(String s, int index) {
        if (index >= s.length()) return 0;

        char ch = s.charAt(index);
        int currentVal = 0;

        if (Character.isLetter(ch)) {
            currentVal = isVowel(ch) ? 1 : -1; // สระได้ +1, พยัญชนะได้ -1
        }

        return currentVal + countDiff(s, index + 1);
    }


    // ==========================================
    // หมวดที่ 2: Iterative Counting
    // ==========================================

    // วิธีที่ 2.1: Iterative แบบมาตรฐานใช้ Loop และ Counter
    public static boolean hasMoreVowelsIterative(String s) {
        if (s == null) return false;

        int vowels = 0;
        int consonants = 0;

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);

            // กรองเฉพาะตัวอักษรภาษาอังกฤษ (ไม่นับตัวเลข/เว้นวรรค/สัญลักษณ์)
            if (Character.isLetter(ch)) {
                if (isVowel(ch)) {
                    vowels++;
                } else {
                    consonants++;
                }
            }
        }

        return vowels > consonants;
    }


    // วิธีที่ 2.2: Iterative แบบใช้ Java Streams / Lambda Expressions (สไตล์โมเดิร์น)
    public static boolean hasMoreVowelsIterativeStream(String s) {
        if (s == null) return false;

        long vowels = s.chars()
                .mapToObj(c -> (char) c)
                .filter(Character::isLetter)
                .filter(VowelConsonantChecker::isVowel)
                .count();

        long consonants = s.chars()
                .mapToObj(c -> (char) c)
                .filter(Character::isLetter)
                .filter(c -> !isVowel(c))
                .count();

        return vowels > consonants;
    }


    // ==========================================
    // Main Method สำหรับทดสอบ
    // ==========================================
    public static void main(String[] args) {
        String[] testCases = {
            "education",             // Vowels: 5, Consonants: 4 -> true
            "Algorithm! 123",        // Vowels: 3, Consonants: 6 -> false
            "A E I O U b c",         // มีเว้นวรรค/ตัวพิมพ์ใหญ่ -> true
            "Java 2026 @#$"          // มีตัวเลขและสัญลักษณ์ -> false
        };

        System.out.println("=== ผลการทดสอบทั้ง 4 วิธี ===");
        System.out.printf("%-20s | %-10s | %-10s | %-10s | %-10s%n", 
                          "Input", "1.1 Rec", "1.2 Diff", "2.1 Loop", "2.2 Stream");
        System.out.println("----------------------------------------------------------------------------------");

        for (String test : testCases) {
            boolean r1_1 = hasMoreVowelsRecursive(test);
            boolean r1_2 = hasMoreVowelsRecursiveDiff(test);
            boolean r2_1 = hasMoreVowelsIterative(test);
            boolean r2_2 = hasMoreVowelsIterativeStream(test);

            System.out.printf("%-20s | %-10b | %-10b | %-10b | %-10b%n", 
                              test, r1_1, r1_2, r2_1, r2_2);
        }
    }
}
