import java.util.ArrayList;
import java.util.List;

public class HeapComparison {
    public static long measureA(List<Job> data) {
        long start = System.nanoTime();
        AlgorithmA_InsertHeapifyUp.build(data, false);
        return System.nanoTime() - start;
    }

    public static long measureB(List<Job> data) {
        long start = System.nanoTime();
        AlgorithmB_BottomUpBuildHeap.build(data, false);
        return System.nanoTime() - start;
    }

    public static long measureRemoveMax(List<Job> data, int count) {
        MaxHeap heap = AlgorithmB_BottomUpBuildHeap.build(data, false);
        int actual = Math.min(count, heap.size());
        long start = System.nanoTime();
        for (int i = 0; i < actual; i++) heap.removeMax();
        return System.nanoTime() - start;
    }

    public static List<Job> generateJobs(int n) {
        List<Job> result = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            String id = String.format("J%06d", i);
            int priority = (i * 37) % 1000 + 1;
            result.add(new Job(id, "Job-" + i, priority, "08:00", (i % 60) + 1));
        }
        return result;
    }
}
