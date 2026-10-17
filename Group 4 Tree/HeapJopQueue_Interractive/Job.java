public class Job {
    private final String jobId;
    private final String jobName;
    private int priority;
    private final String arrivalTime;
    private final int estimatedTime;

    public Job(String jobId, String jobName, int priority, String arrivalTime, int estimatedTime) {
        this.jobId = jobId;
        this.jobName = jobName;
        this.priority = priority;
        this.arrivalTime = arrivalTime;
        this.estimatedTime = estimatedTime;
    }

    public String getJobId() { return jobId; }
    public String getJobName() { return jobName; }
    public int getPriority() { return priority; }
    public void setPriority(int priority) { this.priority = priority; }
    public String getArrivalTime() { return arrivalTime; }
    public int getEstimatedTime() { return estimatedTime; }

    public Job copy() {
        return new Job(jobId, jobName, priority, arrivalTime, estimatedTime);
    }

    @Override
    public String toString() {
        return jobId + " " + jobName + " | Priority=" + priority
                + " | Arrival=" + arrivalTime + " | Estimated=" + estimatedTime;
    }
}
