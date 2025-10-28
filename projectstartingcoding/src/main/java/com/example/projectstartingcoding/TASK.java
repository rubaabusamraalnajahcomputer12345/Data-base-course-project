package com.example.projectstartingcoding;

public class TASK {

    private int lawyer_id;
    private int task_id;
            private String status;
    private String deadline;
    private String task_description;


    public TASK(int task_id,String task_description,String deadline,String status,int lawyer_id){
        this.task_description=task_description;
        this.status=status;
        this.task_id=task_id;
        this.deadline=deadline;
        this.lawyer_id=lawyer_id;

    }




    public int getLawyer_id() {
        return lawyer_id;
    }

    public int getTask_id() {
        return task_id;
    }

    public String getDeadline() {
        return deadline;
    }

    public String getTask_description() {
        return task_description;
    }

    public String getStatus() {
        return status;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public void setTask_id(int task_id) {
        this.task_id = task_id;
    }

    public void setLawyer_id(int lawyer_id) {
        this.lawyer_id = lawyer_id;
    }

    public void setTask_description(String task_description) {
        this.task_description = task_description;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
