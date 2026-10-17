import java.util.List;

public class AlgorithmA_InsertHeapifyUp {
    public static MaxHeap build(List<Job> source, boolean trace) {
        MaxHeap heap = new MaxHeap();
        if (trace) System.out.println("Array เริ่มต้น: []");
        for (Job job : source) {
            heap.insert(job.copy());
            if (trace) {
                System.out.println("เพิ่ม " + job.getJobId() + " - " + job.getJobName()
                        + " (Priority " + job.getPriority() + ")");
                heap.printPriorityArray();
            }
        }
        return heap;
    }
}
