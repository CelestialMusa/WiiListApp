package com.example.ngoveni.todo;
/**
 * Created by Mofokeng on 16-Oct-16.
 */
public class Task {

    public Task(String taskID, String task, String taskDescr, String taskDuedate, String taskCompleted, String username) {
        this.taskID = taskID;
        this.task = task;
        this.taskDescr = taskDescr;
        this.taskDuedate = taskDuedate;
        this.taskCompleted = taskCompleted;
        this.username = username;
    }

    public Task(String task, String taskDescr, String taskDuedate, String taskCompleted, String username) {
        this.task = task;
        this.taskDescr = taskDescr;
        this.taskDuedate = taskDuedate;
        this.taskCompleted = taskCompleted;
        this.username = username;
    }

    private String taskID;
    private String task;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    private String username;
    private String taskDescr;
    private String taskDuedate;
    private String taskCompleted;

    public String isTaskCompleted() {
        return taskCompleted;
    }

    public void setTaskCompleted(String taskCompleted) {
        this.taskCompleted = taskCompleted;
    }

    public String getTaskDuedate() {
        return taskDuedate;
    }

    public void setTaskDuedate(String taskDuedate) {
        this.taskDuedate = taskDuedate;
    }

    public String getTaskDescr() {
        return taskDescr;
    }

    public void setTaskDescr(String taskDescr) {
        this.taskDescr = taskDescr;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public String getTaskID() {
        return taskID;
    }

    public void setTaskID(String taskID) {
        this.taskID = taskID;
    }


}
