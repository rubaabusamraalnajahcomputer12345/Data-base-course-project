package com.example.projectstartingcoding;

public class CASE {
    int caseid;
    String case_type;
    String case_status;
    String date;
    int lawyer_id;
    int client_id;


    public CASE(int caseid,String case_type,String case_status,String date,int lawyer_id,int client_id){
        this.caseid=caseid;
        this.case_type=case_type;
        this.case_status=case_status;
        this.date=date;
        this.lawyer_id=lawyer_id;
        this.client_id=client_id;

    }

    public int getCaseid() {
        return caseid;
    }

    public void setCaseid(int caseid) {
        this.caseid = caseid;
    }

    public void setLawyer_id(int lawyer_id) {
        this.lawyer_id = lawyer_id;
    }

    public int getLawyer_id() {
        return lawyer_id;
    }

    public int getClient_id() {
        return client_id;
    }

    public void setClient_id(int client_id) {
        this.client_id = client_id;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public String getCase_status() {
        return case_status;
    }

    public void setCase_status(String case_status) {
        this.case_status = case_status;
    }

    public String getCase_type() {
        return case_type;
    }

    public void setCase_type(String case_type) {
        this.case_type = case_type;
    }

}
