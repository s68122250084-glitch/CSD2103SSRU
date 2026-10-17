import java.util.List;

public class AlgorithmB_BottomUpBuildHeap {
    public static MaxHeap build(List<Job> source, boolean trace) {
        MaxHeap heap = new MaxHeap();
        for (Job job : source) heap.addRaw(job.copy());
        if (trace) {
            System.out.println("Array ก่อน Bottom-Up Build Heap:");
            heap.printPriorityArray();
        }
        heap.buildHeapBottomUp(trace);
        return heap;
    }
}
