package com.example.task;

public class Task {
    private final String taskId;
    private String taskName;
    private String status; // e.g., TODO, IN_PROGRESS, DONE

    public Task(String taskId, String taskName, String status) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.status = status;
    }

    public String getTaskId() { return taskId; }
    public String getTaskName() { return taskName; }
    public void setTaskName(String taskName) { this.taskName = taskName; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    @Override
    public String toString() {
        return "Task{" + "taskId='" + taskId + '\'' + ", taskName='" + taskName + '\'' + ", status='" + status + '\'' + '}';
    }
}
