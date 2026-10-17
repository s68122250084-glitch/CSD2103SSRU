import java.util.ArrayList;
import java.util.List;

public class MaxHeap {
    private final List<Job> heap = new ArrayList<>();

    public int size() { return heap.size(); }
    public boolean isEmpty() { return heap.isEmpty(); }
    public void clear() { heap.clear(); }
    public List<Job> getItems() { return new ArrayList<>(heap); }

    public void insert(Job job) {
        heap.add(job);
        heapifyUp(heap.size() - 1);
    }

    public void addRaw(Job job) { heap.add(job); }

    public Job peekMax() {
        return heap.isEmpty() ? null : heap.get(0);
    }

    public Job removeMax() {
        if (heap.isEmpty()) return null;
        Job result = heap.get(0);
        Job last = heap.remove(heap.size() - 1);
        if (!heap.isEmpty()) {
            heap.set(0, last);
            heapifyDown(0);
        }
        return result;
    }

    public boolean increasePriority(String jobId, int newPriority) {
        for (int i = 0; i < heap.size(); i++) {
            Job job = heap.get(i);
            if (job.getJobId().equalsIgnoreCase(jobId)) {
                if (newPriority < job.getPriority()) return false;
                job.setPriority(newPriority);
                heapifyUp(i);
                return true;
            }
        }
        return false;
    }

    public void buildHeapBottomUp(boolean trace) {
        for (int i = heap.size() / 2 - 1; i >= 0; i--) {
            heapifyDown(i);
            if (trace) printPriorityArray();
        }
    }

    private void heapifyUp(int index) {
        while (index > 0) {
            int parent = (index - 1) / 2;
            if (compare(heap.get(parent), heap.get(index)) >= 0) break;
            swap(parent, index);
            index = parent;
        }
    }

    private void heapifyDown(int index) {
        while (true) {
            int left = index * 2 + 1;
            int right = left + 1;
            int largest = index;
            if (left < heap.size() && compare(heap.get(left), heap.get(largest)) > 0) largest = left;
            if (right < heap.size() && compare(heap.get(right), heap.get(largest)) > 0) largest = right;
            if (largest == index) break;
            swap(index, largest);
            index = largest;
        }
    }

    // Priority สูงกว่า = เร่งด่วนกว่า; ถ้าเท่ากัน Arrival Time ก่อน; ถ้ายังเท่ากันใช้ Job ID
    private int compare(Job a, Job b) {
        int byPriority = Integer.compare(a.getPriority(), b.getPriority());
        if (byPriority != 0) return byPriority;
        int byArrival = a.getArrivalTime().compareTo(b.getArrivalTime());
        if (byArrival != 0) return -byArrival;
        return -a.getJobId().compareToIgnoreCase(b.getJobId());
    }

    private void swap(int a, int b) {
        Job temp = heap.get(a);
        heap.set(a, heap.get(b));
        heap.set(b, temp);
    }

    public void printPriorityArray() {
        System.out.print("[");
        for (int i = 0; i < heap.size(); i++) {
            System.out.print(heap.get(i).getJobId() + ":" + heap.get(i).getPriority());
            if (i < heap.size() - 1) System.out.print(", ");
        }
        System.out.println("]");
    }

    public void displayHeap() {
        if (heap.isEmpty()) {
            System.out.println("Heap ว่าง");
            return;
        }
        for (int i = 0; i < heap.size(); i++) {
            System.out.println("Index " + i + " -> " + heap.get(i));
        }
    }
}
