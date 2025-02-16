package todoListApp;

public class Task {

    private int taskID;
    private String taskName;
    private String dueDate;

    public Task(int taskID,String taskName,String dueDate){

        this.taskID = taskID;
        this.taskName = taskName;
        this.dueDate = dueDate;
    }


    public int getTaskID() {
        return taskID;
    }

    public String getDueDate() {
        return dueDate;
    }

    public String getTaskName() {
        return taskName;
    }


}
