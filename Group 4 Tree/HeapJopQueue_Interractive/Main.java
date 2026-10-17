import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner SC = new Scanner(System.in);
    private static List<Job> jobs = new ArrayList<>();

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n===== ระบบเปรียบเทียบ Max Heap =====");
            System.out.println("1. กรอกข้อมูล Job");
            System.out.println("2. แสดงข้อมูล Job ที่บันทึก");
            System.out.println("3. รัน Algorithm A - Insert + Heapify Up");
            System.out.println("4. รัน Algorithm B - Bottom-Up Build Heap");
            System.out.println("5. เปรียบเทียบ Algorithm A และ B");
            System.out.println("6. ทดลองคำสั่ง Heap");
            System.out.println("7. ทดลองประสิทธิภาพ n = 1,000 / 10,000 / 100,000 / 500,000");
            System.out.println("0. ออกจากโปรแกรม");
            System.out.print("เลือกเมนู: ");
            int choice = readInt();
            switch (choice) {
                case 1 -> inputJobs();
                case 2 -> displayJobs();
                case 3 -> runA();
                case 4 -> runB();
                case 5 -> compare();
                case 6 -> operations();
                case 7 -> benchmark();
                case 0 -> { System.out.println("จบการทำงาน"); return; }
                default -> System.out.println("กรุณาเลือกเมนู 0-7");
            }
        }
    }

    private static final String[][] JOB_LIST = {
        {"J01", "Backup"},
        {"J02", "Emergency"},
        {"J03", "Report"},
        {"J04", "DatabaseFix"},
        {"J05", "Email"},
        {"J06", "SecurityScan"}
    };

    private static void inputJobs() {
        jobs = new ArrayList<>();
        boolean[] entered = new boolean[JOB_LIST.length];

        System.out.println("\n===== รายการงาน =====");
        for (String[] job : JOB_LIST) {
            System.out.println(job[0] + " - " + job[1]);
        }

        System.out.println("\nเลือก Job ID เองได้ตามลำดับที่ต้องการ");
        System.out.println("ตัวอย่าง: J02 -> J03 -> J06 -> J01");
        System.out.println("พิมพ์ DONE เพื่อจบการกรอกข้อมูล");

        while (true) {
            System.out.print("\nเลือก Job ID ที่ต้องการกรอก: ");
            String selectedId = SC.nextLine().trim();

            if (selectedId.equalsIgnoreCase("DONE")) {
                break;
            }

            int selectedIndex = -1;
            for (int i = 0; i < JOB_LIST.length; i++) {
                if (JOB_LIST[i][0].equalsIgnoreCase(selectedId)) {
                    selectedIndex = i;
                    break;
                }
            }

            if (selectedIndex == -1) {
                System.out.println("ไม่พบ Job ID นี้ กรุณาเลือก J01 - J06");
                continue;
            }

            if (entered[selectedIndex]) {
                System.out.println("Job นี้กรอกข้อมูลไปแล้ว กรุณาเลือก Job ที่ยังไม่ได้กรอก");
                continue;
            }

            String id = JOB_LIST[selectedIndex][0];
            String name = JOB_LIST[selectedIndex][1];

            System.out.println("\n[" + id + " - " + name + "]");
            int priority = readNonNegative("Priority: ");
            System.out.print("Arrival Time (HH:mm): ");
            String arrival = SC.nextLine();
            int estimated = readPositive("Estimated Time: ");

            jobs.add(new Job(id, name, priority, arrival, estimated));
            entered[selectedIndex] = true;
            System.out.println("บันทึก " + id + " - " + name + " เรียบร้อย");

            boolean allEntered = true;
            for (boolean value : entered) {
                if (!value) {
                    allEntered = false;
                    break;
                }
            }
            if (allEntered) {
                System.out.println("\nกรอกข้อมูลครบทุก Job แล้ว");
                break;
            }
        }

        if (jobs.isEmpty()) {
            System.out.println("ยังไม่มีข้อมูล Job ที่บันทึก");
        } else {
            System.out.println("\nจบการกรอกข้อมูล มี Job ทั้งหมด " + jobs.size() + " รายการ");
        }
    }

    private static void displayJobs() {
        if (jobs.isEmpty()) { System.out.println("ยังไม่มีข้อมูล Job"); return; }
        System.out.println("\n===== ข้อมูล Job ที่บันทึก =====");
        for (Job job : jobs) System.out.println(job);
    }

    private static boolean ready() {
        if (jobs.isEmpty()) { System.out.println("กรุณากรอกข้อมูล Job ก่อน"); return false; }
        return true;
    }

    private static void runA() {
        if (!ready()) return;
        System.out.println("\n===== Algorithm A: Insert + Heapify Up =====");
        AlgorithmA_InsertHeapifyUp.build(jobs, true).displayHeap();
    }

    private static void runB() {
        if (!ready()) return;
        System.out.println("\n===== Algorithm B: Bottom-Up Build Heap + Heapify Down =====");
        AlgorithmB_BottomUpBuildHeap.build(jobs, true).displayHeap();
    }

    private static void compare() {
        if (!ready()) return;
        System.out.println("\n===== เปรียบเทียบข้อมูลชุดเดียวกัน =====");
        MaxHeap a = AlgorithmA_InsertHeapifyUp.build(jobs, true);
        MaxHeap b = AlgorithmB_BottomUpBuildHeap.build(jobs, true);
        System.out.println("\nMax ของ Algorithm A: " + a.peekMax());
        System.out.println("Max ของ Algorithm B: " + b.peekMax());
        System.out.printf("เวลา A: %.3f ms%n", HeapComparison.measureA(jobs) / 1_000_000.0);
        System.out.printf("เวลา B: %.3f ms%n", HeapComparison.measureB(jobs) / 1_000_000.0);
        System.out.println("Complexity A = O(n log n)");
        System.out.println("Complexity B = O(n)");
    }

    private static void operations() {
        if (!ready()) return;
        MaxHeap heap = AlgorithmB_BottomUpBuildHeap.build(jobs, false);
        while (true) {
            System.out.println("\n===== Heap Operations =====");
            System.out.println("1. INSERT");
            System.out.println("2. PEEK_MAX");
            System.out.println("3. REMOVE_MAX");
            System.out.println("4. INCREASE_PRIORITY");
            System.out.println("5. BUILD_HEAP");
            System.out.println("6. DISPLAY_HEAP");
            System.out.println("0. กลับเมนูหลัก");
            System.out.print("เลือกเมนู: ");
            int c = readInt();
            if (c == 0) return;
            if (c == 1) insertOperation(heap);
            else if (c == 2) System.out.println("PEEK_MAX: " + heap.peekMax());
            else if (c == 3) System.out.println("REMOVE_MAX: " + heap.removeMax());
            else if (c == 4) increaseOperation(heap);
            else if (c == 5) { heap.buildHeapBottomUp(true); System.out.println("BUILD_HEAP เสร็จแล้ว"); }
            else if (c == 6) heap.displayHeap();
            else System.out.println("เมนูไม่ถูกต้อง");
        }
    }

    private static void insertOperation(MaxHeap heap) {
        System.out.print("Job ID: "); String id = SC.nextLine();
        System.out.print("Job Name: "); String name = SC.nextLine();
        int p = readNonNegative("Priority: ");
        System.out.print("Arrival Time: "); String arrival = SC.nextLine();
        int e = readPositive("Estimated Time: ");
        heap.insert(new Job(id, name, p, arrival, e));
        System.out.println("INSERT สำเร็จ");
    }

    private static void increaseOperation(MaxHeap heap) {
        System.out.print("Job ID: "); String id = SC.nextLine();
        int p = readNonNegative("Priority ใหม่: ");
        System.out.println(heap.increasePriority(id, p) ? "INCREASE_PRIORITY สำเร็จ" : "ไม่พบ Job หรือ Priority ใหม่ต่ำกว่าเดิม");
    }

    private static void benchmark() {
        int[] sizes = {1000, 10000, 100000, 500000};
        System.out.println("\nn\tAlgorithm A(ms)\tAlgorithm B(ms)\tRemove Max 1000(ms)");
        for (int n : sizes) {
            List<Job> data = HeapComparison.generateJobs(n);
            double a = HeapComparison.measureA(data) / 1_000_000.0;
            double b = HeapComparison.measureB(data) / 1_000_000.0;
            double r = HeapComparison.measureRemoveMax(data, 1000) / 1_000_000.0;
            System.out.printf("%d\t%.3f\t\t%.3f\t\t%.3f%n", n, a, b, r);
        }
    }

    private static int readInt() {
        while (!SC.hasNextInt()) { SC.next(); System.out.print("กรอกเป็นตัวเลข: "); }
        int value = SC.nextInt(); SC.nextLine(); return value;
    }

    private static int readPositive(String prompt) {
        int value;
        do { System.out.print(prompt); value = readInt(); } while (value <= 0);
        return value;
    }

    private static int readNonNegative(String prompt) {
        int value;
        do { System.out.print(prompt); value = readInt(); } while (value < 0);
        return value;
    }
}
